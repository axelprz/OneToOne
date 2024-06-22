package com.uch.bd2.onetoone.controllers;

import com.uch.bd2.onetoone.models.Usuario;
import com.uch.bd2.onetoone.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ArrayList<Usuario> getUsuarios() {
        return this.usuarioService.getUsuarios();
    }

    @GetMapping(path = "/{id}")
    public Optional<Usuario> getUsuarioById(@PathVariable Long id) {
        return this.usuarioService.getById(id);
    }
}
