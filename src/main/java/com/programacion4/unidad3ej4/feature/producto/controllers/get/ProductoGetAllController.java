package com.programacion4.unidad3ej4.feature.producto.controllers.get;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoGetAllService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoGetAllController {

    private final IProductoGetAllService productoGetAllService;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDto>> getAll() {
        List<ProductoResponseDto> response = productoGetAllService.execute();
        return ResponseEntity.ok(response);
    }
}