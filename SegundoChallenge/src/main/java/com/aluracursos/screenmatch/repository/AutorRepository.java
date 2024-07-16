package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnioNacimientoBeforeAndAnioFallecimientoAfter(Integer anio);
}
