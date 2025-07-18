package com.biblioteca.registrolibros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // Muestra la página de bienvenida (index.html)
    @GetMapping("/")
    public String index() {
        return "index"; // Nombre de la plantilla HTML (index.html)
    }
}