package com.serethewind.clanbank.controller;

import com.serethewind.clanbank.entity.User;
import com.serethewind.clanbank.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.fetchAllUsers();
    }
}
