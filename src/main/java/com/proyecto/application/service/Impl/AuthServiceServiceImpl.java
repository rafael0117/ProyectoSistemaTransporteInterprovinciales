package com.proyecto.application.service.Impl;


import com.proyecto.application.dto.login.LoginRequest;
import com.proyecto.application.dto.login.LoginResponse;
import com.proyecto.application.service.IAuthService;
import com.proyecto.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    @Override
    public LoginResponse authenticate(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                  loginRequest.getUsername(),
                  loginRequest.getPassword()
                )
        );
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(user);
        long expiration = jwtUtil.extractExpiration(token).getTime();
        return LoginResponse.builder()
                .token(token)
                .username(user.getUsername())
                .roles(user.getAuthorities().stream()
                        .map(r -> r.getAuthority())
                        .toList())
                .expirateAt(expiration)
                .build();
    }
}
