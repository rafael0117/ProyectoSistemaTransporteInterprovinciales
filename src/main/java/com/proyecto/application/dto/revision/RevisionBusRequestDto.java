package com.proyecto.application.dto.revision;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RevisionBusRequestDto {
    private LocalDate fechaRevision;
    private String tipoRevision;
    private String resultado;
    private String observaciones;
    private Long idBus;
}
