package com.example.moviebackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moviebackend.dto.LoginDto;
import com.example.moviebackend.dto.UserResponseDto;
import com.example.moviebackend.model.User;
import com.example.moviebackend.service.UserService;

@RestController
@RequestMapping("/api/movie/users/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userservice;

    @PostMapping("/register")
    public ResponseEntity<String> userRegister(@RequestBody UserResponseDto userResponseDto) {
        try {
            User user = userservice.userRegister(userResponseDto);
            return ResponseEntity.ok("User registered successfully: " + user.getId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody LoginDto loginDto) {
        boolean authenticateUser = userservice.authenticate(loginDto.getEmail(), loginDto.getPassword());
        if (authenticateUser) {
            User user = userservice.findByEmail(loginDto.getEmail());
            if (user != null) {
                UserResponseDto userResponseDto = new UserResponseDto(user.getEmail(), user.getName(), null);
                return ResponseEntity.ok(userResponseDto);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email and password");
        }
    }
}
