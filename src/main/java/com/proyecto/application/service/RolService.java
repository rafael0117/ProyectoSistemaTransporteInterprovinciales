package com.proyecto.application.service;

import com.proyecto.application.dto.rol.RolRequestDto;
import com.proyecto.application.dto.rol.RolResponseDto;

import java.util.List;

public interface RolService {
    List<RolResponseDto> listar();
    RolResponseDto guardar(RolRequestDto rolRequestDto);
    void eliminar(Long id);
    RolResponseDto obtenerRol(Long id);
}
