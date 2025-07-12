package com.proyecto.application.dto.viaje;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ViajeResponseDTO {
    private Long idViaje;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private String incidencias;
    private double precio;
    //bus
    private String modelo;
    private String marca;
    private int anio;
    private int capacidad;
    private String placa;
    //destino
    private String nombre;
}
