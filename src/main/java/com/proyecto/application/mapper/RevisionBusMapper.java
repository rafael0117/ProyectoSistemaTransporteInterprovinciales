package com.proyecto.application.mapper;

import com.proyecto.application.dto.revision.RevisionBusRequestDto;
import com.proyecto.application.dto.revision.RevisionBusResponseDto;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.RevisionBus;
import org.springframework.stereotype.Component;

@Component
public class RevisionBusMapper {
    public RevisionBusResponseDto toDto(RevisionBus revisionBus) {
        return RevisionBusResponseDto.builder()
                .revisionId(revisionBus.getRevisionId())
                .fechaRevision(revisionBus.getFechaRevision())
                .tipoRevision(revisionBus.getTipoRevision())
                .resultado(revisionBus.getResultado())
                .observaciones(revisionBus.getObservaciones())
                .modelo(revisionBus.getBus().getModelo())
                .marca(revisionBus.getBus().getMarca())
                .anio(revisionBus.getBus().getAnio())
                .capacidad(revisionBus.getBus().getCapacidad())
                .placa(revisionBus.getBus().getPlaca())
                .build();
    }

    public RevisionBus toEntity(RevisionBusRequestDto requestDto, Bus bus) {
        return RevisionBus.builder()
                .fechaRevision(requestDto.getFechaRevision())
                .tipoRevision(requestDto.getTipoRevision())
                .resultado(requestDto.getResultado())
                .observaciones(requestDto.getObservaciones())
                .bus(bus)
                .build();
    }
}
