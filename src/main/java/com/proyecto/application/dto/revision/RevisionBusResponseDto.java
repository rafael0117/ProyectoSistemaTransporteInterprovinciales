package com.proyecto.application.dto.revision;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RevisionBusResponseDto {
    private Long idRevision;
    private LocalDate fechaRevision;
    private String observaciones;

    private Long idBus;
    private String modeloBus;
    private String placaBus;

    private Long idPersonal;
    private String nombresPersonal;
    private String apellidosPersonal;
}
