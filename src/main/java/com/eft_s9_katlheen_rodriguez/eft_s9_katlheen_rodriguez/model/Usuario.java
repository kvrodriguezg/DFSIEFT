package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.model;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;

//Clase Usuario para aplicación de viaje de mascotas
@Entity
@Table(name = "usuario")
//Orden de salida del json
@JsonPropertyOrder({ "id", "nombre", "rut", "email", "telefono", "direccion", "clave", "mascota" })
public class Usuario extends RepresentationModel<Usuario>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //No admitir nulos
    @NotNull
    //Tamaño permitido
    @Size(min = 2, max = 100)
    //Restrincciones de columna para la base de datos
    @Column(nullable = false, length = 100, name = "nombre")
    private String nombre;

    @NotNull
    //Solo admitir formato del RUT
    @Pattern(regexp = "\\d{7,8}-[kK\\d]", message = "El RUT debe tener un formato válido (ej: 12345678-9)")
    @Column(nullable = false, length = 12, name = "rut", unique = true)
    private String rut;

    @NotNull
    //Para validar formato email
    @Email(message = "Debe ser un correo válido")
    @Size(max = 100)
    @Column(nullable = false, length = 100, name = "email", unique = true)
    private String email;

    @NotNull
    //Solo admitir formato del tlf
    @Pattern(regexp = "\\+?\\d{8,15}", message = "Debe ser un número de teléfono válido")
    @Column(nullable = false, length = 15, name = "telefono")
    private String telefono;

    @NotNull
    @Size(min = 5, max = 150)
    @Column(nullable = false, length = 150, name = "direccion")
    private String direccion;

    @NotNull
    @Size(min = 6, max = 100, message = "La clave debe tener al menos 6 caracteres")
    @Column(nullable = false, name = "clave", length = 100)
    private String clave;

    @NotNull
    @Size(min = 2, max = 100, message = "La mascota debe tener al menos 2 caracteres")
    @Column(nullable = false, name = "mascota",length = 100)
    private String mascota;

    //Constructores
    public Usuario() {
    }
    //Constructor para crear nuevo objeto de Usuario con sus atributos
    public Usuario(Long id, String nombre, String email, String rut, String telefono, String direccion, String clave, String mascota) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.rut = rut;
        this.telefono = telefono;
        this.direccion = direccion;
        this.clave = clave;
        this.mascota = mascota;
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }
}
