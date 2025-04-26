package com.proyecto.domain.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
