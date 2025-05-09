package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.model.Usuario;
import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Implementación de los metodos del servicio
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Obtener todos los usuarios
    @Override
    public List<Usuario> getAllUsuarios() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            logger.info("Consultando usuarios: {}", usuarios);
            return usuarios;
        } catch (Exception e) {
            logger.error("Error al obtener usuarios", e);
            return null;
        }
    }

    //Obtener un usuario por ID
    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        try {
            return usuarioRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error al obtener el usuario con ID {}", id, e);
            return Optional.empty();
        }
    }

    //Crear un nuevo usuario
    @Override
    public Usuario createUsuario(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            logger.error("Error al crear el usuario", e);
            return null;
        }
    }

    //Actualizar un usuario
    @Override
    public Usuario updateUsuario(Long id, Usuario usuario) {
        try {
            if (usuarioRepository.existsById(id)) {
                usuario.setId(id);
                return usuarioRepository.save(usuario);
            } else {
                logger.warn("No se encontró el usuario con ID: {}", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error al actualizar el usuario con ID {}", id, e);
            return null;
        }
    }

    //Eliminar un usuario
    @Override
    public void deleteUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error al eliminar el usuario con ID {}", id, e);
        }
    }
}