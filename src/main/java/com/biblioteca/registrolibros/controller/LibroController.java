package com.biblioteca.registrolibros.controller;

import com.biblioteca.registrolibros.model.Autor;
import com.biblioteca.registrolibros.model.Libro;
import com.biblioteca.registrolibros.repository.AutorRepository;
import com.biblioteca.registrolibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libros-web") // Prefijo para las URLs de este controlador web
public class LibroController {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository; // Para obtener la lista de autores

    // Muestra la lista de libros y el formulario para añadir uno nuevo
    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroRepository.findAll());
        model.addAttribute("nuevoLibro", new Libro()); // Objeto vacío para el formulario
        model.addAttribute("autoresDisponibles", autorRepository.findAll()); // Lista de autores para el select
        return "libros"; // Nombre de la plantilla HTML (libros.html)
    }

    // Procesa el formulario para agregar un nuevo libro
    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute Libro libro) {
        // Asegúrate de que el autor esté correctamente asociado si el formulario envía solo el ID
        // Si tu Libro tiene un @ManyToOne a Autor y el formulario envía el ID del autor,
        // Spring/JPA debería ser capaz de mapearlo si el campo en el formulario se llama "autor.idAutor".
        // Si no, tendrías que cargarlo manualmente (pero en este caso con th:field="*{autor.idAutor}" debería funcionar):
        if (libro.getAutor() != null && libro.getAutor().getIdAutor() != null) {
            Autor autorExistente = autorRepository.findById(libro.getAutor().getIdAutor()).orElse(null);
            libro.setAutor(autorExistente);
        }
        libroRepository.save(libro);
        return "redirect:/libros-web"; // Redirige a la página de listar libros
    }
}