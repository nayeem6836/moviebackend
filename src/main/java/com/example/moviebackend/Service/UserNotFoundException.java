package com.example.moviebackend.service;

// Custom exception extending RuntimeException
public class UserNotFoundException extends RuntimeException {

    // Constructor to pass the exception message
    public UserNotFoundException(String message) {
        super(message);  // This must be the first statement in the constructor
    }
}
