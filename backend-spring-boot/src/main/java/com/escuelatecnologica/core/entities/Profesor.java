package com.escuelatecnologica.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profesor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Profesor {
    @Id
    @Column(length = 15)
    private String rfc;

    @Column(length = 25, nullable = false)
    private String nombre;

    @Column(name = "apellido_p",length = 25, nullable = false)
    private String apellidoP;

    @Column(name = "apellido_m",length = 25, nullable = false)
    private String apellidoM;

    @Column(length = 25)
    private String direccion;

    @Column(length = 10)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "imparte")
    private Modulo modulo;

}
