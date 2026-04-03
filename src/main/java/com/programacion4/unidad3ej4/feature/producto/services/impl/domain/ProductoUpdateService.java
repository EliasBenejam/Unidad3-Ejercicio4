package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoUpdateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.exceptions.ResourceConflictException;
import com.programacion4.unidad3ej4.feature.producto.exceptions.ResourceNotFoundException;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.ICategoriaRepository;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoUpdateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoUpdateService implements IProductoUpdateService {

    private final IProductoRepository productoRepository;
    private final ICategoriaRepository categoriaRepository;

    @Override
    @Transactional
    public ProductoResponseDto execute(Long id, ProductoUpdateRequestDto request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        if (producto.isEstaEliminado()) {
            throw new ResourceNotFoundException("No se puede actualizar un producto eliminado.");
        }

        if (!producto.getNombre().equalsIgnoreCase(request.getNombre()) && 
             productoRepository.existsByNombre(request.getNombre())) {
            throw new ResourceConflictException("Ya existe otro producto con el nombre: " + request.getNombre());
        }

        var categoria = categoriaRepository.findById(request.getCategoriaId())
                .orElseThrow(() -> new ResourceNotFoundException("La categoría ingresada no existe."));

        producto.setNombre(request.getNombre());
        producto.setCodigo(request.getCodigo());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoria(categoria);

        return ProductoMapper.toResponseDto(productoRepository.save(producto));
    }
}