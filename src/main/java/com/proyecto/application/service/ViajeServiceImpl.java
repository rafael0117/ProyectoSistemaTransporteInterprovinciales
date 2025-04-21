package com.proyecto.application.service;

import com.proyecto.application.dto.ViajeRequestDTO;
import com.proyecto.application.dto.ViajeResponseDTO;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.Destino;
import com.proyecto.domain.entity.Viaje;
import com.proyecto.application.mapper.ViajeMapper;
import com.proyecto.domain.repository.BusRepository;
import com.proyecto.domain.repository.DestinoRepository;
import com.proyecto.domain.repository.ViajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViajeServiceImpl implements ViajeService {

    private final ViajeRepository repository;
    private final ViajeMapper mapper;
    private final BusRepository busRepository;
    private final DestinoRepository destinoRepository;

    @Override
    public List<ViajeResponseDTO> listar() {
        return repository.findAll().stream()
                .map(mapper::getDto)
                .toList();
    }

    @Override
    public ViajeResponseDTO guardar(ViajeRequestDTO dto) {
        Bus bus = busRepository.findById(dto.getIdBus())
                .orElseThrow(() -> new RuntimeException("Bus no encontrado"));
        Destino destino = destinoRepository.findById(dto.getIdDestino())
                .orElseThrow(() -> new RuntimeException("Destino no encontrado"));

        Viaje viaje = mapper.getEntity(dto, bus, destino);
        Viaje nuevo = repository.save(viaje);
        return mapper.getDto(nuevo);
    }

    @Override
    public ViajeResponseDTO editar(Long id, ViajeRequestDTO dto) {
        Viaje viajeExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con ID: " + id));

        Bus bus = busRepository.findById(dto.getIdBus())
                .orElseThrow(() -> new RuntimeException("Bus no encontrado"));
        Destino destino = destinoRepository.findById(dto.getIdDestino())
                .orElseThrow(() -> new RuntimeException("Destino no encontrado"));

        Viaje viajeActualizado = mapper.getEntity(dto, bus, destino);
        viajeActualizado.setIdViaje(viajeExistente.getIdViaje());

        Viaje viajeGuardado = repository.save(viajeActualizado);
        return mapper.getDto(viajeGuardado);
    }

    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Viaje no encontrado con ID: " + id);
        }
        repository.deleteById(id);
    }
}

