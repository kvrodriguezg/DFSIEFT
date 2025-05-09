package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.model.Usuario;
import com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.service.UsuarioService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    //Obtener todos los usuarios con enlaces HATEOAS
    @GetMapping
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();

        List<EntityModel<Usuario>> usuarioResources = usuarios.stream()
                .map(usuario -> EntityModel.of(usuario,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(usuario.getId())).withSelfRel()
                ))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo =  WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios());    
        CollectionModel<EntityModel<Usuario>> resources = CollectionModel.of(usuarioResources, linkTo.withRel("usuarios"));
        
        return resources;
                
    }

    //Obtener un usuario por ID con enlaces HATEOAS
    @GetMapping("/{id}")
    public EntityModel<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);

        if (usuario.isPresent()) {
            return EntityModel.of(usuario.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("todos-usuarios")
            );
        } else {
            throw new UsuarioNotFoundException("Usuario no encontrado con id: " + id);
        }
    }

    //Crear nuevo usuario con enlaces HATEOAS
    @PostMapping
    public EntityModel<Usuario> createUsuario(@RequestBody @Valid Usuario usuario) {
        Usuario usuarioCreado = usuarioService.createUsuario(usuario);

        return EntityModel.of(usuarioCreado,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(usuarioCreado.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("todos-usuarios")
        );
    }

    //Actualizar usuario con enlaces HATEOAS
    @PutMapping("/{id}")
    public EntityModel<Usuario> updateUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
        Optional<Usuario> usuarioIngresado = usuarioService.getUsuarioById(id);

        if (usuarioIngresado.isPresent()) {
            Usuario usuarioActualizado = usuarioService.updateUsuario(id, usuario);

            return EntityModel.of(usuarioActualizado,
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(id)).withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("todos-usuarios")
            );
        } else {
            throw new UsuarioNotFoundException("Usuario no encontrado con id: " + id);
        }
    }

    //Eliminar usuario
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }
}
