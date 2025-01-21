package com.dev.StockManager.config;

import com.dev.StockManager.exceptions.IdNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    private ResponseEntity<ErrorMessage> idNotFoundExceptionHandle(IdNotFoundException exception,
                                                                   HttpServletRequest request){
        ErrorMessage error = new ErrorMessage(Instant.now(),HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(error.getStatus()).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorMessage> validationExceptionsHandle(MethodArgumentNotValidException exception,
                                                                HttpServletRequest request){
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        String erroMessages =fieldErrors.stream().findFirst().map(FieldError::getDefaultMessage).get();

        ErrorMessage error = new ErrorMessage(Instant.now(),HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST, erroMessages, request.getRequestURI());

        return ResponseEntity.status(error.getStatus()).body(error);
    }
}
