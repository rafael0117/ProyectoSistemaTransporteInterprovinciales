package com.proyecto.application.service;

import com.proyecto.application.dto.ventaPasaje.VentaRequestDTO;
import com.proyecto.application.dto.ventaPasaje.VentaResponseDTO;

import java.util.List;

public interface VentaService {
    VentaResponseDTO registrar(VentaRequestDTO dto);
    List<VentaResponseDTO> listar();
    VentaResponseDTO obtenerPorId(Long id);
    VentaResponseDTO agregarAlCarrito(VentaRequestDTO dto);
}
