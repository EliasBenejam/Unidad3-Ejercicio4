package com.programacion4.unidad3ej4.feature.producto.controllers.get;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoGetByIdService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoGetByIdController {

    private final IProductoGetByIdService productoGetByIdService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDto> getById(@PathVariable Long id) {
        ProductoResponseDto response = productoGetByIdService.execute(id);
        return ResponseEntity.ok(response);
    }
}