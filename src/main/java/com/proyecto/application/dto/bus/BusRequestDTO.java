package com.proyecto.application.dto.bus;

import lombok.Builder;
import lombok.Data;

@Data
public class BusRequestDTO {
    private String modelo;
    private String marca;
    private int anio;
    private int capacidad;
    private String placa;

}
