package com.proyecto.application.dto.destino;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DestinoRequestDTO {
    private String nombre;
    private String imagen;
    private String imagenUrl;
}
