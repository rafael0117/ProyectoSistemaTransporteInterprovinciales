package com.proyecto.application.service.Impl;

import com.proyecto.application.dto.personal.PersonalRequestDTO;
import com.proyecto.application.dto.personal.PersonalResponseDTO;
import com.proyecto.application.mapper.PersonalMapper;
import com.proyecto.application.service.IPersonalService;
import com.proyecto.domain.entity.Personal;
import com.proyecto.domain.repository.IPersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalServiceImpl implements IPersonalService {
    private final IPersonalRepository repository;
    private final PersonalMapper mapper;

    @Override
    public List<PersonalResponseDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::getDto)
                .toList();
    }

    @Override
    public PersonalResponseDTO guardar(PersonalRequestDTO dto) {
        Personal personal = mapper.getEntity(dto);
        Personal nuevo = repository.save(personal);
        return mapper.getDto(nuevo);
    }

    @Override
    public PersonalResponseDTO editar(Long id, PersonalRequestDTO dto) {
        Personal existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado con ID: " + id));
        Personal actualizado = mapper.getEntity(dto);
        actualizado.setId(existente.getId());
        Personal guardado = repository.save(actualizado);
        return mapper.getDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe un personal con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public PersonalResponseDTO obtenerPorId(Long id) {
        return repository.findById(id)
                .map(mapper::getDto)
                .orElseThrow(() -> new RuntimeException("No existe el personal con ID: " + id));
    }
}
