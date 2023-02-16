package com.plazoletapowerUp.domain.spi;


import com.plazoletapowerUp.domain.model.RestauranteModel;

public interface IRestaurantePersistencePort {
    RestauranteModel saveRestaurante(RestauranteModel restauranteModel);

}