package com.proyecto.application.service.Impl;

import com.proyecto.application.dto.bus.BusResponseDTO;
import com.proyecto.application.dto.destino.DestinoResponseDTO;
import com.proyecto.application.dto.viaje.ViajeRequestDTO;
import com.proyecto.application.dto.viaje.ViajeResponseDTO;
import com.proyecto.application.mapper.BusMapper;
import com.proyecto.application.mapper.DestinoMapper;
import com.proyecto.application.service.BusService;
import com.proyecto.application.service.DestinoService;
import com.proyecto.application.service.ViajeService;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.Destino;
import com.proyecto.domain.entity.Viaje;
import com.proyecto.application.mapper.ViajeMapper;
import com.proyecto.domain.repository.ViajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViajeServiceImpl implements ViajeService {

    private final ViajeRepository viajerepository;
    private final ViajeMapper viajeMapper;
    private final BusService busService;
    private final BusMapper busMapper;
    private final DestinoService destinoService;
    private final DestinoMapper destinoMapper;

    @Override
    public List<ViajeResponseDTO> listar() {
        return viajerepository.findAll().stream()
                .map(viajeMapper::getDto)
                .toList();
    }

    @Override
    public ViajeResponseDTO guardar(ViajeRequestDTO dto) {
        BusResponseDTO busResponseDTO = busService.obtenerBus(dto.getIdBus());
        Bus bus = busMapper.getEntityBus(busResponseDTO);
        DestinoResponseDTO destinoResponseDTO=destinoService.obtenerDestino(dto.getIdDestino());
        Destino destino=destinoMapper.getEntityDestino(destinoResponseDTO);
        Viaje viaje = viajeMapper.getEntity(dto,bus,destino);
        return viajeMapper.getDto(viajerepository.save(viaje));

    }

    @Override
    public ViajeResponseDTO editar(Long id, ViajeRequestDTO dto) {
        Viaje viajeExistente = viajerepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con ID: " + id));

        BusResponseDTO busResponseDTO = busService.obtenerBus(dto.getIdBus());
        Bus bus = busMapper.getEntityBus(busResponseDTO);

        DestinoResponseDTO destinoResponseDTO = destinoService.obtenerDestino(dto.getIdDestino());
        Destino destino = destinoMapper.getEntityDestino(destinoResponseDTO);

        Viaje viajeActualizado = viajeMapper.getEntity(dto, bus, destino);
        viajeActualizado.setIdViaje(viajeExistente.getIdViaje());

        Viaje viajeGuardado = viajerepository.save(viajeActualizado);
        return viajeMapper.getDto(viajeGuardado);
    }


    @Override
    public void eliminar(Long id) {
        if (!viajerepository.existsById(id)) {
            throw new RuntimeException("Viaje no encontrado con ID: " + id);
        }
        viajerepository.deleteById(id);
    }

}

