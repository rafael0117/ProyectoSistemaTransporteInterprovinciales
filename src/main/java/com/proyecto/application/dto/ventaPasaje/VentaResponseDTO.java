package com.proyecto.application.dto.ventaPasaje;

import com.proyecto.application.dto.detalleVentaPasaje.DetalleVentaResponseDTO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.DoubleStream;

@Data
@Builder
public class VentaResponseDTO {
    private String cliente;
    private double total;
    private List<DetalleVentaResponseDTO> detalles;

}