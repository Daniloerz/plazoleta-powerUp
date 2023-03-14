package com.plazoletapowerUp.infrastructure.out.jpa.adapter;

import com.plazoletapowerUp.domain.model.RestauranteEmpleadoModel;
import com.plazoletapowerUp.domain.spi.IRestauranteEmpleadoPersistencePort;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.RestauranteEmpleadoEntity;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.IRestauranteEmpleadoEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IRestauranteEmpleadoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestauranteEmpleadoJpaAdapter implements IRestauranteEmpleadoPersistencePort {

    private IRestauranteEmpleadoEntityMapper restauranteEmpleadoEntityMapper;
    private IRestauranteEmpleadoRepository restauranteEmpleadoRepository;

    public RestauranteEmpleadoJpaAdapter(IRestauranteEmpleadoEntityMapper restauranteEmpleadoEntityMapper,
                                         IRestauranteEmpleadoRepository restauranteEmpleadoRepository) {
        this.restauranteEmpleadoEntityMapper = restauranteEmpleadoEntityMapper;
        this.restauranteEmpleadoRepository = restauranteEmpleadoRepository;
    }

    @Override
    public void saveRestauranteEmpleado(RestauranteEmpleadoModel restauranteEmpleadoModel) {
        RestauranteEmpleadoEntity restauranteEmpleadoEntity =
                restauranteEmpleadoEntityMapper
                        .toRestauranteEmpleadoEntity(restauranteEmpleadoModel);
        restauranteEmpleadoRepository.save(restauranteEmpleadoEntity);
    }
}
