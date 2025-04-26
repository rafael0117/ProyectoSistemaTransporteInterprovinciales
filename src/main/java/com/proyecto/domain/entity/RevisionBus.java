package com.proyecto.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Entity
@Table(name = "tb_revision")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RevisionBus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "revision_id")
    private Long revisionId;

    @Column(name = "fecha_revision", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRevision;

    @Column(name = "tipo_revision", length = 50, nullable = false)
    private String tipoRevision;

    @Column(name = "resultado", length = 50, nullable = false)
    private String resultado;

    @Column(name = "observaciones", length = 50)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_bus", nullable = false)
    private Bus bus;

}
