package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.model.Envio;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EnvioRepositoryTest {

    @Autowired
    private EnvioRepository envioRepository;

    // Acción que se ejecuta antes de cada prueba
    @BeforeEach
    void setUp() {
        envioRepository.deleteAll();
    }

    // Acción que se ejecuta después de cada prueba
    @AfterEach
    void tearDown() {
        envioRepository.deleteAll();
    }

    // Prueba para guardar un Envío
    @Test
    void testGuardarEnvio() {
        // Se crea el objeto
        Envio envio = new Envio(
                "Lima",
                "Madrid",
                LocalDate.of(2025, 5, 3),
                "En tránsito",
                "Aeropuerto Jorge Chávez",
                LocalDate.of(2025, 5, 15));

        // Se guarda en bdd
        Envio savedEnvio = envioRepository.save(envio);

        // Verificar que el id no sea nulo
        assertNotNull(savedEnvio.getId());

        // Se verifican datos para validar si se creó
        assertEquals("Lima", savedEnvio.getOrigen());
        assertEquals("Madrid", savedEnvio.getDestino());
        assertEquals("En tránsito", savedEnvio.getEstado());
    }

    // Prueba para eliminar un Envío
    @Test
    void testEliminarEnvio() {

        // Se crea objeto
        Envio envio = new Envio(
                "Buenos Aires",
                "Tokio",
                LocalDate.of(2025, 4, 25),
                "Entregado",
                "Centro de distribución de Tokio",
                LocalDate.of(2025, 5, 2));

        // Se guarda en bdd y se obtiene el id
        Envio savedEnvio = envioRepository.save(envio);
        Long envioId = savedEnvio.getId();

        // Se verifica si está creado
        Optional<Envio> found = envioRepository.findById(envioId);
        assertTrue(found.isPresent());

        // Se elimina
        envioRepository.deleteById(envioId);

        // Se consulta si no está presente
        Optional<Envio> deleted = envioRepository.findById(envioId);
        assertFalse(deleted.isPresent());
    }

    // Prueba para actualizar un Envío
    @Test
    void testActualizarEnvio() {
        //Crear envío
        Envio envio = new Envio(
                "Santiago",
                "Nueva York",
                LocalDate.of(2025, 5, 1),
                "Pendiente",
                "Bodega Central",
                LocalDate.of(2025, 5, 20));

        //Guardar envío
        Envio savedEnvio = envioRepository.save(envio);

        //Actualizar campos
        savedEnvio.setEstado("En tránsito");
        savedEnvio.setUbicacionActual("Aeropuerto Internacional");

        //Guardar cambios nuevos
        Envio updatedEnvio = envioRepository.save(savedEnvio);

        //Consultar por ID
        Optional<Envio> resultado = envioRepository.findById(updatedEnvio.getId());

        //Verificar que los cambios se hayan guardado
        assertTrue(resultado.isPresent());
        assertEquals("En tránsito", resultado.get().getEstado());
        assertEquals("Aeropuerto Internacional", resultado.get().getUbicacionActual());
    }

}
