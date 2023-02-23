package com.plazoletapowerUp.infrastructure.out.jpa.mapper;

import com.plazoletapowerUp.domain.model.PlatosModel;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.PlatosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatosEntityMapper {
    PlatosEntity toEntity(PlatosModel platosModel);
    PlatosModel toPlatosModel(PlatosEntity platosEntity);
}
