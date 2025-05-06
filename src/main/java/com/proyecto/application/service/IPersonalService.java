package com.proyecto.application.service;

import com.proyecto.application.dto.personal.PersonalRequestDTO;
import com.proyecto.application.dto.personal.PersonalResponseDTO;

import java.util.List;

public interface IPersonalService {
    List<PersonalResponseDTO> listar();
    PersonalResponseDTO guardar(PersonalRequestDTO dto);
    PersonalResponseDTO editar(Long id, PersonalRequestDTO dto);
    void eliminar(Long id);
    PersonalResponseDTO obtenerPorId(Long id);
}
