package com.proyecto.web.controller;

import com.proyecto.application.dto.destino.DestinoResponseDTO;
import com.proyecto.application.service.IDestinoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/destino")
@RequiredArgsConstructor
public class DestinoController {

    private final IDestinoService service;

    @GetMapping("/listar")
    public List<DestinoResponseDTO> listar() {
        return service.listar();
    }

    @PostMapping("/crear")
    public ResponseEntity<DestinoResponseDTO> crearDestino(
            @RequestParam("nombre") String nombre,
            @RequestParam("imagen") MultipartFile imagen) {
        return ResponseEntity.ok(service.guardarConImagen(nombre, imagen));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<DestinoResponseDTO> editarDestino(
            @PathVariable Long id,
            @RequestParam("nombre") String nombre,
            @RequestParam(value = "imagen", required = false) MultipartFile imagen) {
        return ResponseEntity.ok(service.editarConImagen(id, nombre, imagen));
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
