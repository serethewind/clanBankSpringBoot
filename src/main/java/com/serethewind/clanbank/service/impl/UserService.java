package com.serethewind.clanbank.service.impl;

import com.serethewind.clanbank.entity.User;

import java.util.List;

public interface UserService {
    List<User> fetchAllUsers();

    User fetchSingleUser(Long id);
}
