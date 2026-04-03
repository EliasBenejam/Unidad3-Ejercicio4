package com.programacion4.unidad3ej4.feature.producto.controllers.post; // <-- Fijate que agregamos .post al final

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoCreateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoCreateController {

    private final IProductoCreateService productoCreateService;

    @PostMapping
    public ResponseEntity<ProductoResponseDto> create(@Valid @RequestBody ProductoCreateRequestDto request) {
        ProductoResponseDto response = productoCreateService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}