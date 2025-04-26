package com.proyecto.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Destino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destino")
    private Long idDestino;

    @Column(name = "nombre_des", length = 100, nullable = false)
    private String nombre;

    @Column(name = "imagen", length = 255)
    private String imagen;

    @OneToMany(mappedBy = "destino", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("destino")
    private List<Viaje> viajes;
}
