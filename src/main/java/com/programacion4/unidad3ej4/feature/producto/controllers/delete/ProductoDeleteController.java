package com.programacion4.unidad3ej4.feature.producto.controllers.delete;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoDeleteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoDeleteController {

    private final IProductoDeleteService productoDeleteService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoDeleteService.execute(id);
        
        return ResponseEntity.noContent().build();
    }
}