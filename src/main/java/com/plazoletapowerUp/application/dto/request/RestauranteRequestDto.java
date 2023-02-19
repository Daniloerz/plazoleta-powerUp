package com.plazoletapowerUp.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteRequestDto {
    private String nombre;
    private String nit;
    private String direccion;
    private String telefono;
    private String urlLogo;
    private Integer idPropietario;
}
