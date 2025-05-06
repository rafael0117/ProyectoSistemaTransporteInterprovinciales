package com.proyecto.application.service.Impl;


import com.proyecto.application.dto.login.LoginRequest;
import com.proyecto.application.dto.login.LoginResponse;
import com.proyecto.application.dto.registrar.RegistrarRequestDTO;
import com.proyecto.application.service.IAuthService;
import com.proyecto.domain.entity.Rol;
import com.proyecto.domain.entity.Usuario;
import com.proyecto.domain.repository.IRolRepository;
import com.proyecto.domain.repository.IUsuarioRepository;
import com.proyecto.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.proyecto.security.SecurityConfig;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceServiceImpl implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final IRolRepository repository;
    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
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

    @Override
    public String register(RegistrarRequestDTO request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("El usuario ya existe");
        }

        Rol rolUser = repository.findById("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("El rol USER no existe"));

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(rolUser))
                .build();

        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente";
    }


}
