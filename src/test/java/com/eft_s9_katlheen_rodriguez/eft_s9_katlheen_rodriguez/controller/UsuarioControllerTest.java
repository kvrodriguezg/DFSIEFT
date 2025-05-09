package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.controller;

import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.model.Usuario;
import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UsuarioControllerTest {

    private MockMvc mockMvc;

    @Mock //Se crea un objeto falso (mock)
    private UsuarioService usuarioService;

    @InjectMocks //Se crea una instancia de UsuarioController y se inyecta el mock de UsuarioService
    private UsuarioController usuarioController;

    private ObjectMapper objectMapper;

    //Antes de cada prueba
    @BeforeEach
    void setUp() {
        //Se inicializan los objetos mock
        MockitoAnnotations.openMocks(this);
        //Objeto MockMvc para realizar pruebas
        mockMvc = MockMvcBuilders.standaloneSetup(usuarioController).build();
        //Para convertir objetos javas a JSON
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCrearUsuario() throws Exception {
        //Crear un usuario de prueba
        Usuario usuario = new Usuario();
        usuario.setNombre("Ana Pérez");
        usuario.setRut("12345678-9");
        usuario.setEmail("ana@gmail.com");
        usuario.setTelefono("+56912345678");
        usuario.setDireccion("Los Olmos 123");
        usuario.setClave("clavesecreta123");
        usuario.setMascota("Fido");

        //Se indica que al crear debe devolver el usuario creado previamente
        when(usuarioService.createUsuario(any(Usuario.class))).thenReturn(usuario);

        //Convertir el objeto usuario a JSON
        String usuarioJson = objectMapper.writeValueAsString(usuario);

        //Se realiza una solicitud POST usando MockMvc y se verifican que los datos coincidan
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/usuarios")
                        .contentType("application/json")
                        .content(usuarioJson))
                .andExpect(status().isOk()) //Verificar que la respuesta este OK
                .andExpect(MockMvcResultMatchers.jsonPath("$.nombre").value("Ana Pérez")) // Verificar que el nombre sea correcto
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("ana@gmail.com")); // Verificar que el email sea correcto

        //Verificar que el servicio fue llamado solo una vez
        verify(usuarioService, times(1)).createUsuario(any(Usuario.class));
    }
}