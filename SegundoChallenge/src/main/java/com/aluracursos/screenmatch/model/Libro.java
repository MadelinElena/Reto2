package com.aluracursos.screenmatch.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String idioma;
    private Integer descargas;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    // Getters y Setters
}
