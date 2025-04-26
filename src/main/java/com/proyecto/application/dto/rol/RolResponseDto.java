package com.proyecto.application.dto.rol;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolResponseDto {
    private Long idRol;
    private String descripcion;
}
