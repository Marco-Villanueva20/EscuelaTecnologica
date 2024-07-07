package com.escuelatecnologica.core.controllers;

import com.escuelatecnologica.core.entities.Modulo;
import com.escuelatecnologica.core.repositories.ModuloRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    @Autowired
    private ModuloRepository moduloRepository;

    @GetMapping
    public List<Modulo> getAllModulos() {
        return moduloRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modulo> getModuloById(@PathVariable Integer id) {
        Optional<Modulo> modulo = moduloRepository.findById(id);
        return modulo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Modulo createModulo(@RequestBody Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modulo> updateModulo(@PathVariable Integer id, @RequestBody Modulo moduloDetails) {
        Optional<Modulo> modulo = moduloRepository.findById(id);
        if (modulo.isPresent()) {
            Modulo updatedModulo = modulo.get();
            updatedModulo.setNombre(moduloDetails.getNombre());
            return ResponseEntity.ok(moduloRepository.save(updatedModulo));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModulo(@PathVariable Integer id) {
        if (moduloRepository.existsById(id)) {
            moduloRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}