package com.plazoletapowerUp.infrastructure.configuration;

import com.plazoletapowerUp.domain.api.IObjectServicePort;
import com.plazoletapowerUp.domain.spi.IObjectPersistencePort;
import com.plazoletapowerUp.domain.usecase.ObjectUseCase;
import com.plazoletapowerUp.infrastructure.out.jpa.adapter.ObjectJpaAdapter;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.IObjectEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IObjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IObjectRepository objectRepository;
    private final IObjectEntityMapper objectEntityMapper;

    @Bean
    public IObjectPersistencePort objectPersistencePort() {
        return new ObjectJpaAdapter(objectRepository, objectEntityMapper);
    }

    @Bean
    public IObjectServicePort objectServicePort() {
        return new ObjectUseCase(objectPersistencePort());
    }
}