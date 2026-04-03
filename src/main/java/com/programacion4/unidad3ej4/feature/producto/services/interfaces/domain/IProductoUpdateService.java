package com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain;

import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;

public interface IProductoUpdateService {
    ProductoResponseDto execute(Long id, ProductoUpdateRequestDto request);
}