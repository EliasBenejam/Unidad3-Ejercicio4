package com.programacion4.unidad3ej4.feature.producto.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductoCreateRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotNull(message = "El ID de la categoría es obligatorio")
    private Long idCategoria;
}
