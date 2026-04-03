package com.programacion4.unidad3ej4.feature.producto.exceptions;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.programacion4.unidad3ej4.config.exceptions.CustomException;

public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, List.of("No se encontró el recurso solicitado."));
    }
}