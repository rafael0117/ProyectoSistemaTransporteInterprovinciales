package com.proyecto.application.mapper;

import com.proyecto.application.dto.bus.BusRequestDTO;
import com.proyecto.application.dto.bus.BusResponseDTO;
import com.proyecto.domain.entity.Bus;
import org.springframework.stereotype.Component;

@Component
public class BusMapper {
    public BusResponseDTO getDto(Bus bus){
        return BusResponseDTO.builder()
                .idBus(bus.getIdBus())
                .modelo(bus.getModelo())
                .marca(bus.getMarca())
                .anio(bus.getAnio())
                .capacidad(bus.getCapacidad())
                .placa(bus.getPlaca())
                .build();
    }
    public Bus getEntity(BusRequestDTO dto){
        return Bus.builder()
                .modelo(dto.getModelo())
                .marca(dto.getMarca())
                .anio(dto.getAnio())
                .capacidad(dto.getCapacidad())
                .placa(dto.getPlaca())
                .build();
    }
    public Bus getEntityBus(BusResponseDTO busResponseDTO){
        return Bus.builder()
                .idBus(busResponseDTO.getIdBus())
                .modelo(busResponseDTO.getModelo())
                .marca(busResponseDTO.getMarca())
                .anio(busResponseDTO.getAnio())
                .capacidad(busResponseDTO.getCapacidad())
                .placa(busResponseDTO.getPlaca())
                .build();
    }
}
