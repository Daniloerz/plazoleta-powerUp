package com.plazoletapowerUp.domain.api;


import com.plazoletapowerUp.domain.model.RestauranteModel;
import com.plazoletapowerUp.domain.model.RestaurantePageable;

import java.util.List;

public interface IRestauranteServicePort {

    void saveRestauranteSP(RestauranteModel restauranteModel);
    RestaurantePageable findAllRestaurantesSP(Integer page);
}