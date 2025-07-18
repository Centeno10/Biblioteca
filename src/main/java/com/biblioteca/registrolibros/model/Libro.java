package com.biblioteca.registrolibros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(nullable = true, length = 100)
    private String genero;

    @Column(name = "anio_publicacion", nullable = true)
    private Integer anioPublicacion;

    @ManyToOne(fetch = FetchType.EAGER) // EAGER para que cargue el autor inmediatamente
    @JoinColumn(name = "id_autor", nullable = false) // Columna de clave foránea
    private Autor autor;

    // Getters y Setters
    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(Integer anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}