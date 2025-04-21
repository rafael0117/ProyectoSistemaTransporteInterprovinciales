package com.proyecto.application.service;

import com.proyecto.application.dto.ViajeRequestDTO;
import com.proyecto.application.dto.ViajeResponseDTO;

import java.util.List;

public interface ViajeService {
    List<ViajeResponseDTO> listar();
    ViajeResponseDTO guardar(ViajeRequestDTO dto);
    ViajeResponseDTO editar(Long id, ViajeRequestDTO dto);
    void eliminar(Long id);


}
