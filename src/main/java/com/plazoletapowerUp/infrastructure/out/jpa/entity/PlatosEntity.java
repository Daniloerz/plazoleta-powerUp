package com.plazoletapowerUp.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Platos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlatosEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id_plato")
    private Integer id;
    private String nombre;
    private Integer precio;
    private String descripcion;
    private String urlImagen;
    private Integer id_categoria;
    private boolean activo;
    private Integer id_restaurante;
}
