package com.sprint2.personalworkout.exception;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(UserAlreadyExistsException.class)
    public final ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {    
     ExceptionResponse exceptionResponse  = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));   
     return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);    
    }
    
    @ExceptionHandler(UserNotFoundException.class)
    public final   ResponseEntity<Object>  handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {    
     ExceptionResponse exceptionResponse  = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(true));     
     return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);    
    	
    }
    
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {    
     ExceptionResponse exceptionResponse  = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));     
     return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);    
    }
    
    @ExceptionHandler(WorkoutNotFoundException.class)
    public final ResponseEntity<Object> handleWorkoutNotFoundException(WorkoutNotFoundException ex, WebRequest request) {    
     ExceptionResponse exceptionResponse  = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));     
     return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);    
    }
    
    @ExceptionHandler(WorkoutAlreadyExistsException.class)
    public final ResponseEntity<Object> handleWorkoutAlreadyExistsException(WorkoutAlreadyExistsException ex, WebRequest request) {    
     ExceptionResponse exceptionResponse  = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));     
     return new ResponseEntity<>(exceptionResponse, HttpStatus.UNPROCESSABLE_ENTITY);    
    }
    
}