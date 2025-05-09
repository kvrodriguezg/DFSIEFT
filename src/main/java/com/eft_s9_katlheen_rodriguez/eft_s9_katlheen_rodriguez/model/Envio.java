package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;
//Clase Envio para seguimiento de envios internacionales
@Entity
@Table(name = "envio")
//Orden de salida del json
@JsonPropertyOrder({ "id", "origen", "destino", "fechaEnvio", "estado", "ubicacionActual", "fechaEstimadaEntrega" })
public class Envio extends RepresentationModel<Envio> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //No admitir nulos
    @NotNull
    //Tamaño permitido
    @Size(min = 2, max = 100)
    //Restrincciones de columna para la base de datos
    @Column(name = "origen", nullable = false, length = 100)
    private String origen;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(name = "destino", nullable = false, length = 100)
    private String destino;

    @NotNull
    @Column(name = "fecha_envio", nullable = false)
    private LocalDate fechaEnvio;

    @NotNull
    @Size(min = 2, max = 50)
    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @NotNull
    @Size(min = 2, max = 150)
    @Column(name = "ubicacion_actual", nullable = false, length = 150)
    private String ubicacionActual;

    @NotNull
    @Column(name = "fecha_estimada_entrega", nullable = false)
    private LocalDate fechaEstimadaEntrega;

    //Constructores
    public Envio() {
    }

    public Envio(String origen, String destino, LocalDate fechaEnvio, String estado, String ubicacionActual, LocalDate fechaEstimadaEntrega) {
        this.origen = origen;
        this.destino = destino;
        this.fechaEnvio = fechaEnvio;
        this.estado = estado;
        this.ubicacionActual = ubicacionActual;
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    //Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public LocalDate getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDate fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getUbicacionActual() { return ubicacionActual; }
    public void setUbicacionActual(String ubicacionActual) { this.ubicacionActual = ubicacionActual; }

    public LocalDate getFechaEstimadaEntrega() { return fechaEstimadaEntrega; }
    public void setFechaEstimadaEntrega(LocalDate fechaEstimadaEntrega) { this.fechaEstimadaEntrega = fechaEstimadaEntrega; }
}
