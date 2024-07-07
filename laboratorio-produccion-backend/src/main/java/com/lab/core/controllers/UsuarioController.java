package com.lab.core.controllers;

import com.lab.core.entities.Usuario;
import com.lab.core.services.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/register/user")
    public Usuario createUser(@RequestBody Usuario usuario) {
        Usuario savedUser = usuarioService.saveUser(usuario);
        return savedUser;
    }

//    @PostMapping("/login")
//    public ResponseEntity<Usuario> loginUsuario(@RequestBody Usuario usuario) {
//        Usuario authenticatedUser = usuarioService.authenticateUser(usuario.getUsername(), usuario.getPassword());
//        if (authenticatedUser == null) {
//            return ResponseEntity.badRequest().build(); // Usuario no encontrado o clave incorrecta
//        }
//        return ResponseEntity.ok(authenticatedUser);
//    }

    @GetMapping("register/all")
    public ResponseEntity<List<Usuario>> getAllUsers() {
        List<Usuario> users = usuarioService.findAllUsers();
        return ResponseEntity.ok(users);
    }
}