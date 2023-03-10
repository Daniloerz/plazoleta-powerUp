package com.plazoletapowerUp.infrastructure.out.jpa.mapper;

import com.plazoletapowerUp.domain.model.PedidoModel;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.PedidosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPedidosEntityMapper {
    PedidosEntity toEntity(PedidoModel pedidoModel);

}
