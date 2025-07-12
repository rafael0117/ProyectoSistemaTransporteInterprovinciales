package com.proyecto.application.dto.detalleVentaPasaje;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalleVentaResponseDTO {
    private String nombreViaje;
    private int cantidad;
    private double precioUnitario;
    private double total;
}