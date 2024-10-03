package com.deepanshu.dairydriveapi.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(LoginAttemptFailedException.class)
    public ResponseEntity<String> loginAttemptFailedException(LoginAttemptFailedException ex){
        logger.debug("{}: {}", ex.getClass().getName(), ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> resourceNotFoundException(ResourceNotFoundException ex){
        logger.debug("{}: {}", ex.getClass().getName(), ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        logger.debug("{}: {}", ex.getClass().getName(), ex.getMessage());
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((err) ->{
            String fieldName = ((FieldError)(err)).getField();
            String msg = err.getDefaultMessage();
            errors.put(fieldName,msg);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DataIntegrityViolationException>  dataIntegrityViolationExceptionHandler(DataIntegrityViolationException ex){
        logger.debug("{}: {}",ex.getClass().getName(), ex.getMessage());
        HttpStatus httpStatus = HttpStatus.OK;
        String msg = ex.getMessage();
        if(msg.contains("Duplicate entry"))
            httpStatus = HttpStatus.CONFLICT;
        return new ResponseEntity<>(ex,httpStatus);
    }


}
