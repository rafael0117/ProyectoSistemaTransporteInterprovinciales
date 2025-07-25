package com.proyecto.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "rol")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    @Id
    private String name;
}
