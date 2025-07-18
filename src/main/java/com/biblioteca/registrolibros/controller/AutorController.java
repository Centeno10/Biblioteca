package com.biblioteca.registrolibros.controller;

import com.biblioteca.registrolibros.model.Autor;
import com.biblioteca.registrolibros.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/autores-web") // Prefijo para las URLs de este controlador web
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    // Muestra la lista de autores y el formulario para añadir uno nuevo
    @GetMapping
    public String listarAutores(Model model) {
        model.addAttribute("autores", autorRepository.findAll());
        model.addAttribute("nuevoAutor", new Autor()); // Objeto vacío para el formulario
        return "autores"; // Nombre de la plantilla HTML (autores.html)
    }

    // Procesa el formulario para agregar un nuevo autor
    @PostMapping("/guardar")
    public String guardarAutor(@ModelAttribute Autor autor) {
        autorRepository.save(autor);
        return "redirect:/autores-web"; // Redirige a la página de listar autores
    }
}