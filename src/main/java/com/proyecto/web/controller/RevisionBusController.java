package com.proyecto.web.controller;

import com.proyecto.application.dto.revision.RevisionBusRequestDto;
import com.proyecto.application.dto.revision.RevisionBusResponseDto;
import com.proyecto.application.mapper.BusMapper;
import com.proyecto.application.mapper.PersonalMapper;
import com.proyecto.application.mapper.RevisionBusMapper;
import com.proyecto.application.service.IBusService;
import com.proyecto.application.service.IPersonalService;
import com.proyecto.application.service.IRevisionBusService;
import com.proyecto.domain.entity.Bus;
import com.proyecto.domain.entity.Personal;
import com.proyecto.domain.entity.RevisionBus;
import com.proyecto.domain.repository.IRevisionBusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revision-buses")
@RequiredArgsConstructor
public class RevisionBusController {

    private final IRevisionBusService service;

    @GetMapping("/listar")
    public List<RevisionBusResponseDto> listar() {
        return service.listar();
    }

    @PostMapping("/guardar")
    public RevisionBusResponseDto guardar(@RequestBody RevisionBusRequestDto dto) {
        return service.guardar(dto);
    }

    @PutMapping("/editar/{id}")
    public RevisionBusResponseDto editar(@PathVariable Long id, @RequestBody RevisionBusRequestDto dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/{id}")
    public RevisionBusResponseDto obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }
}