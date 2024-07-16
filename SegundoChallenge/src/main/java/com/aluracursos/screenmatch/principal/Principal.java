package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnioNacimientoBeforeAndAnioFallecimientoAfter(Integer anio);
}
