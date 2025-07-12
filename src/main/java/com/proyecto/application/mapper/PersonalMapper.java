package com.proyecto.application.mapper;


import com.proyecto.application.dto.personal.PersonalRequestDTO;
import com.proyecto.application.dto.personal.PersonalResponseDTO;
import com.proyecto.domain.entity.Personal;
import org.springframework.stereotype.Component;

@Component
public class PersonalMapper {
    public PersonalResponseDTO getDto(Personal personal) {
        return PersonalResponseDTO.builder()
                .id(personal.getId())
                .nombre(personal.getNombre())
                .apellido(personal.getApellido())
                .dni(personal.getDni())
                .cargo(personal.getCargo())
                .telefono(personal.getTelefono())
                .build();
    }

    public Personal getEntity(PersonalRequestDTO dto) {
        return Personal.builder()
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .dni(dto.getDni())
                .cargo(dto.getCargo())
                .telefono(dto.getTelefono())
                .build();
    }

    public Personal getEntityFromResponse(PersonalResponseDTO dto) {
        return Personal.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .apellido(dto.getApellido())
                .dni(dto.getDni())
                .cargo(dto.getCargo())
                .telefono(dto.getTelefono())
                .build();
    }

}
