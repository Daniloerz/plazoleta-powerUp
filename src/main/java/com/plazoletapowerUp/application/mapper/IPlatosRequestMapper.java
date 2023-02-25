package com.plazoletapowerUp.application.mapper;

import com.plazoletapowerUp.application.dto.request.PlatosRequestActiveDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestPatchDto;
import com.plazoletapowerUp.domain.model.PlatosModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPlatosRequestMapper {
    PlatosModel toPlatosModel(PlatosRequestDto platosRequestDto);
    PlatosModel toPlatosModel(PlatosRequestPatchDto platosRequestPatchDto);
    PlatosModel toPlatosModel(PlatosRequestActiveDto platosRequestActiveDto);

}
