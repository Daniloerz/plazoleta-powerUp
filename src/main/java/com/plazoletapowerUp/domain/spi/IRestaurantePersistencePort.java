package com.plazoletapowerUp.domain.spi;


import com.plazoletapowerUp.domain.model.RestauranteModel;
import com.plazoletapowerUp.domain.model.RestaurantePageable;

import java.util.List;

public interface IRestaurantePersistencePort {
    RestauranteModel saveRestaurante(RestauranteModel restauranteModel);
    RestauranteModel findByIdPP(Integer id);
    RestaurantePageable findAllRestaurantesPP(Integer page);

}