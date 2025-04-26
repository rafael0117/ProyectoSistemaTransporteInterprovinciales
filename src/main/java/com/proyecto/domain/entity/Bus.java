package com.proyecto.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_bus")
@Data
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_bus")
    private Long idBus;

    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "anio", nullable = false)
    private int anio;

    @Column(name = "capacidad", nullable = false)
    private int capacidad;

    @Column(name = "placa", nullable = false, length = 20)
    private String placa;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RevisionBus> buses;
}
