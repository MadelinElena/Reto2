package com.aluracursos.screenmatch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibroController {

    @GetMapping("/libros")
    public String mostrarMensaje(){
        return "Este es mi primer mensaje en mi aplicación web";
    }
}
