package com.proyecto.web.controller;

import com.proyecto.application.dto.bus.BusRequestDTO;
import com.proyecto.application.dto.bus.BusResponseDTO;
import com.proyecto.application.dto.revision.RevisionBusRequestDto;
import com.proyecto.application.dto.revision.RevisionBusResponseDto;
import com.proyecto.application.service.RevisionBusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/revision")
@RequiredArgsConstructor
public class RevisionBusController {
    private final RevisionBusService service;

    @GetMapping("/listar")
    public List<RevisionBusResponseDto> listar() {return service.listar();}

    @PostMapping("/guardar")
    public RevisionBusResponseDto guardar(@RequestBody RevisionBusRequestDto requestDto) {return service.guardar(requestDto);}


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {service.eliminar(id);}

    @GetMapping("/{id}")
    public RevisionBusResponseDto listarPorId(@PathVariable Long id) {return service.listarPorId(id);}
}
