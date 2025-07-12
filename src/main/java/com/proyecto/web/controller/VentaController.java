package com.proyecto.web.controller;

import com.proyecto.application.dto.ventaPasaje.VentaRequestDTO;
import com.proyecto.application.dto.ventaPasaje.VentaResponseDTO;
import com.proyecto.application.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
public class VentaController {
    private final IVentaService ventaPasajeService;


    @PostMapping("/agregar")
    public void agregarAlCarrito(@RequestBody VentaRequestDTO dto) {
        ventaPasajeService.agregarAlCarrito(dto);
    }

    @GetMapping("/{cliente}")
    public VentaResponseDTO obtenerCarrito(@PathVariable String cliente) {
        return ventaPasajeService.obtenerCarrito(cliente);
    }
}