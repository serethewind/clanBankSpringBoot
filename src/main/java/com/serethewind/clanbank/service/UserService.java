package com.serethewind.clanbank.service;

import com.serethewind.clanbank.entity.User;
import com.serethewind.clanbank.payload.UserRequest;

import java.util.List;


public interface UserService {

    User registerUser(UserRequest userRequest);

    List<User> fetchAllRegisteredUsers();

    User fetchSingleUserById(Long id);

    String deleteUser(Long id);

    User updateUser(Long id, UserRequest userRequest);
}
