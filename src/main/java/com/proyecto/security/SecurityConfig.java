package com.proyecto.security;


import com.proyecto.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService userDetailService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/images/**", "/css/**", "/js/**", "/favicon.ico").permitAll()

                        // SUPERVISOR: puede hacer GET a bus y destino
                        .requestMatchers(HttpMethod.GET, "/api/bus/**").hasAnyRole("ADMIN", "SUPER")
                        .requestMatchers(HttpMethod.GET, "/api/destino/**").hasAnyRole("ADMIN", "SUPER")

                        // ADMIN: acceso total a bus y destino (POST, PUT, DELETE, etc.)
                        .requestMatchers("/api/bus/**").hasRole("ADMIN")
                        .requestMatchers("/api/destino/**").hasRole("ADMIN")

                        // SUPERVISOR: acceso completo a viajes
                        .requestMatchers("/api/viaje/**").hasRole("SUPER")
                        .requestMatchers("/api/personal/**").hasRole("SUPER")
                        .requestMatchers("/api/revision-buses/**").hasRole("SUPER")




                        // USER y ADMIN: acceso a ventas
                        .requestMatchers("/api/venta/**").hasAnyRole("ADMIN", "USER")

                        .anyRequest().authenticated()
                )
                .authenticationProvider(provider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }



    // Configura el codificador de contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Proveedor de autenticación con detalles de usuario y codificación de contraseñas
    @Bean
    public DaoAuthenticationProvider provider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);  // Usa el servicio personalizado para cargar usuarios
        provider.setPasswordEncoder(passwordEncoder());  // Usa el codificador BCrypt
        return provider;
    }

    // Configura el AuthenticationManager, necesario para la autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Configuración de CORS para permitir solicitudes desde localhost:4200 (Frontend Angular)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:4200"));  // Asegúrate de que el frontend está en este origen
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));  // Permite los encabezados de autorización
        corsConfiguration.setAllowCredentials(true);  // Permite el envío de credenciales (tokens)
        corsConfiguration.setExposedHeaders(List.of("Authorization"));  // Expone el encabezado de autorización
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}