package com.proyecto.application.service;

import com.proyecto.application.dto.Personal.PersonalRequestDto;
import com.proyecto.application.dto.Personal.PersonalResponseDto;

import java.util.List;

public interface PersonalService {
    List<PersonalResponseDto> listar();
    PersonalResponseDto guardar(PersonalRequestDto requestDto);
    void eliminar(Long id);
    PersonalResponseDto listarPorId(Long id);
}
