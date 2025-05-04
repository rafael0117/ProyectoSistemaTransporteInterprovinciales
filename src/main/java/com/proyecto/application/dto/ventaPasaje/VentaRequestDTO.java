package com.proyecto.application.dto.ventaPasaje;

import com.proyecto.application.dto.detalleVentaPasaje.DetalleVentaRequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class VentaRequestDTO {
    private String cliente;
    private List<DetalleVentaRequestDTO> detalles;
}