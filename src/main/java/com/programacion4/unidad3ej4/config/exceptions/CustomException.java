package com.programacion4.unidad3ej4.config.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException {
    
    private final HttpStatus status;
    private final List<String> errors;

    public CustomException(String message, HttpStatus status, List<String> errors) {
        super(message);
        this.status = status; 
        this.errors = errors; 
    }
}
