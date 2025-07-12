package com.proyecto.application.mapper;

import com.proyecto.application.dto.destino.DestinoRequestDTO;
import com.proyecto.application.dto.destino.DestinoResponseDTO;
import com.proyecto.domain.entity.Destino;
import org.springframework.stereotype.Component;

@Component
public class DestinoMapper {
    public DestinoResponseDTO getDto(Destino destino){
        return DestinoResponseDTO.builder()
                .idDestino(destino.getIdDestino())
                .nombre(destino.getNombre())
                .imagen(destino.getImagen())
                .build();
    }


    public Destino getEntity(DestinoRequestDTO dto){
        return Destino.builder()
                .nombre(dto.getNombre())
                .imagen(dto.getImagen())
                .build();
    }
    public Destino getEntityDestino(DestinoResponseDTO destinoResponseDTO){
        return Destino.builder()
                .idDestino(destinoResponseDTO.getIdDestino())
                .nombre(destinoResponseDTO.getNombre())
                .build();
    }

}
