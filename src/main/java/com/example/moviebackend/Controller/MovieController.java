package com.example.moviebackend.Controller;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.moviebackend.Entity.Movie;
import com.example.moviebackend.Service.MovieService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }
    @PostMapping("saveMovies")
    public Movie saved(@Valid @RequestBody Movie m){
        return movieService.saved(m);
    }
    @GetMapping("/movies")
    public Page<Movie> getAllMovies(
        @RequestParam(defaultValue = "0") int Page,
        @RequestParam(defaultValue= "10")int size){
            return movieService.getAllMovies(Page, size);
    }
    @PostMapping("saveBulk")
    public List<Movie> saveBulk(@RequestBody @Valid List<Movie> m){
        return movieService.savebulk(m);
    }
    @GetMapping("/popular")
    public List<Movie> getPopularMovies(){
        return movieService.getPopularMovies();
    }
    @GetMapping("/genre/{genre}")
    public List<Movie> getByGenre (@PathVariable String genre){
        return movieService.getMoviesByGenre(genre);
    }
    @GetMapping("findByname/{name}")
    public Movie getbyname(@PathVariable String name){
        return movieService.findbyName(name);
    }
      @GetMapping("Search/{name}")
    public List<Movie>getSimilar(@PathVariable String name){
        return movieService.search(name);
    }
    @GetMapping("/upcoming")
    public List<Movie> getUpcomingMovies() {
        return movieService.getUpcomingMovies();
    }
    @GetMapping("/desc")
    public List<Movie> getMoviesBasedondesc(){
        return movieService.getMoviesbydesc();
    }
    @DeleteMapping("/del/{id}")
    public String deletemovie(@PathVariable Long id){
        return movieService.deletemovie(id);
    }

}
