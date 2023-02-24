package com.plazoletapowerUp.infrastructure.out.jpa.adapter;

import com.plazoletapowerUp.domain.model.PlatosModel;
import com.plazoletapowerUp.domain.spi.IPlatosPersistencePort;
import com.plazoletapowerUp.infrastructure.exception.NoDataFoundException;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.PlatosEntity;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.IPlatosEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IPlatosRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PlatosJpaAdapter implements IPlatosPersistencePort {

    private final IPlatosRepository platosRepository;
    private final IPlatosEntityMapper platosEntityMapper;

    @Override
    public PlatosModel savePlatoPP(PlatosModel platosModel) {
        PlatosEntity platosEntity = platosRepository.save(platosEntityMapper.toEntity(platosModel));
        return platosEntityMapper.toPlatosModel(platosEntity);
    }

    @Override
    public PlatosModel findPlatoById(PlatosModel platosModel) {
        Optional<PlatosEntity> platosEntityOptional = platosRepository.findById(platosModel.getId());
        if (platosEntityOptional.isPresent()){
            PlatosEntity platosEntity = platosEntityOptional.get();
            return platosEntityMapper.toPlatosModel(platosEntity);
        } else {
            throw new NoDataFoundException();
        }
    }
}
