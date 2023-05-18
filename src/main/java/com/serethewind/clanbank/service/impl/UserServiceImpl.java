package com.serethewind.clanbank.service.impl;

import com.serethewind.clanbank.entity.User;
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


//        user.setFirstName(userRequest.getFirstName());
//        user.setLastName(userRequest.getLastName());
//        user.setOtherName(userRequest.getOtherName());
//        user.setAccountNumber(Utility.generateAccountNumber());
//        user.setAccountBalance(0.0);
//        user.setPhoneNumber(userRequest.getPhoneNumber());
//        user.setAlternativePhoneNumber(userRequest.getAlternativePhoneNumber());
//        user.setEmail(userRequest.getEmail());
//        user.setGender(userRequest.getGender());
//        user.setAddress(userRequest.getAddress());
//        user.setReligion(userRequest.getReligion());
//        user.setDateOfBirth(userRequest.getDateOfBirth());
//        user.setBvn(userRequest.getBvn());
//        user.setReferralCode(userRequest.getReferralCode());
//        user.setPassword(userRequest.getPassword());
//
//        return userRepository.save(user);
    }


    @Override
    public List<UserResponse> fetchAllRegisteredUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();
        for(User user : userList){
            UserResponse userResponse= modelMapper.map(user, UserResponse.class);
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
        userRepository.deleteById(id);


        return "User with " + id + " has been deleted";
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setOtherName(userRequest.getOtherName());
        user.setAccountNumber(Utility.generateAccountNumber());
        user.setAccountBalance(0.0);
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setAlternativePhoneNumber(userRequest.getAlternativePhoneNumber());
        user.setEmail(userRequest.getEmail());
        user.setGender(userRequest.getGender());
        user.setAddress(userRequest.getAddress());
        user.setReligion(userRequest.getReligion());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setBvn(userRequest.getBvn());
        user.setReferralCode(userRequest.getReferralCode());
        user.setPassword(userRequest.getPassword());

        return userRepository.save(user);
    }


}
