package com.proyecto.web.controller;

import com.proyecto.application.dto.Personal.PersonalRequestDto;
import com.proyecto.application.dto.Personal.PersonalResponseDto;
import com.proyecto.application.service.PersonalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personal")
@RequiredArgsConstructor
public class PersonalController {
    private final PersonalService service;

    @GetMapping("/listar")
    public List<PersonalResponseDto> listar() {return service.listar();}

    @PostMapping("/guardar")
    public PersonalResponseDto guardar(@RequestBody PersonalRequestDto requestDto) {return service.guardar(requestDto);}

    @DeleteMapping("/eliminar/{id}")
    public void delete (@PathVariable Long id) {service.eliminar(id);}

    @GetMapping("/{id}")
    public PersonalResponseDto listarPorId(@PathVariable Long id) {return service.listarPorId(id);}
}
