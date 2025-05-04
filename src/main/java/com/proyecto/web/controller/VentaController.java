package com.proyecto.web.controller;

import com.proyecto.application.dto.ventaPasaje.VentaRequestDTO;
import com.proyecto.application.dto.ventaPasaje.VentaResponseDTO;
import com.proyecto.application.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService ventaService;
    @PostMapping("/carrito")
    public VentaResponseDTO agregarAlCarrito(@RequestBody VentaRequestDTO dto) {
        return ventaService.agregarAlCarrito(dto);
    }

    @GetMapping
    public List<VentaResponseDTO> listarVentas() {
        return ventaService.listar();
    }

    @GetMapping("/{id}")
    public VentaResponseDTO obtenerVenta(@PathVariable Long id) {
        return ventaService.obtenerPorId(id);
    }
}