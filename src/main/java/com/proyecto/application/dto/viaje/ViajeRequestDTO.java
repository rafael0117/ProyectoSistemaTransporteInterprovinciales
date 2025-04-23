package com.proyecto.application.dto.viaje;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ViajeRequestDTO {
    private Long idBus;
    private Long idDestino;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private String incidencias;
    private double precio;
}
