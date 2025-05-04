package com.proyecto.application.mapper;

import com.proyecto.application.dto.Personal.PersonalRequestDto;
import com.proyecto.application.dto.Personal.PersonalResponseDto;
import com.proyecto.domain.entity.Personal;
import com.proyecto.domain.entity.Rol;
import org.springframework.stereotype.Component;

@Component
public class PersonalMapper {
    public PersonalResponseDto toDto(Personal personal) {
        return PersonalResponseDto.builder()
                .idPersonal(personal.getIdPersonal())
                .nombre(personal.getNombre())
                .apellido(personal.getApellido())
                .dni(personal.getDni())
                .telefono(personal.getTelefono())
                .email(personal.getEmail())
                .direccion(personal.getDireccion())
                .descripcion(personal.getRol().getName())
                .build();
    }

    public Personal toEntity(PersonalRequestDto requestDto, Rol rol) {
        return Personal.builder()
                .nombre(requestDto.getNombre())
                .apellido(requestDto.getApellido())
                .dni(requestDto.getDni())
                .telefono(requestDto.getTelefono())
                .email(requestDto.getEmail())
                .direccion(requestDto.getDireccion())
                .rol(rol)
                .build();
    }

}
