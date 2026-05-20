package com.blog.tpfinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutorBlogDTO {

    private Long id;
    private String nombrePublico;
    private String biografia;
    private String fotoPerfil;
    private String twitter;
    private String instagram;
    private LocalDate fechaIngreso;
}
