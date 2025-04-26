package com.proyecto.application.service;

import com.proyecto.application.dto.bus.BusRequestDTO;
import com.proyecto.application.dto.bus.BusResponseDTO;

import java.util.List;

public interface BusService {
    List<BusResponseDTO> listar();
    BusResponseDTO guardar(BusRequestDTO dto);
    BusResponseDTO editar(Long id, BusRequestDTO dto);
    void eliminar(Long id);
    BusResponseDTO obtenerBus(Long id);
}
