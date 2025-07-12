package com.proyecto.application.dto.rol;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolResponseDto {
    private String idRol;
    private String descripcion;
}
