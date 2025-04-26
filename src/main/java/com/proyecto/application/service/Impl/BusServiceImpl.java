package com.proyecto.application.service.Impl;

import com.proyecto.application.dto.bus.BusRequestDTO;
import com.proyecto.application.dto.bus.BusResponseDTO;
import com.proyecto.application.service.BusService;
import com.proyecto.domain.entity.Bus;
import com.proyecto.application.mapper.BusMapper;
import com.proyecto.domain.repository.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService
{
    private final BusRepository repository;
    private final BusMapper mapper;
    @Override
    public List<BusResponseDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::getDto).toList();
    }

    @Override
    public BusResponseDTO guardar(BusRequestDTO dto) {
        Bus bus = mapper.getEntity(dto);
        Bus busNueva = repository.save(bus);
        return mapper.getDto(busNueva);
    }

    @Override
    public BusResponseDTO editar(Long id, BusRequestDTO dto) {
        Bus busExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus no encontrado con ID: " + id));
        Bus busActualizado = mapper.getEntity(dto);
        busActualizado.setIdBus(busExistente.getIdBus());
        Bus busGuardado = repository.save(busActualizado);
        return mapper.getDto(busGuardado);
    }


    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe un bus con ID: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public BusResponseDTO obtenerBus(Long id) {
        return repository.findById(id)
                .map(mapper::getDto)
                .orElseThrow(() -> new RuntimeException("No existe el Bus con ID : " + id));
    }
}
