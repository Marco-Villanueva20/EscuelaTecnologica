package com.escuelatecnologica.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alumno")
@Data
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expediente;

    @Column(length = 25, nullable = false)
    private String nombre;

    @Column(name = "apellido_p",length = 25, nullable = false)
    private String apellidoP;

    @Column(name = "apellido_m",length = 25, nullable = false)
    private String apellidoM;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_nac",nullable = false)
    private Date fechaNac;

	@Column(nullable = false)
    private boolean delegado;

	@OneToMany(mappedBy = "alumno",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ModuloAlumno> moduloAlumnos;

}
