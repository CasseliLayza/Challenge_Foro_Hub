package com.backend.challengeforohub.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopicoDtoInput {

    @NotBlank(message = "The value titulo should not be empty")
    @Size(max = 50, message = "The value titulo should has max 50 characters")
    private String titulo;
    @NotBlank(message = "The value mensaje should not be empty")
    @Size(max = 50, message = "The value mensaje should has max 50 characters")
    private String mensaje;

    private boolean status;
    @NotBlank(message = "The value fechaCreacion should not be empty")
    @Size(max = 50, message = "The value fechaCreacion should has max 50 characters")
    private String fechaCreacion;
    @NotBlank(message = "The value curso should not be empty")
    @Size(max = 50, message = "The value curso should has max 50 characters")
    private String curso;
    @NotBlank(message = "The value autor should not be empty")
    @Size(max = 50, message = "The value autor should has max 50 characters")
    private String autor;


    @Override
    public String toString() {
        return "TopicoDtoInput{" +
                "titulo='" + titulo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", status=" + status +
                ", fechaCreacion='" + fechaCreacion + '\'' +
                ", curso='" + curso + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}
