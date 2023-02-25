package com.plazoletapowerUp.application.handler;

import com.plazoletapowerUp.application.dto.request.PlatosRequestActiveDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestPatchDto;
import com.plazoletapowerUp.domain.model.PlatosModel;

public interface IPlatosHandler {
    void savePlatos(PlatosRequestDto platosRequestDto);
    PlatosModel updatePlatoByPriceDescription(PlatosRequestPatchDto platosRequestPatchDto);
    PlatosModel updatePlatoActive(PlatosRequestActiveDto platosRequestActiveDto);
}
