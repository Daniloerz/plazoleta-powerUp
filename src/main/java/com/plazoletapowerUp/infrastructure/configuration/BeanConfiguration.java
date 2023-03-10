package com.plazoletapowerUp.infrastructure.configuration;

import com.plazoletapowerUp.domain.api.IPedidosServicePort;
import com.plazoletapowerUp.domain.api.IPlatosServicePort;
import com.plazoletapowerUp.domain.api.IRestauranteServicePort;
import com.plazoletapowerUp.domain.client.IUsuarioClientPort;
import com.plazoletapowerUp.domain.spi.ICategoriaPersistencePort;
import com.plazoletapowerUp.domain.spi.IPedidosPersistencePort;
import com.plazoletapowerUp.domain.spi.IPlatosPersistencePort;
import com.plazoletapowerUp.domain.spi.IRestaurantePersistencePort;
import com.plazoletapowerUp.domain.usecase.PedidosUseCase;
import com.plazoletapowerUp.domain.usecase.PlatosUseCase;
import com.plazoletapowerUp.domain.usecase.RestauranteUseCase;
import com.plazoletapowerUp.infrastructure.out.feing.IUsuarioRestClient;
import com.plazoletapowerUp.infrastructure.out.feing.adapter.UsuarioClientAdapter;
import com.plazoletapowerUp.infrastructure.out.feing.mapper.IUsuarioResponseMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.adapter.CategoriaJpaAdapter;
import com.plazoletapowerUp.infrastructure.out.jpa.adapter.PedidosJpaAdapter;
import com.plazoletapowerUp.infrastructure.out.jpa.adapter.PlatosJpaAdapter;
import com.plazoletapowerUp.infrastructure.out.jpa.adapter.RestauranteJpaAdapter;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.ICategoriaEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.IPlatosEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.IRestauranteEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.*;
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
    private final IPedidosRepository pedidosRepository;
    private final IPedidosPlatosRepository pedidosPlatosRepository;
    private final IUsuarioRestClient usuarioRestClient;
    private final IUsuarioResponseMapper usuarioResponseMapper;

    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort() {
        return new RestauranteJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }

    @Bean
    public IUsuarioClientPort usuarioClientPort() {
        return new UsuarioClientAdapter(usuarioRestClient, usuarioResponseMapper);
    }

    @Bean
    public IRestauranteServicePort restauranteServicePort() {
        return new RestauranteUseCase(restaurantePersistencePort(), usuarioClientPort());
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

    @Bean
    public IPedidosPersistencePort pedidosPersistencePort() {
        return new PedidosJpaAdapter(pedidosRepository, pedidosPlatosRepository, platosRepository);
    }

    @Bean
    public IPedidosServicePort pedidosServicePort() {

        return new PedidosUseCase(pedidosPersistencePort());
    }

}