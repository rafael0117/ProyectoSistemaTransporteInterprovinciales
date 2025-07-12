package com.proyecto.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "personal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String dni;
    private String cargo;
    private String telefono;

    // Relación inversa con RevisionBus
    @OneToMany(mappedBy = "personal", fetch = FetchType.LAZY)
    private List<RevisionBus> revisionBuses;  // Un Personal puede tener muchas revisiones
}