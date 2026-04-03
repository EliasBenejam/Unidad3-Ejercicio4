package com.programacion4.unidad3ej4.feature.producto.controllers.put;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoUpdateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoUpdateController {

    private final IProductoUpdateService productoUpdateService;

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> update(
            @PathVariable Long id, 
            @Valid @RequestBody ProductoUpdateRequestDto request) {
        
        ProductoResponseDto response = productoUpdateService.execute(id, request);
        return ResponseEntity.ok(response);
    }
}