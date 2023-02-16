package com.plazoletapowerUp.application.mapper;

import com.plazoletapowerUp.application.dto.request.RestauranteRequestDto;
import com.plazoletapowerUp.domain.model.RestauranteModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestauranteRequestMapper {
    RestauranteModel toObject(RestauranteRequestDto restauranteRequestDto);
}
