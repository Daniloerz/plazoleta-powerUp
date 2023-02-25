package com.plazoletapowerUp.domain.api;

import com.plazoletapowerUp.domain.model.PlatosModel;

public interface IPlatosServicePort {

    void savePlatosSP(PlatosModel platosModel);

    PlatosModel updatePlatoByPriceDescriptionSP(PlatosModel platosModel);
    PlatosModel updatePlatoActiveSP(PlatosModel platosModel);
}
