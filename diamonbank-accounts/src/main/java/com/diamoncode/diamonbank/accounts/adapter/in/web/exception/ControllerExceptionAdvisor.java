package com.diamoncode.diamonbank.accounts.adapter.in.web.exception;

import com.diamoncode.diamonbank.accounts.adapter.in.web.dto.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Use the @RestControllerAdvice annotation to handle exceptions globally in a Spring Boot application
@RestControllerAdvice
public class ControllerExceptionAdvisor {

    // Handle AccountNotFoundException and return a ResponseErrorDTO with a BAD_REQUEST status
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleAccountNotFoundException(AccountNotFoundException exception) {
        return new ResponseErrorDTO(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
