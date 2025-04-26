package com.proyecto.application.dto.Personal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalResponseDto {
    private Integer idPersonal;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String direccion;
    private String descripcion;
}
