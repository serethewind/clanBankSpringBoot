package com.serethewind.clanbank.controller;

import com.serethewind.clanbank.entity.User;
import com.serethewind.clanbank.payload.UserRequest;
import com.serethewind.clanbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public User registerUser(@RequestBody UserRequest userRequest){
        return userService.registerUser(userRequest);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.fetchAllRegisteredUsers();
    }

    @GetMapping("/{id}")
    public User getSingleUser(@PathVariable("id") Long id){
        return userService.fetchSingleUser(id);
    }

    @PutMapping("/{id}")
    public User updateSingleUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest){
        return userService.updateUser(userRequest, id);
    }


}
