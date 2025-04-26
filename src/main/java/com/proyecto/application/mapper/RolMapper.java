package com.proyecto.application.mapper;

import com.proyecto.application.dto.rol.RolRequestDto;
import com.proyecto.application.dto.rol.RolResponseDto;
import com.proyecto.domain.entity.Rol;
import org.springframework.stereotype.Component;

@Component
public class RolMapper {
    public RolResponseDto dto(Rol rol) {
        return RolResponseDto.builder()
                .idRol(rol.getIdrol())
                .descripcion(rol.getDescripcion())
                .build();
    }

    public Rol toEntity(RolRequestDto rolRequestDto) {
        return Rol.builder()
                .descripcion(rolRequestDto.getDescripcion())
                .build();
    }

    public Rol toEntityRol(RolResponseDto rolResponseDto) {
        return Rol.builder()
                .idrol(rolResponseDto.getIdRol())
                .descripcion(rolResponseDto.getDescripcion())
                .build();
    }
}
