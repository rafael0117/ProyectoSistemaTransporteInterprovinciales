package com.proyecto.web.controller;

import com.proyecto.application.dto.ViajeRequestDTO;
import com.proyecto.application.dto.ViajeResponseDTO;
import com.proyecto.application.service.ViajeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viaje")
@RequiredArgsConstructor
public class ViajeController {

    private final ViajeService service;

    @GetMapping("/listar")
    public List<ViajeResponseDTO> listar() {
        return service.listar();
    }

    @PostMapping("/guardar")
    public ViajeResponseDTO guardar(@RequestBody ViajeRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/editar/{id}")
    public ViajeResponseDTO editar(@PathVariable Long id, @RequestBody ViajeRequestDTO dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
