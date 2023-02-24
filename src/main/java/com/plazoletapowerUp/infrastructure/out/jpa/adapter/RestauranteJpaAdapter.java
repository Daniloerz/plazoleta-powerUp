package com.plazoletapowerUp.infrastructure.out.jpa.adapter;

import com.plazoletapowerUp.domain.model.RestauranteModel;
import com.plazoletapowerUp.domain.spi.IRestaurantePersistencePort;
import com.plazoletapowerUp.infrastructure.exception.NoDataFoundException;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.RestauranteEntity;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.IRestauranteEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RestauranteJpaAdapter implements IRestaurantePersistencePort {

    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;

    @Override
    public RestauranteModel saveRestaurante(RestauranteModel restauranteModel) {
        RestauranteEntity restauranteEntity = restauranteRepository.save(restauranteEntityMapper.toEntity(restauranteModel));
        return restauranteEntityMapper.toRestauranteModel(restauranteEntity);
    }

    @Override
    public RestauranteModel findByIdPP(Integer id) throws NoDataFoundException {
        Optional<RestauranteEntity> restauranteEntityOptional = restauranteRepository.findById(id);
        if (restauranteEntityOptional.isPresent()){
            RestauranteEntity restauranteEntity = restauranteEntityOptional.get();
            return restauranteEntityMapper.toRestauranteModel(restauranteEntity);
        } else {
            throw new NoDataFoundException();
        }
    }
}