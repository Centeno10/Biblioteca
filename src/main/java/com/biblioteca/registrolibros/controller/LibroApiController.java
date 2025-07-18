package com.biblioteca.registrolibros.controller;

import com.biblioteca.registrolibros.model.Libro;
import com.biblioteca.registrolibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Indica que es un controlador REST
@RequestMapping("/api/libros") // Ruta base para los endpoints REST de libros
public class LibroApiController {

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todos los libros
    @GetMapping
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    // Obtener un libro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Libro> getLibroById(@PathVariable Long id) {
        Optional<Libro> libro = libroRepository.findById(id);
        return libro.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo libro
    @PostMapping
    public Libro createLibro(@RequestBody Libro libro) {
        return libroRepository.save(libro);
    }

    // Actualizar un libro existente
    @PutMapping("/{id}")
    public ResponseEntity<Libro> updateLibro(@PathVariable Long id, @RequestBody Libro libroDetails) {
        Optional<Libro> libro = libroRepository.findById(id);
        if (libro.isPresent()) {
            Libro existingLibro = libro.get();
            existingLibro.setTitulo(libroDetails.getTitulo());
            existingLibro.setGenero(libroDetails.getGenero());
            existingLibro.setAnioPublicacion(libroDetails.getAnioPublicacion());
            existingLibro.setAutor(libroDetails.getAutor()); // Asegúrate de que el autor se envíe correctamente en el JSON
            return ResponseEntity.ok(libroRepository.save(existingLibro));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un libro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}