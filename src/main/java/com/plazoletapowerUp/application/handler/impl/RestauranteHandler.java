package com.plazoletapowerUp.application.handler.impl;

import com.plazoletapowerUp.application.dto.request.RestauranteRequestDto;
import com.plazoletapowerUp.application.handler.IRestauranteHandler;
import com.plazoletapowerUp.application.mapper.IRestauranteRequestMapper;
import com.plazoletapowerUp.domain.api.IRestauranteServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestauranteHandler implements IRestauranteHandler {
    private final IRestauranteServicePort restauranteServicePort;
    private final IRestauranteRequestMapper restauranteRequestMapper;

    @Override
    public void saveRestaurante(RestauranteRequestDto restauranteRequestDto) {
        restauranteServicePort.saveRestaurante(restauranteRequestMapper.toObject(restauranteRequestDto));
    }
}
