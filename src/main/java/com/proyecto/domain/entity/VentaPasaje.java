package com.proyecto.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "venta_pasaje")
public class VentaPasaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    @Column(name = "cliente", nullable = false, length = 100)
    private String cliente;

    @Column(name = "total", nullable = false)
    private double total;

    @OneToMany(mappedBy = "ventaPasaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVentaPasaje> detalles = new ArrayList<>();
}