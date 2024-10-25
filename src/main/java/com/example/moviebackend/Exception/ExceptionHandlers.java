package com.example.moviebackend.Exception;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class ExceptionHandlers {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }    


@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> errorMessages = new ArrayList<>(); 
    
    for (ObjectError error : ex.getBindingResult().getAllErrors()) {
     
        errorMessages.add(error.getDefaultMessage());
    }
    
    return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
}

    
}
