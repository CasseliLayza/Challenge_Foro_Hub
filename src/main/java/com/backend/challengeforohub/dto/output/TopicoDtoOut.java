package com.backend.challengeforohub.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoDtoOut {

    private Long id;
    private String titulo;
    private String mensaje;
    private boolean status;
    private String fechaCreacion;
    private String autor;
    private String curso;

    @Override
    public String toString() {
        return "TopicoDtoOut{" +
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
