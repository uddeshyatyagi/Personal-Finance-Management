package com.example.pfm.service;

import com.example.pfm.model.User;
import com.example.pfm.repository.UserRepository;
import com.example.pfm.util.JwtUtil;  // Import JwtUtil from util package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;  // Import List from java.util
import java.util.Optional;  // Import Optional from java.util

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // Authenticate user
    public ResponseEntity<String> authenticate(User user) {
        // Find the user by username (Returns Optional<User>)
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());

        if (foundUser.isPresent() && foundUser.get().getPassword().equals(user.getPassword())) {
            // Generate JWT token if user is authenticated
            String token = jwtUtil.generateToken(foundUser.get());
            return ResponseEntity.ok(token); // Return token if successful
        }

        // Return error response if credentials are invalid
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    // Create multiple users
    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users); // Save the list of users
    }
}
