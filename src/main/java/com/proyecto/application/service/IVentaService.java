package com.proyecto.application.service;

import com.proyecto.application.dto.ventaPasaje.VentaRequestDTO;
import com.proyecto.application.dto.ventaPasaje.VentaResponseDTO;

public interface IVentaService {
    void agregarAlCarrito(VentaRequestDTO dto);
    VentaResponseDTO obtenerCarrito(String cliente);
}
