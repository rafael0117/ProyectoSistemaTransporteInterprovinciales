package com.proyecto.application.service;

import com.proyecto.application.dto.login.LoginRequest;
import com.proyecto.application.dto.login.LoginResponse;
import com.proyecto.application.dto.registrar.RegistrarRequestDTO;

public interface IAuthService {
    LoginResponse authenticate(LoginRequest loginRequest);
    String register(RegistrarRequestDTO registerRequest);
}
