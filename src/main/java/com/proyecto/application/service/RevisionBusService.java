package com.proyecto.application.service;

import com.proyecto.application.dto.bus.BusRequestDTO;
import com.proyecto.application.dto.bus.BusResponseDTO;
import com.proyecto.application.dto.revision.RevisionBusRequestDto;
import com.proyecto.application.dto.revision.RevisionBusResponseDto;

import java.util.List;

public interface RevisionBusService {
    List<RevisionBusResponseDto> listar();
    RevisionBusResponseDto guardar(RevisionBusRequestDto requestDto);
    void eliminar(Long id);
    RevisionBusResponseDto listarPorId(Long id);
}
