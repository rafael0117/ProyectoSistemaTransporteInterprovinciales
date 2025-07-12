package com.proyecto.application.dto.personal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalRequestDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private String cargo;
    private String telefono;
}
