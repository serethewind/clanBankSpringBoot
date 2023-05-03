package com.serethewind.clanbank.service;

import com.serethewind.clanbank.entity.User;
import com.serethewind.clanbank.payload.UserRequest;

import java.util.List;


public interface UserService {

    User registerUser(UserRequest userRequest);

    List<User> fetchAllRegisteredUsers();

    User fetchSingleUser(Long id);

    User updateUser(UserRequest userRequest, Long id);
}
