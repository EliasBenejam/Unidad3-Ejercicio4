package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.exceptions.ResourceNotFoundException;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoGetByIdService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoGetByIdService implements IProductoGetByIdService {

    private final IProductoRepository productoRepository;

@Override
    public ProductoResponseDto execute(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));

        if (producto.isEstaEliminado()) {
            throw new ResourceNotFoundException("El producto solicitado ha sido eliminado.");
        }

        return ProductoMapper.toResponseDto(producto);
    }
}