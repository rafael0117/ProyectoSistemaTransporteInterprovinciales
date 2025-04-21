package com.proyecto.application.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DestinoRequestDTO {
    private String nombre;
    private String imagen;
    private String imagenUrl;
}
