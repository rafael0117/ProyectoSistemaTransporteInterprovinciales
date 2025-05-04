package com.proyecto.application.mapper;

import com.proyecto.application.dto.detalleVentaPasaje.DetalleVentaResponseDTO;
import com.proyecto.application.dto.ventaPasaje.VentaRequestDTO;
import com.proyecto.application.dto.ventaPasaje.VentaResponseDTO;
import com.proyecto.domain.entity.DetalleVentaPasaje;
import com.proyecto.domain.entity.VentaPasaje;
import com.proyecto.domain.entity.Viaje;
import com.proyecto.domain.repository.ViajeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VentaMapper {

    public VentaPasaje toEntity(VentaRequestDTO dto) {
        VentaPasaje venta = new VentaPasaje();
        venta.setCliente(dto.getCliente());
        venta.setDetalles(new ArrayList<>()); // Detalles se setean en el service
        return venta;
    }

    public VentaResponseDTO toDto(VentaPasaje venta) {
        return VentaResponseDTO.builder()
                .idVenta(venta.getIdVenta())
                .cliente(venta.getCliente())
                .fechaVenta(venta.getFechaVenta())
                .total(venta.getTotal())
                .detalles(venta.getDetalles().stream().map(detalle ->
                        DetalleVentaResponseDTO.builder()
                                .idDetalle(detalle.getIdDetalle())
                                .idViaje(detalle.getViaje().getIdViaje())
                                .precio(detalle.getPrecio())
                                .cantidad(detalle.getCantidad())
                                .total(detalle.getTotal())
                                .cantidadDisponible(detalle.getViaje().getCantidadDisponible())
                                .build()
                ).collect(Collectors.toList()))
                .build();
    }

}