package com.example.moviebackend.Actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import com.example.moviebackend.Repository.MovieRepository;


@Endpoint(id = "movieCount") 
@Component
public class MovieCountEndpoint {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieCountEndpoint(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @ReadOperation  
    public String countMovies() {
        long count = movieRepository.count();  // Count the number of movies
        return "Total Movies: " + count;  // Return the count as a string
    }
}