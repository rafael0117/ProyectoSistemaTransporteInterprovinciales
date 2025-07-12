package com.proyecto.application.dto.bus;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusResponseDTO {
    private Long idBus;
    private String modelo;
    private String marca;
    private int anio;
    private int capacidad;
    private String placa;
}
