package com.proyecto.application.mapper;

import com.proyecto.application.dto.revision.RevisionBusRequestDto;
import com.proyecto.application.dto.revision.RevisionBusResponseDto;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.Personal;
import com.proyecto.domain.entity.RevisionBus;
import org.springframework.stereotype.Component;

@Component
public class RevisionBusMapper {
    // Convierte el DTO de solicitud en una entidad RevisionBus
    public RevisionBusResponseDto getDto(RevisionBus revision) {
        return RevisionBusResponseDto.builder()
                .idRevision(revision.getIdRevision())
                .fechaRevision(revision.getFechaRevision())
                .observaciones(revision.getObservaciones())
                .idBus(revision.getBus().getIdBus())
                .modeloBus(revision.getBus().getModelo())
                .placaBus(revision.getBus().getPlaca())
                .idPersonal(revision.getPersonal().getId())
                .nombresPersonal(revision.getPersonal().getNombre())
                .apellidosPersonal(revision.getPersonal().getApellido())
                .build();
    }

    public RevisionBus getEntity(RevisionBusRequestDto dto, Bus bus, Personal personal) {
        return RevisionBus.builder()
                .fechaRevision(dto.getFechaRevision())
                .observaciones(dto.getObservaciones())
                .bus(bus)
                .personal(personal)
                .build();
    }
}
