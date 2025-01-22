package com.backend.challengeforohub.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TOPICOS")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String titulo;
    @Column(length = 50)
    private String mensaje;
    @Column(length = 50)
    private boolean status;
    private String fechaCreacion;
    @Column(length = 50)
    private String autor;
    @Column(length = 50)
    private String curso;

    public Topico(String titulo, String mensaje, boolean status, String fechaCreacion, String autor, String curso) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.status = status;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Topico{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", status=" + status +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", autor='" + autor + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
