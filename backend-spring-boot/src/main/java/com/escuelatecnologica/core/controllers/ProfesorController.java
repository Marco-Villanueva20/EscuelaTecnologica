package com.escuelatecnologica.core.controllers;

import com.escuelatecnologica.core.entities.Profesor;
import com.escuelatecnologica.core.repositories.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping
    public List<Profesor> getAllProfesores() {
        return profesorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable String id) {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        return profesor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Profesor createProfesor(@RequestBody Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable String id, @RequestBody Profesor profesorDetails) {
        Optional<Profesor> profesor = profesorRepository.findById(id);
        if (profesor.isPresent()) {
            Profesor updatedProfesor = profesor.get();
            updatedProfesor.setNombre(profesorDetails.getNombre());
            updatedProfesor.setApellidoP(profesorDetails.getApellidoP());
            updatedProfesor.setApellidoM(profesorDetails.getApellidoM());
            updatedProfesor.setDireccion(profesorDetails.getDireccion());
            updatedProfesor.setTelefono(profesorDetails.getTelefono());
            updatedProfesor.setModulo(profesorDetails.getModulo());
            return ResponseEntity.ok(profesorRepository.save(updatedProfesor));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable String id) {
        if (profesorRepository.existsById(id)) {
            profesorRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
