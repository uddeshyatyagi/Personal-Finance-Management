package com.example.pfm.repository;

import com.example.pfm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;  // Import Optional from java.util

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // Method to find a user by username
}
