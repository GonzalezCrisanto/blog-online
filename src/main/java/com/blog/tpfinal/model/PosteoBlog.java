package com.blog.tpfinal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "posteos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosteoBlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contenido;

    private String imagenUrl;
    private String tags;

    @Column(nullable = false)
    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private AutorBlog autor;
}
