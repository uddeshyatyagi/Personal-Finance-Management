package com.example.pfm.controller;

import com.example.pfm.model.User;
import com.example.pfm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;  // Import List from java.util

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Endpoint to create multiple users
    @PostMapping
    public List<User> createUsers(@RequestBody List<User> users) {
        return userService.createUsers(users);
    }

    // Endpoint to authenticate user and generate JWT token
    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return userService.authenticate(user);
    }
}
