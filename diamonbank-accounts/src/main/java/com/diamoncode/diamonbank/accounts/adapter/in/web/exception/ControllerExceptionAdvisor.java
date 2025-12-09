package com.diamoncode.diamonbank.accounts.adapter.in.web.exception;

import com.diamondcode.common.adapter.in.web.exception.CustomFoundException;
import com.diamondcode.common.adapter.in.web.exception.CustomNotFoundException;
import com.diamondcode.common.adapter.in.web.model.ResponseErrorDTO;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Use the @RestControllerAdvice annotation to handle exceptions globally in a Spring Boot application
@RestControllerAdvice
public class ControllerExceptionAdvisor {

    // Handle AccountNotFoundException and return a ResponseErrorDTO with a BAD_REQUEST status
    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseErrorDTO handleNotFoundException(CustomNotFoundException exception) {
        return new ResponseErrorDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }

    @ExceptionHandler(CustomFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseErrorDTO handleFoundException(CustomFoundException exception) {
        return new ResponseErrorDTO(HttpStatus.CONFLICT.value(), exception.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleValidationException(MethodArgumentNotValidException ex) {
        List<ObjectError> errors = ex.getBindingResult().getAllErrors();
        Map<String, String> map = new HashMap<>(errors.size());

        errors.forEach(error -> {
            String key = ((FieldError) error).getField();
            String val = error.getDefaultMessage();
            map.put(key, val);
        });

        return new ResponseErrorDTO(HttpStatus.BAD_REQUEST.value(), "Bad request", map);
    }

    @ExceptionHandler(ClientValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handleValidationException(ClientValidationException ex) {
        return new ResponseErrorDTO(HttpStatus.BAD_REQUEST.value(), String.format("Backend validation: %s", ex.getMessage()));
    }


    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseErrorDTO handlePropertyValidationException(PropertyReferenceException ex) {
        return new ResponseErrorDTO(HttpStatus.BAD_REQUEST.value(), String.format("Bad request: %s", ex.getMessage()));
    }
}
