package com.wladecode.diamonbank.web.exception;

import com.diamondcode.common.adapter.in.web.model.ResponseErrorDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ERROR = "error";
    private static final String NO_DATA_FOUND = "No Data found";
    private static final String UNEXPECTED_ERROR = "An unexpected error occurred";

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public void handleAccountNotFoundException(HttpClientErrorException.NotFound ex,
                                                 Model model,
                                                 HttpServletRequest request) {
        if (ex.getResponseBodyAsString() != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                ResponseErrorDTO error = mapper.readValue(ex.getResponseBodyAsString(), ResponseErrorDTO.class);
                String messageError = error.getHeader().getMessage();
                model.addAttribute(ERROR, error);
                model.addAttribute(ERROR_MESSAGE, messageError);
                return;
            } catch (IOException e) {
                log.error("Error parsing error response", e);
            }
        }

        model.addAttribute(ERROR, new ResponseErrorDTO(404, NO_DATA_FOUND));
        model.addAttribute(ERROR_MESSAGE, NO_DATA_FOUND);


    }

    @ExceptionHandler(Exception.class)
    public String handleGlobalException(Exception ex, Model model) {
        log.error("Unexpected error occurred", ex);
        model.addAttribute(ERROR_MESSAGE, UNEXPECTED_ERROR);
        return "error/500"; // Your custom error page
    }
}