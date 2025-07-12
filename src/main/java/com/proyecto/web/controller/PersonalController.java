package com.proyecto.web.controller;

import com.proyecto.application.dto.personal.PersonalRequestDTO;
import com.proyecto.application.dto.personal.PersonalResponseDTO;
import com.proyecto.application.service.IPersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal")
@RequiredArgsConstructor
public class PersonalController {

    private final IPersonalService service;

    @GetMapping("/listar")
    public List<PersonalResponseDTO> listar() {
        return service.listar();
    }

    @PostMapping("/guardar")
    public PersonalResponseDTO guardar(@RequestBody PersonalRequestDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/editar/{id}")
    public PersonalResponseDTO editar(@PathVariable Long id, @RequestBody PersonalRequestDTO dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/{id}")
    public PersonalResponseDTO obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}
