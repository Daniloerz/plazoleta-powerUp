package com.plazoletapowerUp.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteResponseDto {

    @JsonIgnore
    private Integer id;
    private String nombre;
    private String urlLogo;
}
