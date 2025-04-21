package com.proyecto.application.mapper;

import com.proyecto.application.dto.ViajeRequestDTO;
import com.proyecto.application.dto.ViajeResponseDTO;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.Destino;
import com.proyecto.domain.entity.Viaje;
import org.springframework.stereotype.Component;

@Component
public class ViajeMapper {
    public ViajeResponseDTO getDto(Viaje viaje) {
        return ViajeResponseDTO.builder()
                .idViaje(viaje.getIdViaje())
                .nombreBus(viaje.getIdBus().getPlaca())
                .nombreDestino(viaje.getDestino().getNombre())
                .fechaSalida(viaje.getFechaSalida())
                .fechaLlegada(viaje.getFechaLlegada())
                .incidencias(viaje.getIncidencias())
                .precio(viaje.getPrecio())
                .build();
    }

    public Viaje getEntity(ViajeRequestDTO dto, Bus bus, Destino destino) {
        return Viaje.builder()
                .idBus(bus)
                .destino(destino)
                .fechaSalida(dto.getFechaSalida())
                .fechaLlegada(dto.getFechaLlegada())
                .incidencias(dto.getIncidencias())
                .precio(dto.getPrecio())
                .build();
    }
}
