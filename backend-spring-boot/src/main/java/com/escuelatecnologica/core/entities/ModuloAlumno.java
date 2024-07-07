package com.escuelatecnologica.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "modulo_alumno")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModuloAlumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmodulo_alumno")
    private int idModuloAlumno;

    @JoinColumn(name = "matriculado_a_idx", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Alumno alumno;


    @JoinColumn(name = "matriculado_m_idx", nullable = false)
    @ManyToOne
    private Modulo modulo;
}
