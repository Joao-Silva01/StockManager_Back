package com.dev.StockManager.exceptions;

public class IdNotFoundException extends RuntimeException{

    public IdNotFoundException(String message) {
        super(message);
    }
}
