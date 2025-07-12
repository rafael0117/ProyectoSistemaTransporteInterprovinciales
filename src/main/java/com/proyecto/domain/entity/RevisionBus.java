package com.proyecto.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "revision_bus")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RevisionBus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_revision")
    private Long idRevision;

    @Column(nullable = false)
    private LocalDate fechaRevision;

    @Column(length = 255)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "id_bus", nullable = false)
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "id_personal", nullable = false)
    private Personal personal;
}