package com.backend.challengeforohub.dto.output;


import com.backend.challengeforohub.entity.Rol;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.PrePersist;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDtoOut {

    private Long id;
    private String nombre;
    private String apellido;
    private int dni;
    private int edad;
    private String telefono;
    private String email;
    private String nacionalidad;
    private String direccion;
    private boolean esAdmin;
    private boolean estaActivo;

    @PrePersist
    public void prePersist() {
        this.estaActivo = true;
    }

    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private List<Rol> roles;


    @Override
    public String toString() {
        return "UsuarioDtoOut{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
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
                ", roles=" + roles +
                '}';
    }
}
