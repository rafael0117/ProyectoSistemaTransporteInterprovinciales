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
                .cors(Customizer.withDefaults())  // Habilita CORS con la configuración que has definido
                .csrf(csrf -> csrf.disable())    // Deshabilita CSRF, adecuado para API RESTful
                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Sin estado (stateless)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Permite el acceso sin autenticación a las rutas de autenticación
                        .requestMatchers("/api/auth/**").permitAll()
                        // Asegura que las rutas de bus, destino y viaje sean accesibles solo para usuarios con rol "ADMIN"
                        .requestMatchers("/api/bus/**").permitAll()
                        .requestMatchers("/api/destino/**").permitAll()
                        .requestMatchers("/images/**", "/css/**", "/js/**", "/favicon.ico").permitAll()
                        .requestMatchers("/api/viaje/**").permitAll()
                        .requestMatchers("/api/venta/**").permitAll()
                        // Otras rutas requieren que el usuario esté autenticado
                        .anyRequest().authenticated()
                )
                .authenticationProvider(provider())  // Configura el proveedor de autenticación
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);  // Agrega el filtro JWT antes del filtro de autenticación estándar
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
