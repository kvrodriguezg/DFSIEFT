package com.eft_s9_katlheen_rodriguez.eft_s9_katlheen_rodriguez.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException {

    public UsuarioNotFoundException(String mensaje){
        super(mensaje);
    }
    
}
