package com.serethewind.clanbank.service.impl;

import com.serethewind.clanbank.entity.User;
import com.serethewind.clanbank.exception.ResourceNotFoundException;
import com.serethewind.clanbank.payload.UserRequest;
import com.serethewind.clanbank.payload.UserResponse;
import com.serethewind.clanbank.repository.UserRepository;
import com.serethewind.clanbank.service.UserService;
import com.serethewind.clanbank.util.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponse registerUser(UserRequest userRequest) {

        User user = modelMapper.map(userRequest, User.class);
        user.setAccountNumber(Utility.generateAccountNumber());
        user.setAccountBalance(0.0);
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);

    }


    @Override
    public List<UserResponse> fetchAllRegisteredUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user : userList) {
            UserResponse userResponse = modelMapper.map(user, UserResponse.class);
            userResponseList.add(userResponse);
        }
        return userResponseList;
    }

    @Override
    public UserResponse fetchSingleUserById(Long id) {
        return modelMapper.map(userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)), UserResponse.class);
    }

    @Override
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id not found in database."));
        userRepository.delete(user);
        return "User deleted successfully";
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        modelMapper.map(userRequest, user);
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public String creditAccount(Long id, double amount) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with Id not found"));
        double newBalance = amount + user.getAccountBalance();
        user.setAccountBalance(newBalance);
        userRepository.save(user);
        String response = "Account successfully credited. Your new account balance is " + user.getAccountBalance();
        return response;
    }

    @Override
    public String debitAccount(Long id, double amount) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with Id not found"));
        String response = "";
        if (amount > user.getAccountBalance()) {
            response = "Insufficient funds, debit failed.";
        } else {
            double newBalance = user.getAccountBalance() - amount;
            user.setAccountBalance(newBalance);
            userRepository.save(user);
            response = "Account successfully debited. Your new account balance is " + user.getAccountBalance();
        }
        return response;
    }

    @Override
    public String transferToOtherUser(Long id, String accountNumber, double amount) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id not found"));
        Boolean status = userRepository.existsByAccountNumber(accountNumber);
        String response = "";
        if (!status) {
            response = "Account to be credited incorrect. Try again.";
        } else if (status && user.getAccountBalance() < amount) {
            response = "Insufficient funds.";
        } else if (status && user.getAccountBalance() >= amount) {
            User recipientUser = userRepository.findByAccountNumber(accountNumber);
            debitAccount(user.getId(), amount);
            creditAccount(recipientUser.getId(), amount);
            response = (amount + " successfully transferred. Balance is " + user.getAccountBalance());
        }
        return response;
    }

    //trf
    /**
     *enter via id...
     *check your balance and see if its good enough for a debit
     * creditUser with account.
     * i need my own long id,
     * i need user's account.
     * Return my updated balance coupled with 'i have been successfully transferred to other user
     * use cases.
     * i dont have enough money
     * i have enough money but i dont write the right recipient account
     * i have enough money and i input the right account
     * transfer successful
     *
     */


}
