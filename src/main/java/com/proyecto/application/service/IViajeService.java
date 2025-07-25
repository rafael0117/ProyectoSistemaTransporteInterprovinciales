package com.proyecto.application.service;

import com.proyecto.application.dto.viaje.ViajeRequestDTO;
import com.proyecto.application.dto.viaje.ViajeResponseDTO;

import java.util.List;

public interface IViajeService {
    List<ViajeResponseDTO> listar();
    ViajeResponseDTO guardar(ViajeRequestDTO dto);
    ViajeResponseDTO editar(Long id, ViajeRequestDTO dto);
    void eliminar(Long id);
    List<ViajeResponseDTO> listarViajesPorDestino(Long idDestino);


}
