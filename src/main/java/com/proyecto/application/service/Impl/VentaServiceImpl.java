package com.proyecto.application.service.Impl;


import com.proyecto.application.dto.detalleVentaPasaje.DetalleVentaRequestDTO;
import com.proyecto.application.dto.detalleVentaPasaje.DetalleVentaResponseDTO;
import com.proyecto.application.dto.ventaPasaje.VentaRequestDTO;
import com.proyecto.application.dto.ventaPasaje.VentaResponseDTO;
import com.proyecto.application.service.IVentaService;
import com.proyecto.domain.entity.DetalleVentaPasaje;
import com.proyecto.domain.entity.VentaPasaje;
import com.proyecto.domain.entity.Viaje;
import com.proyecto.domain.repository.IVentaRepository;
import com.proyecto.domain.repository.IViajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements IVentaService {
    private final IVentaRepository ventaPasajeRepository;
    private final IViajeRepository IViajeRepository;

    @Override
    public void agregarAlCarrito(VentaRequestDTO dto) {
        VentaPasaje venta = ventaPasajeRepository.findByCliente(dto.getCliente())
                .orElseGet(() -> {
                    VentaPasaje nuevaVenta = new VentaPasaje();
                    nuevaVenta.setCliente(dto.getCliente());
                    nuevaVenta.setFechaVenta(LocalDateTime.now());
                    nuevaVenta.setDetalles(new ArrayList<>());
                    return nuevaVenta;
                });

        for (DetalleVentaRequestDTO d : dto.getDetalles()) {
            Viaje viaje = IViajeRepository.findById(d.getIdViaje()).orElseThrow();

            DetalleVentaPasaje detalle = new DetalleVentaPasaje();
            detalle.setViaje(viaje);
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecio(viaje.getPrecio());
            detalle.setTotal(viaje.getPrecio() * d.getCantidad());
            detalle.setVentaPasaje(venta);

            venta.getDetalles().add(detalle);
        }

        double total = venta.getDetalles().stream().mapToDouble(DetalleVentaPasaje::getTotal).sum();
        venta.setTotal(total);

        ventaPasajeRepository.save(venta);
    }

    @Override
    public VentaResponseDTO obtenerCarrito(String cliente) {
        VentaPasaje venta = ventaPasajeRepository.findByCliente(cliente).orElseThrow();

        return VentaResponseDTO.builder()
                .cliente(venta.getCliente())
                .total(venta.getTotal())
                .detalles(venta.getDetalles().stream().map(det -> DetalleVentaResponseDTO.builder()
                        .nombreViaje(det.getViaje().getDestino().getNombre())
                        .cantidad(det.getCantidad())
                        .precioUnitario(det.getPrecio())
                        .total(det.getTotal())
                        .build()).toList())
                .build();
    }
}