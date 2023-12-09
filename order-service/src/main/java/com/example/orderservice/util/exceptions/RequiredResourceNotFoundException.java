package com.example.orderservice.util.exceptions;

public class RequiredResourceNotFoundException extends RuntimeException{
    public RequiredResourceNotFoundException(String message) {
        super(message);
    }
}
