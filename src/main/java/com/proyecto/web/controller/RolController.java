package com.proyecto.web.controller;


import com.proyecto.application.dto.rol.RolRequestDto;
import com.proyecto.application.dto.rol.RolResponseDto;
import com.proyecto.application.service.RolService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rol")
public class RolController {
    private final RolService service;

    @GetMapping
    public List<RolResponseDto> listar() {return service.listar();}

    @PostMapping
    public RolResponseDto guardar(@RequestBody RolRequestDto rolRequestDto) {return service.guardar(rolRequestDto);}

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {service.eliminar(id);}
}
