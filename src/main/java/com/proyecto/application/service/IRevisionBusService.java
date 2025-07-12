package com.proyecto.application.service;

import com.proyecto.application.dto.revision.RevisionBusRequestDto;
import com.proyecto.application.dto.revision.RevisionBusResponseDto;

import java.util.List;

public interface IRevisionBusService {
    List<RevisionBusResponseDto> listar();
    RevisionBusResponseDto guardar(RevisionBusRequestDto dto);
    RevisionBusResponseDto editar(Long id, RevisionBusRequestDto dto);
    void eliminar(Long id);
    RevisionBusResponseDto obtenerPorId(Long id);
}
