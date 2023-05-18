package com.serethewind.clanbank.service;

import com.serethewind.clanbank.payload.UserRequest;
import com.serethewind.clanbank.payload.UserResponse;

import java.util.List;


public interface UserService {

    UserResponse registerUser(UserRequest userRequest);

    List<UserResponse> fetchAllRegisteredUsers();

    UserResponse fetchSingleUserById(Long id);

    String deleteUser(Long id);

    UserResponse updateUser(Long id, UserRequest userRequest);

    UserResponse creditAccount(Long id, double amount);

    UserResponse debitAccount(Long id, double amount);
}
