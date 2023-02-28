package com.plazoletapowerUp.application.handler;


import com.plazoletapowerUp.application.dto.request.RestauranteRequestDto;
import com.plazoletapowerUp.application.dto.response.RestaurantePageResponseDto;
import com.plazoletapowerUp.application.dto.response.RestauranteResponseDto;

import java.util.List;

public interface IRestauranteHandler {
    void saveRestaurante(RestauranteRequestDto restauranteRequestDto);
    RestaurantePageResponseDto findAllRestaurantes(Integer page);
}