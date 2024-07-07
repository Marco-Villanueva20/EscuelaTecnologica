package com.escuelatecnologica.core.controllers;

import com.escuelatecnologica.core.entities.Alumno;
import com.escuelatecnologica.core.repositories.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

	
	
    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Integer id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        return alumno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Alumno createAlumno(@RequestBody Alumno alumno) {
    
        return alumnoRepository.save(alumno);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Integer id, @RequestBody Alumno alumnoDetails) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        if (alumno.isPresent()) {
            Alumno updatedAlumno = alumno.get();
            updatedAlumno.setNombre(alumnoDetails.getNombre());
            updatedAlumno.setApellidoP(alumnoDetails.getApellidoP());
            updatedAlumno.setApellidoM(alumnoDetails.getApellidoM());
            updatedAlumno.setFechaNac(alumnoDetails.getFechaNac());
            updatedAlumno.setDelegado(alumnoDetails.isDelegado());
            return ResponseEntity.ok(alumnoRepository.save(updatedAlumno));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Integer id) {
        if (alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}