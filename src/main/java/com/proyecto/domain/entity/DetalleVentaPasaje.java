package com.proyecto.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "detalle_venta_pasaje")
public class DetalleVentaPasaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;  // Esta columna es la que tiene auto_increment

    @ManyToOne
    @JoinColumn(name = "id_venta")  // Clave foránea, NO AUTO_INCREMENT
    private VentaPasaje ventaPasaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_viaje", nullable = false)
    private Viaje viaje;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    @Column(name = "total", nullable = false)
    private double total;
}