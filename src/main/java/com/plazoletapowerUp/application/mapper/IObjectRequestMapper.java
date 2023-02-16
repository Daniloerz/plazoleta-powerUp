package com.plazoletapowerUp.application.mapper;

import com.plazoletapowerUp.application.dto.request.ObjectRequestDto;
import com.plazoletapowerUp.domain.model.ObjectModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IObjectRequestMapper {


    ObjectModel toObject(ObjectRequestDto objectRequestDto);
}
