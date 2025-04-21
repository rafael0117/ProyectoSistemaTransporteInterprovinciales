package com.proyecto.application.service;

import com.proyecto.application.dto.DestinoRequestDTO;
import com.proyecto.application.dto.DestinoResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DestinoService {
    List<DestinoResponseDTO> listar();
    DestinoResponseDTO guardarConImagen(String nombre, MultipartFile imagen);
    DestinoResponseDTO editarConImagen(Long id, String nombre, MultipartFile imagen);

    void eliminar(Long id);
}
