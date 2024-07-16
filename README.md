Screenmatch - Catálogo de Libros
Screenmatch es una aplicación Java/Spring Boot que permite a los usuarios buscar y gestionar un catálogo de libros. Utiliza la API de Gutendex para obtener información de libros de Project Gutenberg. La aplicación permite realizar diversas operaciones, como buscar libros por título, listar libros registrados, listar autores, y más.

Características
Buscar libro por título: Permite al usuario buscar libros en la base de datos por su título.
Listar libros registrados: Muestra todos los libros que han sido registrados en la base de datos.
Listar autores registrados: Muestra todos los autores de los libros registrados.
Listar autores vivos en un determinado año: Permite listar los autores que estaban vivos en un año específico.
Listar libros por idioma: Permite listar los libros según el idioma en el que están escritos.
Requisitos
Java 17: La aplicación está desarrollada utilizando Java 17. Asegúrate de tenerlo instalado en tu sistema.
Maven: Utilizado para gestionar las dependencias del proyecto.
PostgreSQL: La base de datos utilizada es PostgreSQL. Asegúrate de tener una instancia en funcionamiento.
Spring Boot: La aplicación utiliza Spring Boot para facilitar el desarrollo y despliegue.
API de Gutendex: La aplicación utiliza esta API para obtener información sobre libros.
Configuración del Proyecto
Paso 1: Clonar el repositorio
Clona el repositorio a tu máquina local:

bash
Copiar código
git clone https://github.com/tu_usuario/screenmatch.git
cd screenmatch
Paso 2: Configurar la base de datos
Asegúrate de tener PostgreSQL instalado y en funcionamiento. Crea una base de datos y un usuario:

sql
Copiar código
CREATE DATABASE Libros;
CREATE USER madelin WITH PASSWORD 'password123';
GRANT ALL PRIVILEGES ON DATABASE Libros TO madelin;
Paso 3: Configurar application.properties
Modifica el archivo src/main/resources/application.properties para que se ajuste a tu configuración de PostgreSQL:

properties
Copiar código
spring.datasource.url=jdbc:postgresql://localhost:5432/Libros
spring.datasource.username=madelin
spring.datasource.password=password123
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true
Paso 4: Construir y ejecutar la aplicación
Utiliza Maven para construir y ejecutar la aplicación:

bash
Copiar código
mvn clean install
mvn spring-boot:run
Estructura del Proyecto
Directorios Principales
src/main/java: Contiene el código fuente de la aplicación.
src/main/resources: Contiene recursos como application.properties.
src/test/java: Contiene pruebas unitarias.
Paquetes Principales
controller: Contiene controladores REST.
model: Contiene las clases de entidad JPA.
repository: Contiene interfaces JPA para interactuar con la base de datos.
service: Contiene servicios que encapsulan la lógica de negocio.
principal: Contiene la clase principal que inicia la aplicación.
Clases Principales
LibroController.java
java
Copiar código
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
Autor.java
java
Copiar código
package com.aluracursos.screenmatch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer anioNacimiento;
    private Integer anioFallecimiento;

    // Getters y Setters
}
Libro.java
java
Copiar código
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
Principal.java
java
Copiar código
package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.Libro;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal implements CommandLineRunner {

    private final Scanner teclado = new Scanner(System.in);
    private final ConsumoAPI consumoApi = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/";

    @Override
    public void run(String... args) throws Exception {
        muestraElMenu();
    }

    private void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                1- Buscar libro por titulo
                2- Listar libros registrados
                3- Listar autores registrados
                4- Listar autores vivos en un determinado año
                5- Listar libros por idioma
                0- Salir
                """);
            System.out.print("Ingrese una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); // Consume newline

            switch (opcion) {
                case 1 -> buscarLibroPorTitulo();
                case 2 -> listarLibrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosEnUnDeterminadoAno();
                case 5 -> listarLibrosPorIdioma();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.print("Ingrese el nombre del libro que desea buscar: ");
        String titulo = teclado.nextLine();
        String url = URL_BASE + "?search=" + titulo.replace(" ", "%20");
        String jsonResponse = consumoApi.obtenerDatos(url);
        // Convertir jsonResponse a objeto Libro y procesarlo
        System.out.println(jsonResponse);
    }

    // Métodos adicionales para las demás funcionalidades...

    private void listarLibrosRegistrados() {
        // Implementar lógica para listar libros registrados
    }

    private void listarAutoresRegistrados() {
        // Implementar lógica para listar autores registrados
    }

    private void listarAutoresVivosEnUnDeterminadoAno() {
        // Implementar lógica para listar autores vivos en un determinado año
    }

    private void listarLibrosPorIdioma() {
        // Implementar lógica para listar libros por idioma
    }
}
AutorRepository.java
java
Copiar código
package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnioNacimientoBeforeAndAnioFallecimientoAfter(Integer anio);
}
LibroRepository.java
java
Copiar código
package com.aluracursos.screenmatch.repository;

import com.aluracursos.screenmatch.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    // Métodos de consulta personalizados
}
ConsumoAPI.java
java
Copiar código
package com.aluracursos.screenmatch.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoAPI {

    private final HttpClient client = HttpClient.newHttpClient();

    public String obtenerDatos(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener datos de la API", e);
        }
    }
}
ConvierteDatos.java
java
Copiar código
package com.aluracursos.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obtenerDatos(String json, Class<T> tipo) {
        try {
            return objectMapper.readValue(json, tipo);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al convertir datos JSON", e);
        }
    }
}
Contribuciones
Las contribuciones son bienvenidas. Si deseas contribuir, por favor abre un issue o envía un pull request.

Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

Contacto
Para cualquier consulta o sugerencia, puedes contactarme a través de:

Correo: madelinalzate@gmail.com
GitHub: MadelinAlzate
