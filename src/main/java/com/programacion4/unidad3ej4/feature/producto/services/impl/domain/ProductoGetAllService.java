package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoGetAllService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoGetAllService implements IProductoGetAllService {

    private final IProductoRepository productoRepository;

    @Override
    public List<ProductoResponseDto> execute() {
        return productoRepository.findAllByEstaEliminadoFalse()
                .stream()
                .map(ProductoMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}