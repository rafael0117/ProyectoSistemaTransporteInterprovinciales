package com.proyecto.application.dto.revision;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RevisionBusRequestDto {
    private LocalDate fechaRevision;
    private String observaciones;
    private Long idBus;
    private Long idPersonal;
}
