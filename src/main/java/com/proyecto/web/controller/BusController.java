package com.proyecto.web.controller;

import com.proyecto.application.dto.bus.BusRequestDTO;
import com.proyecto.application.dto.bus.BusResponseDTO;
import com.proyecto.application.service.IBusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus")
@RequiredArgsConstructor
public class BusController {
    private final IBusService service;

    @GetMapping("/listar")
    public List<BusResponseDTO> listar(){
        return service.listar();
    }
    @PostMapping("/guardar")
    public BusResponseDTO guardar(@RequestBody BusRequestDTO dto){
        return service.guardar(dto);

    }
    @PutMapping("/editar/{id}")
    public BusResponseDTO editar(@PathVariable Long id, @RequestBody BusRequestDTO dto) {
        return service.editar(id, dto);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/{id}")
    public BusResponseDTO obtenerBus(@PathVariable Long id) {return service.obtenerBus(id);}
}
