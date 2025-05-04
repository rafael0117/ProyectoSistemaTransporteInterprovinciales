package com.proyecto.security;


import com.proyecto.domain.entity.Usuario;
import com.proyecto.domain.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final IUsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no registrado"));
        return new User(
          user.getUsername(),
          user.getPassword(),
          user.getRoles().stream()
                  .map(r -> new SimpleGrantedAuthority(r.getName()))
                  .toList()
        );


    }
}
