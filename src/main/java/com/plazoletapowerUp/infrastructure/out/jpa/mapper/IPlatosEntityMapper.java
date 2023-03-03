package com.plazoletapowerUp.infrastructure.out.jpa.mapper;

import com.plazoletapowerUp.domain.model.PlatoRestauranteModel;
import com.plazoletapowerUp.domain.model.PlatosModel;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.PlatosEntity;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.custom.IPlatoRestaurante;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatosEntityMapper {
    PlatosEntity toEntity(PlatosModel platosModel);
    PlatosModel toPlatosModel(PlatosEntity platosEntity);
    List<PlatosModel> toPlatosModelList(List<PlatosEntity> platosEntityList);
    List<PlatoRestauranteModel> toPlatoRestauranteModelList(List<IPlatoRestaurante> iPlatoRestauranteList);
}
