package com.proyecto.application.service.Impl;

import com.proyecto.application.dto.rol.RolRequestDto;
import com.proyecto.application.dto.rol.RolResponseDto;
import com.proyecto.application.mapper.RolMapper;
import com.proyecto.application.service.RolService;
import com.proyecto.domain.entity.Rol;
import com.proyecto.domain.repository.RolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolRepository repository;
    private final RolMapper mapper;
    @Override
    public List<RolResponseDto> listar() {
        return repository.findAll().stream()
                .map(mapper::dto)
                .toList();
    }

    @Override
    public RolResponseDto guardar(RolRequestDto rolRequestDto) {
        Rol rol = mapper.toEntity(rolRequestDto);
        return mapper.dto(repository.save(rol));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)){
            throw new RuntimeException("no existe el rol con ID : " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public RolResponseDto obtenerRol(Long id) {
        return repository.findById(id)
                .map(mapper::dto)
                .orElseThrow(() -> new RuntimeException("no existe el rol con ID : " + id));
    }
}
