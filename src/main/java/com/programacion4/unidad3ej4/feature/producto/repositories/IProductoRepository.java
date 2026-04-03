package com.programacion4.unidad3ej4.feature.producto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.programacion4.unidad3ej4.feature.producto.models.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
    
    boolean existsByNombre(String nombre);
    
    List<Producto> findAllByEstaEliminadoFalse(); 
}