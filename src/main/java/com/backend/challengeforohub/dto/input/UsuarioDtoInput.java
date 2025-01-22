package com.backend.challengeforohub.dto.input;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoInput {

    @NotBlank(message = "The value nombre should not be empty")
    @Size(max = 50, message = "The value nombre should has max 50 characters")
    private String nombre;
    @NotBlank(message = "The value apellido should not be empty")
    @Size(max = 50, message = "The value apellido should has max 50 characters")
    private String apellido;
    @Positive(message = "The value dni should not be null or less than zero")
    @Digits(integer = 8, fraction = 0, message = "The value dni should has max 8 digits")
    private int dni;
    @Positive(message = "The value dni should not be null or less than zero")
    @Digits(integer = 2, fraction = 0, message = "The value edad should has max 2 digits")
    private int edad;
    @NotBlank(message = "The value telefono should not be empty")
    @Size(max = 50, message = "The value email should has max 50 characters")
    private String telefono;
    @NotBlank(message = "The value email should not be empty")
    @Size(max = 50, message = "The value email should has max 50 characters")
    private String email;
    @NotBlank(message = "The value nacionalidad should not be empty")
    @Size(max = 50, message = "The value nacionalidad should has max 50 characters")
    private String nacionalidad;
    @NotBlank(message = "The value direccion should not be empty")
    @Size(max = 50, message = "The value direccion should has max 50 characters")
    private String direccion;
    private boolean esAdmin;
    private boolean estaActivo;
    @NotBlank(message = "The value userName should not be empty")
    @Size(max = 55, message = "The value userName should has max 55 characters")
    private String userName;

    @NotBlank
    //@NotBlank(message = "The value password should not be empty", groups = OnCreate.class)
    @Size(max = 100, message = "The value password should has max 100 characters")
    private String password;

    @Override
    public String toString() {
        return "UsuarioDtoInput{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", nacionalidad='" + nacionalidad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", esAdmin=" + esAdmin +
                ", estaActivo=" + estaActivo +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
