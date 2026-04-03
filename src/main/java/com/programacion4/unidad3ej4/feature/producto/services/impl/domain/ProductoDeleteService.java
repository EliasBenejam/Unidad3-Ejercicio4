package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programacion4.unidad3ej4.feature.producto.exceptions.ResourceNotFoundException;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoDeleteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoDeleteService implements IProductoDeleteService {

    private final IProductoRepository productoRepository;

    @Override
    @Transactional
    public void execute(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró el producto para eliminar con ID: " + id));

        producto.setEstaEliminado(true);

        productoRepository.save(producto);
    }
}