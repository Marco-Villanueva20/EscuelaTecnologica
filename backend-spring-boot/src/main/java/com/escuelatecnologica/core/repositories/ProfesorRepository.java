package com.escuelatecnologica.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escuelatecnologica.core.entities.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, String> {

}
