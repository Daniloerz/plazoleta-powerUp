package com.plazoletapowerUp.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatosRequestDto {
    private Integer id_user;
    private Integer id_restaurante;
    private String nombre;
    private Integer precio;
    private String descripcion;
    private String urlImagen;
    private Integer id_categoria;
}
