package com.proyecto.application.dto.ventaPasaje;

import com.proyecto.application.dto.detalleVentaPasaje.DetalleVentaResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class VentaResponseDTO {
    private Long idVenta;
    private String cliente;
    private double total;
    private LocalDateTime fechaVenta;
    private List<DetalleVentaResponseDTO> detalles;
}