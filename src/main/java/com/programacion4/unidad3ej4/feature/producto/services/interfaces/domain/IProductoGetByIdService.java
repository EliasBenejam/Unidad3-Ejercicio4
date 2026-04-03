package com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain;

import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;

public interface IProductoGetByIdService {
    ProductoResponseDto execute(Long id);
}