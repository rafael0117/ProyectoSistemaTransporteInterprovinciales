package com.proyecto.application.service.Impl;

import com.proyecto.application.dto.bus.BusResponseDTO;
import com.proyecto.application.dto.destino.DestinoResponseDTO;
import com.proyecto.application.dto.viaje.ViajeRequestDTO;
import com.proyecto.application.dto.viaje.ViajeResponseDTO;
import com.proyecto.application.mapper.BusMapper;
import com.proyecto.application.mapper.DestinoMapper;
import com.proyecto.application.service.IBusService;
import com.proyecto.application.service.IDestinoService;
import com.proyecto.application.service.IViajeService;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.Destino;
import com.proyecto.domain.entity.Viaje;
import com.proyecto.application.mapper.ViajeMapper;
import com.proyecto.domain.repository.IViajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViajeServiceImpl implements IViajeService {

    private final IViajeRepository viajerepository;
    private final ViajeMapper viajeMapper;
    private final IBusService IBusService;
    private final BusMapper busMapper;
    private final IDestinoService IDestinoService;
    private final DestinoMapper destinoMapper;

    @Override
    public List<ViajeResponseDTO> listar() {
        return viajerepository.findAll().stream()
                .map(viajeMapper::getDto)
                .toList();
    }

    @Override
    public ViajeResponseDTO guardar(ViajeRequestDTO dto) {
        BusResponseDTO busResponseDTO = IBusService.obtenerBus(dto.getIdBus());
        Bus bus = busMapper.getEntityBus(busResponseDTO);
        DestinoResponseDTO destinoResponseDTO= IDestinoService.obtenerDestino(dto.getIdDestino());
        Destino destino=destinoMapper.getEntityDestino(destinoResponseDTO);
        Viaje viaje = viajeMapper.getEntity(dto,bus,destino);
        return viajeMapper.getDto(viajerepository.save(viaje));

    }

    @Override
    public ViajeResponseDTO editar(Long id, ViajeRequestDTO dto) {
        Viaje viajeExistente = viajerepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado con ID: " + id));

        BusResponseDTO busResponseDTO = IBusService.obtenerBus(dto.getIdBus());//obtiene el id del bus
        Bus bus = busMapper.getEntityBus(busResponseDTO);//convierte el bus en entidad

        DestinoResponseDTO destinoResponseDTO = IDestinoService.obtenerDestino(dto.getIdDestino());//obtiene el id del bus
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
    @Override
    public List<ViajeResponseDTO> listarViajesPorDestino(Long idDestino) {
        return viajerepository.findByDestinoIdDestino(idDestino).stream()
                .map(viajeMapper::getDto)
                .collect(Collectors.toList());
    }
}

