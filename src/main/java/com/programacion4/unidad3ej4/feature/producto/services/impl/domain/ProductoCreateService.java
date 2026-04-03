package com.programacion4.unidad3ej4.feature.producto.services.impl.domain;

import org.springframework.stereotype.Service;

import com.programacion4.unidad3ej4.feature.producto.dtos.request.ProductoCreateRequestDto;
import com.programacion4.unidad3ej4.feature.producto.dtos.response.ProductoResponseDto;
import com.programacion4.unidad3ej4.feature.producto.exceptions.ResourceConflictException;
import com.programacion4.unidad3ej4.feature.producto.exceptions.ResourceNotFoundException;
import com.programacion4.unidad3ej4.feature.producto.mappers.ProductoMapper;
import com.programacion4.unidad3ej4.feature.producto.models.Categoria;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.ICategoriaRepository;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.commons.IProductoExistByNameService;
import com.programacion4.unidad3ej4.feature.producto.services.interfaces.domain.IProductoCreateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoCreateService implements IProductoCreateService {

    private final IProductoRepository productoRepository;
    private final ICategoriaRepository categoriaRepository;
    private final IProductoExistByNameService productoExistByNameService;

    @Override
    public ProductoResponseDto execute(ProductoCreateRequestDto request) {
        
        String nombreFormateado = capitalizar(request.getNombre());
        String descFormateada = capitalizar(request.getDescripcion());

        if (productoExistByNameService.execute(nombreFormateado)) {
            throw new ResourceConflictException("Recurso en Conflicto: El producto ya existe.");
        }

        Categoria categoria = categoriaRepository.findById(request.getIdCategoria())
                .orElseThrow(() -> new ResourceNotFoundException("La categoría ingresada no existe."));

        Producto producto = ProductoMapper.toEntity(request, categoria);
        
        producto.setNombre(nombreFormateado);
        producto.setDescripcion(descFormateada);
        producto.setEstaEliminado(false); 

        Producto productoGuardado = productoRepository.save(producto);
        
        return ProductoMapper.toResponseDto(productoGuardado);
    }

    private String capitalizar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return texto;
        }
        String clean = texto.trim().toLowerCase();
        return clean.substring(0, 1).toUpperCase() + clean.substring(1);
    }
}