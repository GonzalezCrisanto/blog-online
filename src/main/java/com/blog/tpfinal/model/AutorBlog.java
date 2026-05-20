package com.blog.tpfinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePublico;
    private String biografia;
    private String fotoPerfil;
    private String twitter;
    private String instagram;

    @Column(nullable = false)
    private LocalDate fechaIngreso;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<PosteoBlog> posteos;
}
