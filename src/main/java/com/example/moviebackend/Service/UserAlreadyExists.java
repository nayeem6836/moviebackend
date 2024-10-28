package com.example.moviebackend.service;

// Custom exception extending RuntimeException
public class UserAlreadyExists extends RuntimeException {

    // Constructor to pass the exception message
    public UserAlreadyExists(String message) {
        super(message);  // This must be the first statement in the constructor
    }
}
