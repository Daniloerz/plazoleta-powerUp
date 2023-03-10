package com.plazoletapowerUp.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RestauranteEmpleado")
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteEmpleado {
    @Id
    private Integer idRestaurante;
    private Integer idPersona;
    private String field;
}
