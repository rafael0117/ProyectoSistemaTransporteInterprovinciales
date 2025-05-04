package com.proyecto.application.service;

import com.proyecto.application.dto.login.LoginRequest;
import com.proyecto.application.dto.login.LoginResponse;

public interface IAuthService {
    LoginResponse authenticate(LoginRequest loginRequest);
}
