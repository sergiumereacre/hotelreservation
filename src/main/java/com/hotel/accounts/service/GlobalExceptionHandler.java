package com.hotel.accounts.service;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public void handleGenericException(Exception ex) {
        // Return response entity based on exception type
        return;
    }
}
