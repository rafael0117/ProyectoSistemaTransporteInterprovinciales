package com.proyecto.application.mapper;

import com.proyecto.application.dto.viaje.ViajeRequestDTO;
import com.proyecto.application.dto.viaje.ViajeResponseDTO;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.Destino;
import com.proyecto.domain.entity.Viaje;
import org.springframework.stereotype.Component;

@Component
public class ViajeMapper {
    public ViajeResponseDTO getDto(Viaje viaje) {
        return ViajeResponseDTO.builder()
                //viaje
                .idViaje(viaje.getIdViaje())
                .fechaSalida(viaje.getFechaSalida())
                .fechaLlegada(viaje.getFechaLlegada())
                .incidencias(viaje.getIncidencias())
                .precio(viaje.getPrecio())
                //bus

                .modelo(viaje.getIdBus().getModelo())
                .placa(viaje.getIdBus().getPlaca())
                .marca(viaje.getIdBus().getMarca())
                .anio(viaje.getIdBus().getAnio())
                .capacidad(viaje.getIdBus().getCapacidad())
                //destino
                .nombre(viaje.getDestino().getNombre())


                .build();
    }

    public Viaje getEntity(ViajeRequestDTO dto, Bus bus, Destino destino) {

        return Viaje.builder()

                .fechaSalida(dto.getFechaSalida())
                .fechaLlegada(dto.getFechaLlegada())
                .incidencias(dto.getIncidencias())
                .precio(dto.getPrecio())
                .idBus(bus)
                .destino(destino)
                .build();
    }
}
