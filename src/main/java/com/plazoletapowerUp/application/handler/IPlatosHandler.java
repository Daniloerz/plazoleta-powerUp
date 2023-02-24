package com.plazoletapowerUp.application.handler;

import com.plazoletapowerUp.application.dto.request.PlatosRequestDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestPatchDto;
import com.plazoletapowerUp.domain.model.PlatosModel;

public interface IPlatosHandler {
    void savePlatos(PlatosRequestDto platosRequestDto);
    PlatosModel updatePlato(PlatosRequestPatchDto platosRequestPatchDto);
}
