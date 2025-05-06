package com.proyecto.application.dto.detalleVentaPasaje;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetalleVentaRequestDTO {
    private Long idViaje;
    private int cantidad;
}