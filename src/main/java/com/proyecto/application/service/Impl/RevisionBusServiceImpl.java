package com.proyecto.application.service.Impl;

import com.proyecto.application.dto.bus.BusResponseDTO;
import com.proyecto.application.dto.personal.PersonalResponseDTO;
import com.proyecto.application.dto.revision.RevisionBusRequestDto;
import com.proyecto.application.dto.revision.RevisionBusResponseDto;
import com.proyecto.application.mapper.BusMapper;
import com.proyecto.application.mapper.PersonalMapper;
import com.proyecto.application.mapper.RevisionBusMapper;
import com.proyecto.application.service.IBusService;
import com.proyecto.application.service.IPersonalService;
import com.proyecto.application.service.IRevisionBusService;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.Personal;
import com.proyecto.domain.entity.RevisionBus;
import com.proyecto.domain.repository.IRevisionBusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RevisionBusServiceImpl implements IRevisionBusService {
    private final IRevisionBusRepository repository;
    private final IBusService busService;
    private final IPersonalService personalService;
    private final BusMapper busMapper;
    private final PersonalMapper personalMapper;
    private final RevisionBusMapper mapper;

    @Override
    public List<RevisionBusResponseDto> listar() {
        return repository.findAll().stream()
                .map(mapper::getDto)
                .toList();
    }

    @Override
    public RevisionBusResponseDto guardar(RevisionBusRequestDto dto) {
        Bus bus = busMapper.getEntityBus(busService.obtenerBus(dto.getIdBus()));
        Personal personal = personalMapper.getEntityFromResponse(personalService.obtenerPorId(dto.getIdPersonal()));
        RevisionBus revision = mapper.getEntity(dto, bus, personal);
        return mapper.getDto(repository.save(revision));
    }

    @Override
    public RevisionBusResponseDto editar(Long id, RevisionBusRequestDto dto) {
        RevisionBus revisionExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Revisión no encontrada con ID: " + id));

        Bus bus = busMapper.getEntityBus(busService.obtenerBus(dto.getIdBus()));
        Personal personal = personalMapper.getEntityFromResponse(personalService.obtenerPorId(dto.getIdPersonal()));
        RevisionBus actualizada = mapper.getEntity(dto, bus, personal);
        actualizada.setIdRevision(revisionExistente.getIdRevision());
        return mapper.getDto(repository.save(actualizada));
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe una revisión con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public RevisionBusResponseDto obtenerPorId(Long id) {
        return repository.findById(id)
                .map(mapper::getDto)
                .orElseThrow(() -> new RuntimeException("Revisión no encontrada con ID: " + id));
    }
}
