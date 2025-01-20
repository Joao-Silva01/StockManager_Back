package com.dev.StockManager.config;

import com.dev.StockManager.exceptions.IdNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    private ResponseEntity<ErrorMessage> IdNotFoundExceptionHandle(IdNotFoundException exception, HttpServletRequest request){
        ErrorMessage error = new ErrorMessage(Instant.now(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
