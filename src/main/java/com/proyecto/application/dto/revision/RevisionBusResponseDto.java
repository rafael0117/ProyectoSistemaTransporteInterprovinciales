package com.proyecto.application.dto.revision;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RevisionBusResponseDto {
    private Long revisionId;
    private LocalDate fechaRevision;
    private String tipoRevision;
    private String resultado;
    private String observaciones;
    private String modelo;
    private String marca;
    private int anio;
    private int capacidad;
    private String placa;
}
