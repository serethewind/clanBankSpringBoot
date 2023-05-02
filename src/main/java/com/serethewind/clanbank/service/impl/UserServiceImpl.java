package com.serethewind.clanbank.service.impl;

import com.serethewind.clanbank.entity.User;
import com.serethewind.clanbank.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User fetchSingleUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
