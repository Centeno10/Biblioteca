package com.biblioteca.registrolibros.controller;

import com.biblioteca.registrolibros.model.Autor;
import com.biblioteca.registrolibros.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Indica que es un controlador REST
@RequestMapping("/api/autores") // Ruta base para los endpoints REST de autores
public class AutorApiController {

    @Autowired
    private AutorRepository autorRepository;

    // Obtener todos los autores
    @GetMapping
    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }

    // Obtener un autor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        return autor.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo autor
    @PostMapping
    public Autor createAutor(@RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    // Actualizar un autor existente
    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(@PathVariable Long id, @RequestBody Autor autorDetails) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            Autor existingAutor = autor.get();
            existingAutor.setNombre(autorDetails.getNombre());
            existingAutor.setNacionalidad(autorDetails.getNacionalidad());
            return ResponseEntity.ok(autorRepository.save(existingAutor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un autor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}