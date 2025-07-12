package com.proyecto.application.dto.registrar;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrarRequestDTO {
    private String username;
    private String password;

}
