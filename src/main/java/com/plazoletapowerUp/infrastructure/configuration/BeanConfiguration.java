package com.plazoletapowerUp.infrastructure.configuration;

import com.plazoletapowerUp.domain.api.IPlatosServicePort;
import com.plazoletapowerUp.domain.api.IRestauranteServicePort;
import com.plazoletapowerUp.domain.spi.ICategoriaPersistencePort;
import com.plazoletapowerUp.domain.spi.IPlatosPersistencePort;
import com.plazoletapowerUp.domain.spi.IRestaurantePersistencePort;
import com.plazoletapowerUp.domain.usecase.PlatosUseCase;
import com.plazoletapowerUp.domain.usecase.RestauranteUseCase;
import com.plazoletapowerUp.infrastructure.out.jpa.adapter.CategoriaJpaAdapter;
import com.plazoletapowerUp.infrastructure.out.jpa.adapter.PlatosJpaAdapter;
import com.plazoletapowerUp.infrastructure.out.jpa.adapter.RestauranteJpaAdapter;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.ICategoriaEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.IPlatosEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.IRestauranteEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.ICategoriaRepository;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IPlatosRepository;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IRestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestauranteRepository restauranteRepository;
    private final IRestauranteEntityMapper restauranteEntityMapper;
    private final IPlatosRepository platosRepository;
    private final IPlatosEntityMapper platosEntityMapper;
    private final ICategoriaRepository categoriaRepository;
    private final ICategoriaEntityMapper categoriaEntityMapper;

    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort() {
        return new RestauranteJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }

    @Bean
    public IRestauranteServicePort restauranteServicePort() {
        return new RestauranteUseCase(restaurantePersistencePort());
    }

    @Bean
    public IPlatosPersistencePort platosPersistencePort() {
        return new PlatosJpaAdapter(platosRepository, platosEntityMapper);
    }

    @Bean
    public ICategoriaPersistencePort categoriaPersistencePort() {
        return new CategoriaJpaAdapter(categoriaRepository, categoriaEntityMapper);
    }

    @Bean
    public IPlatosServicePort platosServicePort() {
        return new PlatosUseCase(platosPersistencePort(), categoriaPersistencePort(), restaurantePersistencePort());
    }

}