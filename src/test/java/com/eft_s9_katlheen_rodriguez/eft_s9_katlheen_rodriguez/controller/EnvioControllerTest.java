package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.service.EnvioService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EnvioControllerTest {

    private MockMvc mockMvc;

    @Mock //Se crea un objeto falso (mock)
    private EnvioService envioService;

    @InjectMocks //Se crea una instancia de EnvioController y se inyecta el mock de EnvioService
    private EnvioController envioController;

    //Antes de cada prueba
    @BeforeEach
    void setUp() {
        //Se inicializan los objetos mock
        MockitoAnnotations.openMocks(this);
        //Objeto MockMvc para realizar pruebas
        mockMvc = MockMvcBuilders.standaloneSetup(envioController).build();
    }

    //Prueba para eliminar un envio
    @Test
    void testEliminarEnvio() throws Exception {
        //Id del envío a eliminar
        Long envioId = 1L;

        //Configurar el mock para que no haga nada al eliminar
        doNothing().when(envioService).deleteEnvio(envioId);

        //Realizar la solicitud delete y verificar que el estado sea OK
        mockMvc.perform(delete("/envios/{id}", envioId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Verificar que el método deleteEnvio fue llamado una sola vez y con el id correcto
        verify(envioService, times(1)).deleteEnvio(envioId);
    }
}