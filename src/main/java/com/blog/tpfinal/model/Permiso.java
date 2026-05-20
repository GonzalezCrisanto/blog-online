package com.blog.tpfinal.model;

import com.blog.tpfinal.enums.EPermiso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permisos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private EPermiso nombre;
}
