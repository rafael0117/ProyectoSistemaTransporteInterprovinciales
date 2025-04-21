package com.proyecto.application.service;

import com.proyecto.application.dto.BusRequestDTO;
import com.proyecto.application.dto.BusResponseDTO;

import java.util.List;

public interface BusService {
    List<BusResponseDTO> listar();
    BusResponseDTO guardar(BusRequestDTO dto);
    BusResponseDTO editar(Long id, BusRequestDTO dto);
    void eliminar(Long id);
}
