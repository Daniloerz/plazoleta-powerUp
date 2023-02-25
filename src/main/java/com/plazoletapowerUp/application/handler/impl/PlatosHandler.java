package com.plazoletapowerUp.application.handler.impl;

import com.plazoletapowerUp.application.dto.request.PlatosRequestActiveDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestPatchDto;
import com.plazoletapowerUp.application.handler.IPlatosHandler;
import com.plazoletapowerUp.application.mapper.IPlatosRequestMapper;
import com.plazoletapowerUp.domain.api.IPlatosServicePort;
import com.plazoletapowerUp.domain.model.PlatosModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlatosHandler implements IPlatosHandler {

    private final IPlatosServicePort platosServicePort;
    private final IPlatosRequestMapper platosRequestMapper;

    @Override
    public void savePlatos(PlatosRequestDto platosRequestDto) {
        platosServicePort.savePlatosSP(platosRequestMapper.toPlatosModel(platosRequestDto));
    }

    @Override
    public PlatosModel updatePlatoByPriceDescription(PlatosRequestPatchDto platosRequestPatchDto) {
        return platosServicePort.updatePlatoByPriceDescriptionSP(platosRequestMapper.toPlatosModel(platosRequestPatchDto));
    }

    @Override
    public PlatosModel updatePlatoActive(PlatosRequestActiveDto platosRequestActiveDto) {
        return platosServicePort.updatePlatoActiveSP(platosRequestMapper.toPlatosModel(platosRequestActiveDto));
    }
}
