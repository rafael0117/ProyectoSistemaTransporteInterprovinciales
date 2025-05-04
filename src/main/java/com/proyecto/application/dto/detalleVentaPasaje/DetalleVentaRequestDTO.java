package com.proyecto.application.dto.detalleVentaPasaje;

import lombok.Data;

@Data
public class DetalleVentaRequestDTO {
    private Long idViaje;
    private double precio;
    private int cantidad;
}