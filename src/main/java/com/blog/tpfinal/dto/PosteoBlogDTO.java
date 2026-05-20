package com.blog.tpfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PosteoBlogDTO {

    private Long id;
    private String titulo;
    private String contenido;
    private String imagenUrl;
    private String tags;
    private LocalDateTime fechaPublicacion;
    private String autorNombre;
}
