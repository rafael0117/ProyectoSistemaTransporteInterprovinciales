package com.proyecto.application.service.Impl;


import com.proyecto.application.dto.detalleVentaPasaje.DetalleVentaRequestDTO;
import com.proyecto.application.dto.ventaPasaje.VentaRequestDTO;
import com.proyecto.application.dto.ventaPasaje.VentaResponseDTO;
import com.proyecto.application.mapper.VentaMapper;
import com.proyecto.application.service.VentaService;
import com.proyecto.domain.entity.DetalleVentaPasaje;
import com.proyecto.domain.entity.VentaPasaje;
import com.proyecto.domain.entity.Viaje;
import com.proyecto.domain.repository.IVentaRepository;
import com.proyecto.domain.repository.ViajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final IVentaRepository ventaRepository;
    private final ViajeRepository viajeRepository;
    private final VentaMapper mapper;

    @Override
    public VentaResponseDTO registrar(VentaRequestDTO dto) {
        return agregarAlCarrito(dto); // misma lógica
    }

    @Override
    public List<VentaResponseDTO> listar() {
        return ventaRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public VentaResponseDTO obtenerPorId(Long id) {
        return ventaRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con ID: " + id));
    }

    @Override
    public VentaResponseDTO agregarAlCarrito(VentaRequestDTO dto) {
        VentaPasaje venta = mapper.toEntity(dto);
        List<DetalleVentaPasaje> detalles = new ArrayList<>();
        double total = 0.0;

        for (DetalleVentaRequestDTO detalleDTO : dto.getDetalles()) {
            Viaje viaje = viajeRepository.findById(detalleDTO.getIdViaje())
                    .orElseThrow(() -> new RuntimeException("Viaje no encontrado con ID: " + detalleDTO.getIdViaje()));

            if (detalleDTO.getCantidad() > viaje.getCantidadDisponible()) {
                throw new RuntimeException("Cantidad solicitada excede disponibilidad para viaje " + detalleDTO.getIdViaje());
            }

            double precio = viaje.getPrecio();
            int cantidad = detalleDTO.getCantidad();

            DetalleVentaPasaje detalle = new DetalleVentaPasaje();
            detalle.setViaje(viaje);
            detalle.setCantidad(cantidad);
            detalle.setPrecio(precio);
            detalle.setTotal(precio * cantidad);
            detalle.setVentaPasaje(venta);

            detalles.add(detalle);
            total += detalle.getTotal();

            viaje.setCantidadDisponible(viaje.getCantidadDisponible() - cantidad);
            viajeRepository.save(viaje);
        }

        venta.setDetalles(detalles);
        venta.setFechaVenta(LocalDateTime.now());
        venta.setTotal(total);

        VentaPasaje guardada = ventaRepository.save(venta);
        return mapper.toDto(guardada);
    }

}