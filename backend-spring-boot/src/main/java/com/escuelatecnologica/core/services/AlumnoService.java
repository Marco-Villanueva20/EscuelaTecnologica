package com.escuelatecnologica.core.services;

import java.util.List;
import java.util.Optional;

import com.escuelatecnologica.core.entities.Alumno;

public interface AlumnoService {
	 	List<Alumno> getAllProducts();
	    Optional<Alumno> getAlumnosById(Long id);
	    Alumno createAlumno(Alumno alumno);
	    void deleteAlumno(Integer id);
}
