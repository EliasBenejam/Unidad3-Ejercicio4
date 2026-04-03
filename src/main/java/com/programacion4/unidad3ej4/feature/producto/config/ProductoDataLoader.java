package com.programacion4.unidad3ej4.feature.producto.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.programacion4.unidad3ej4.feature.producto.models.Categoria;
import com.programacion4.unidad3ej4.feature.producto.models.Producto;
import com.programacion4.unidad3ej4.feature.producto.repositories.ICategoriaRepository;
import com.programacion4.unidad3ej4.feature.producto.repositories.IProductoRepository;

@Configuration
public class ProductoDataLoader {

    private static final String[] TIPOS_PRODUCTO = {
            "Remera", "Buzo", "Pantalón", "Campera", "Chomba", "Zapatillas", "Gorra"
    };

    private static final String[] DESCRIPCIONES = {
            "de algodón premium", "estilo oversize", "con detalles bordados", 
            "impermeable", "de temporada invierno", "edición limitada"
    };

    @Bean
    public CommandLineRunner cargarDatos(IProductoRepository productoRepository, 
                                        ICategoriaRepository categoriaRepository) {
        return args -> {
            if (productoRepository.count() > 0 || categoriaRepository.count() > 0) {
                return;
            }

            List<Categoria> categoriasSubidas = new ArrayList<>();
            String[] nombresCategorias = {"Cat 1", "Cat 2", "Cat 3"};
            
            for (String nombreCat : nombresCategorias) {
                Categoria cat = new Categoria();
                cat.setNombre(nombreCat);
                categoriasSubidas.add(categoriaRepository.save(cat));
            }

            Random random = new Random();
            int cantidad = 5 + random.nextInt(6);

            for (int i = 0; i < cantidad; i++) {
                String tipo = TIPOS_PRODUCTO[random.nextInt(TIPOS_PRODUCTO.length)];
                String desc = DESCRIPCIONES[random.nextInt(DESCRIPCIONES.length)];
                
                Producto producto = new Producto();
                producto.setNombre(tipo + " " + (i + 1));
                producto.setCodigo("PROD-" + (1000 + i));
                producto.setDescripcion(tipo + " " + desc);
                
                double precioAleatorio = 1500.0 + (8000.0 * random.nextDouble());
                producto.setPrecio(Math.round(precioAleatorio * 100.0) / 100.0);
                
                producto.setStock(10 + random.nextInt(91));
                producto.setEstaEliminado(false);

                Categoria catAsignada = categoriasSubidas.get(random.nextInt(categoriasSubidas.size()));
                producto.setCategoria(catAsignada);

                productoRepository.save(producto);
            }
            
            System.out.println(">> Se han precargado 3 categorías y " + cantidad + " productos con sus relaciones.");
        };
    }
}