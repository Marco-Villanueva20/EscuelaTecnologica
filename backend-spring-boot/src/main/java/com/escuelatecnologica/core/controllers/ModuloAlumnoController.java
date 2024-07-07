package com.escuelatecnologica.core.controllers;

import com.escuelatecnologica.core.entities.ModuloAlumno;
import com.escuelatecnologica.core.repositories.ModuloAlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/moduloalumnos")
public class ModuloAlumnoController {

    @Autowired
    private ModuloAlumnoRepository moduloAlumnoRepository;

    @GetMapping
    public List<ModuloAlumno> getAllModuloAlumnos() {
        return moduloAlumnoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuloAlumno> getModuloAlumnoById(@PathVariable Integer id) {
        Optional<ModuloAlumno> moduloAlumno = moduloAlumnoRepository.findById(id);
        return moduloAlumno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ModuloAlumno createModuloAlumno(@RequestBody ModuloAlumno moduloAlumno) {
        return moduloAlumnoRepository.save(moduloAlumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuloAlumno> updateModuloAlumno(@PathVariable Integer id, @RequestBody ModuloAlumno moduloAlumnoDetails) {
        Optional<ModuloAlumno> moduloAlumno = moduloAlumnoRepository.findById(id);
        if (moduloAlumno.isPresent()) {
            ModuloAlumno updatedModuloAlumno = moduloAlumno.get();
            updatedModuloAlumno.setAlumno(moduloAlumnoDetails.getAlumno());
            updatedModuloAlumno.setModulo(moduloAlumnoDetails.getModulo());
            return ResponseEntity.ok(moduloAlumnoRepository.save(updatedModuloAlumno));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModuloAlumno(@PathVariable Integer id) {
        if (moduloAlumnoRepository.existsById(id)) {
            moduloAlumnoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}