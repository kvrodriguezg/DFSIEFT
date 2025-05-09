package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.model.Envio;
import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.service.EnvioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/envios")
@CrossOrigin(origins = "*")
public class EnvioController {

    //Se llama servicio
    @Autowired
    private EnvioService envioService;


    //Obtener todos los envios con enlaces HATEOAS
    @GetMapping
    public CollectionModel<EntityModel<Envio>> getAllEnvios() {
        List<Envio> envios = envioService.getAllEnvios();

        List<EntityModel<Envio>> envioResources = envios.stream()
                .map(envio -> EntityModel.of(envio,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEnvioById(envio.getId())).withSelfRel()
                ))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo =  WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEnvios());    
        CollectionModel<EntityModel<Envio>> resources = CollectionModel.of(envioResources, linkTo.withRel("envios"));
        
        return resources;                
    }

    //Obtener un envio por ID con enlaces HATEOAS
    @GetMapping("/{id}")
    public EntityModel<Envio> getEnvioById(@PathVariable Long id) {
        Optional<Envio> envio = envioService.getEnvioById(id);

        if (envio.isPresent()) {
            return EntityModel.of(envio.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEnvioById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEnvios()).withRel("todos-envios")
            );
        } else {
            throw new EnvioNotFoundException("Envio no encontrado con id: " + id);
        }
    }

    //Crear nuevo envio con enlaces HATEOAS
    @PostMapping
    public EntityModel<Envio> createEnvio(@RequestBody @Valid Envio envio) {
        Envio envioCreado = envioService.createEnvio(envio);

        return EntityModel.of(envioCreado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEnvioById(envioCreado.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEnvios()).withRel("todos-envios")
        );
    }

    //Actualizar usuario con enlaces HATEOAS
    @PutMapping("/{id}")
    public EntityModel<Envio> updateEnvio(@PathVariable Long id, @RequestBody @Valid Envio envio) {
        Optional<Envio> envioIngresado = envioService.getEnvioById(id);

        if (envioIngresado.isPresent()) {
            Envio envioActualizado = envioService.updateEnvio(id, envio);

            return EntityModel.of(envioActualizado,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getEnvioById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllEnvios()).withRel("todos-envios")
            );
        } else {
            throw new EnvioNotFoundException("Envio no encontrado con id: " + id);
        }
    }
    //Eliminar un envio
    @DeleteMapping("/{id}")
    public void deleteEnvio(@PathVariable Long id) {
        envioService.deleteEnvio(id);
    }
}
