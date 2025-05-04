package com.proyecto.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Viaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viaje")
    private Long idViaje;

    @ManyToOne
    @JoinColumn(name = "id_bus", nullable = false)
    @JsonIgnoreProperties("viajes")
    private Bus idBus;

    @ManyToOne
    @JoinColumn(name = "id_destino", nullable = false)
    @JsonIgnoreProperties("viajes")
    private Destino destino;

    @Column(name = "fech_sal", nullable = false)
    private LocalDate fechaSalida;

    @Column(name = "fech_lle", nullable = false)
    private LocalDate fechaLlegada;

    @Column(name = "incidencias", length = 40)
    private String incidencias;

    @Column(name = "precio", nullable = false)
    private double precio;
    @Column(name = "cantidad_disponible")
    private int cantidadDisponible;
}
