package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Métodos de consulta personalizados
}
