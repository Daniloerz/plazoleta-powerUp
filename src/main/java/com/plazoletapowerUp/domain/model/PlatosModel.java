package com.plazoletapowerUp.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatosModel {
    private Integer id;
    private String nombre;
    private Integer precio;
    private String descripcion;
    private String urlImagen;
    private Integer id_categoria;
    private boolean activo;
    private Integer id_restaurante;

}
