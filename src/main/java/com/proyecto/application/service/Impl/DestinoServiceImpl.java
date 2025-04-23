package com.proyecto.application.service.Impl;

import com.proyecto.application.dto.destino.DestinoResponseDTO;
import com.proyecto.application.service.DestinoService;
import com.proyecto.domain.entity.Destino;
import com.proyecto.application.mapper.DestinoMapper;
import com.proyecto.domain.repository.DestinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DestinoServiceImpl implements DestinoService {
    private final DestinoRepository repository;
    private final DestinoMapper mapper;

    @Override
    public List<DestinoResponseDTO> listar() {
        return repository.findAll().stream()
                .map(d -> DestinoResponseDTO.builder()
                        .idDestino(d.getIdDestino())
                        .nombre(d.getNombre())
                        .imagen(d.getImagen())
                        .imagenUrl("http://localhost:8080/images/" + d.getImagen())
                        .build()
                )
                .collect(Collectors.toList());
    }



    @Override
    public DestinoResponseDTO guardarConImagen(String nombre, MultipartFile imagen) {
        try {
            // 1. Usar el nombre original del archivo
            String nombreArchivo = imagen.getOriginalFilename();

            // 2. Ruta absoluta en el sistema de archivos
            Path rutaImagenes = Paths.get("src/main/resources/static/images/");
            if (!Files.exists(rutaImagenes)) {
                Files.createDirectories(rutaImagenes); // Crea la carpeta si no existe
            }

            Path rutaCompleta = rutaImagenes.resolve(nombreArchivo);
            Files.copy(imagen.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

            // 3. Solo guardar el nombre del archivo en la base de datos
            Destino destino = Destino.builder()
                    .nombre(nombre)
                    .imagen(nombreArchivo)
                    .build();

            return mapper.getDto(repository.save(destino));

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar la imagen localmente", e);
        }
    }




    public DestinoResponseDTO editarConImagen(Long id, String nombre, MultipartFile imagen) {
        try {
            Destino destino = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Destino no encontrado con ID: " + id));

            destino.setNombre(nombre);

            if (imagen != null && !imagen.isEmpty()) {
                String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
                Path rutaImagenes = Paths.get("src/main/resources/static/images/");
                if (!Files.exists(rutaImagenes)) {
                    Files.createDirectories(rutaImagenes);
                }
                Path rutaCompleta = rutaImagenes.resolve(nombreArchivo);
                Files.copy(imagen.getInputStream(), rutaCompleta, StandardCopyOption.REPLACE_EXISTING);

                String urlImagen = "/images/" + nombreArchivo;
                destino.setImagen(urlImagen);
            }

            return mapper.getDto(repository.save(destino));

        } catch (IOException e) {
            throw new RuntimeException("Error al actualizar el destino", e);
        }
    }



    @Override
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No existe un Destino con ID: " + id);
        }
        repository.deleteById(id);
    }


}
