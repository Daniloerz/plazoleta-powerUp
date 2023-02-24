package com.plazoletapowerUp.domain.spi;

import com.plazoletapowerUp.domain.model.PlatosModel;

public interface IPlatosPersistencePort {
    PlatosModel savePlatoPP(PlatosModel platosModel);
    PlatosModel findPlatoById(PlatosModel platosModel);
}
