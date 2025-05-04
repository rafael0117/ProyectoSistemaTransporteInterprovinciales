package com.proyecto.application.dto.detalleVentaPasaje;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalleVentaResponseDTO {
    private Long idDetalle;
    private Long idViaje;
    private double precio;
    private int cantidad;
    private double total;
    private int cantidadDisponible;
}