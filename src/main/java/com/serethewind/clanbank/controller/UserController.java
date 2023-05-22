package com.serethewind.clanbank.controller;

import com.serethewind.clanbank.payload.UserRequest;
import com.serethewind.clanbank.payload.UserResponse;
import com.serethewind.clanbank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.registerUser(userRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.fetchAllRegisteredUsers(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateSingleUser(@PathVariable("id") Long id, @RequestBody UserRequest userRequest) {
        return new ResponseEntity<>(userService.updateUser(id, userRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getSingleUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.fetchSingleUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}/top-up")
    public ResponseEntity<String> creditAccount(@PathVariable("id") String accountNumber, @RequestParam("amount") double amount) {
        return new ResponseEntity<>(userService.creditAccount(accountNumber, amount), HttpStatus.OK);
    }

    @PatchMapping("/{id}/withdraw")
    public ResponseEntity<String> debitAccount(@PathVariable("id") String accountNumber, @RequestParam("amount") double amount) {
        return new ResponseEntity<>(userService.debitAccount(accountNumber, amount), HttpStatus.OK);
    }

    @PatchMapping("/{id}/transfer")
    public ResponseEntity<String> transferToAnotherUser(@PathVariable("id") String accountNumber, @RequestParam("amount") double amount, @RequestParam("accountNumber") String recipientAccountNumber) {
        return new ResponseEntity<>(userService.transferToOtherUser(accountNumber, recipientAccountNumber, amount), HttpStatus.OK);
    }
}
