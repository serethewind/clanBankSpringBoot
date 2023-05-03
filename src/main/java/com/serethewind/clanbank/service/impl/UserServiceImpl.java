package com.serethewind.clanbank.service.impl;

import com.serethewind.clanbank.entity.User;
import com.serethewind.clanbank.payload.UserRequest;
import com.serethewind.clanbank.repository.UserRepository;
import com.serethewind.clanbank.service.UserService;
import com.serethewind.clanbank.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(UserRequest userRequest) {

        //check if the user already exists by email

        //save record
//        if (isExist) {
//            userRequest = null;
//        }
        //save record
        User user = new User();
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

    @Override
    public List<User> fetchAllRegisteredUsers() {
        return userRepository.findAll();
    }

    @Override
    public User fetchSingleUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public User updateUser(UserRequest userRequest, Long id) {
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
