package com.proyecto.application.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ViajeResponseDTO {
    private Long idViaje;
    private String nombreBus;
    private String nombreDestino;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private String incidencias;
    private double precio;
}
