package com.programacion4.unidad3ej4.feature.producto.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.programacion4.unidad3ej4.config.exceptions.CustomException;

public class ResourceConflictException extends CustomException {
    public ResourceConflictException(String message) {
        super(message, HttpStatus.CONFLICT, List.of("El producto ya se encuentra registrado."));
    }
}