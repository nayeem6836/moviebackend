package com.example.moviebackend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviebackend.dto.UserResponseDto;
import com.example.moviebackend.model.User;
import com.example.moviebackend.repository.UserRepository;

    @Service
 public class UserService {

    @Autowired
    private UserRepository userRepository;
   

    public User userRegister(UserResponseDto userResponseDto) {

        String email = userResponseDto.getEmail();
    
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExists("User with email " + email + " already exists");
        }
        User user = new User(
            userResponseDto.getEmail(),
            userResponseDto.getPassword(),
            userResponseDto.getName()
        );
    
        return userRepository.save(user);
    }
    
    

    //user Authenticate
    public boolean authenticate(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                return true;
            } else {
                return false; 
            }
        } else {
            return false; 
        }
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() ->new UserNotFoundException("User Not Found"+email));

    } 
    

 }