package com.backend.challengeforohub.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USUARIOS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String nombre;
    @Column(length = 50)
    private String apellido;
    private int dni;
    private int edad;
    @Column(length = 50)
    private String telefono;
    @Column(length = 50)
    private String email;
    @Column(length = 20)
    private String nacionalidad;
    @Column(length = 50)
    private String direccion;
    private boolean esAdmin;
    private boolean estaActivo;
    @Column(length = 80, unique = true)
    private String userName;
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 80)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "USUARIO_ROL",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id", "rol_id"})}
    )
    private List<Rol> roles;


    public Usuario(String nombre, String apellido, int dni, int edad, String telefono, String email, String nacionalidad, String direccion, boolean esAdmin, boolean estaActivo, String userName, String password, List<Rol> roles) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.telefono = telefono;
        this.email = email;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.esAdmin = esAdmin;
        this.estaActivo = estaActivo;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Usuario{" +
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
