package com.proyecto.application.dto.Personal;

import lombok.Data;

@Data
public class PersonalRequestDto {
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String email;
    private String direccion;
    private Long idRol;
}
