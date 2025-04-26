package com.proyecto.application.service.Impl;

import com.proyecto.application.dto.bus.BusResponseDTO;
import com.proyecto.application.dto.revision.RevisionBusRequestDto;
import com.proyecto.application.dto.revision.RevisionBusResponseDto;
import com.proyecto.application.mapper.BusMapper;
import com.proyecto.application.mapper.RevisionBusMapper;
import com.proyecto.application.service.BusService;
import com.proyecto.application.service.RevisionBusService;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.RevisionBus;
import com.proyecto.domain.repository.RevisionBusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RevisionBusServiceImpl implements RevisionBusService {
    private final RevisionBusRepository repository;
    private final RevisionBusMapper revisionBusMapper;
    private final BusService busService;
    private final BusMapper busMapper;

    @Override
    public List<RevisionBusResponseDto> listar() {
        return repository.findAll().stream()
                .map(revisionBusMapper::toDto)
                .toList();
    }

    @Override
    public RevisionBusResponseDto guardar(RevisionBusRequestDto requestDto) {
        BusResponseDTO busResponseDTO = busService.obtenerBus(requestDto.getIdBus());
        Bus bus = busMapper.getEntityBus(busResponseDTO);
        RevisionBus revisionBus = revisionBusMapper.toEntity(requestDto,bus);
        return revisionBusMapper.toDto(repository.save(revisionBus));
    }


    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe la revision con el ID : " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public RevisionBusResponseDto listarPorId(Long id) {
        return repository.findById(id)
                .map(revisionBusMapper::toDto)
                .orElseThrow(() -> new RuntimeException("mo hay revisiones con el ID : " + id));
    }
}
