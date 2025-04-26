package com.proyecto.application.service.Impl;

import com.proyecto.application.dto.Personal.PersonalRequestDto;
import com.proyecto.application.dto.Personal.PersonalResponseDto;
import com.proyecto.application.dto.rol.RolResponseDto;
import com.proyecto.application.mapper.PersonalMapper;
import com.proyecto.application.mapper.RolMapper;
import com.proyecto.application.service.PersonalService;
import com.proyecto.application.service.RolService;
import com.proyecto.domain.entity.Personal;
import com.proyecto.domain.entity.Rol;
import com.proyecto.domain.repository.PersonalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalServiceImpl implements PersonalService {
    private final PersonalRepository repository;
    private final PersonalMapper personalMapper;
    private final RolService rolService;
    private final RolMapper rolMapper;
    @Override
    public List<PersonalResponseDto> listar() {
        return repository.findAll().stream()
                .map(personalMapper::toDto)
                .toList();
    }

    @Override
    public PersonalResponseDto guardar(PersonalRequestDto requestDto) {
        RolResponseDto rolResponseDto = rolService.obtenerRol(requestDto.getIdRol());
        Rol rol = rolMapper.toEntityRol(rolResponseDto);
        Personal personal = personalMapper.toEntity(requestDto,rol);
        return personalMapper.toDto(repository.save(personal));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("no existe el personal con ID : " + id);
        }
        repository.deleteById(id);
    }


    @Override
    public PersonalResponseDto listarPorId(Long id) {
        return repository.findById(id)
                .map(personalMapper::toDto)
                .orElseThrow(() -> new RuntimeException("no hay reservas con el ID : " + id));
    }
}
