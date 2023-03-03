package com.plazoletapowerUp.application.handler;

import com.plazoletapowerUp.application.dto.request.PlatosRequestDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestPatchDto;
import com.plazoletapowerUp.application.dto.response.PlatosPageResponseDto;
import com.plazoletapowerUp.domain.model.PlatosModel;

public interface IPlatosHandler {
    void savePlatos(PlatosRequestDto platosRequestDto);
    PlatosModel updatePlatoByPriceDescription(PlatosRequestPatchDto platosRequestPatchDto);
    PlatosModel updatePlatoActive(Integer id, Boolean active);
    PlatosPageResponseDto findPlatosByIdRestaurante(Integer id, Integer initPage, Integer numElementsPage);

}
