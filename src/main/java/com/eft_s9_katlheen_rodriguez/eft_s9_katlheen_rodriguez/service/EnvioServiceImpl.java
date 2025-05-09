package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.model.Envio;
import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.repository.EnvioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Implementación de los metodos del servicio
@Service
public class EnvioServiceImpl implements EnvioService {

    private static final Logger logger = LoggerFactory.getLogger(EnvioServiceImpl.class);

    @Autowired
    private EnvioRepository envioRepository;

    //Obtener todos los envíos
    @Override
    public List<Envio> getAllEnvios() {
        try {
            List<Envio> envios = envioRepository.findAll();
            logger.info("Consultando envíos: {}", envios);
            return envios;
        } catch (Exception e) {
            logger.error("Error al obtener envíos", e);
            return null;
        }
    }

    //Obtener un envío por ID
    @Override
    public Optional<Envio> getEnvioById(Long id) {
        try {
            return envioRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error al obtener el envío con ID {}", id, e);
            return Optional.empty();
        }
    }

    //Crear un nuevo envío
    @Override
    public Envio createEnvio(Envio envio) {
        try {
            return envioRepository.save(envio);
        } catch (Exception e) {
            logger.error("Error al crear el envío", e);
            return null;
        }
    }

    //Actualizar un envío
    @Override
    public Envio updateEnvio(Long id, Envio envio) {
        try {
            if (envioRepository.existsById(id)) {
                envio.setId(id);
                return envioRepository.save(envio);
            } else {
                logger.warn("No se encontró el envío con ID: {}", id);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error al actualizar el envío con ID {}", id, e);
            return null;
        }
    }

    //Eliminar un envío
    @Override
    public void deleteEnvio(Long id) {
        try {
            envioRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error al eliminar el envío con ID {}", id, e);
        }
    }
}