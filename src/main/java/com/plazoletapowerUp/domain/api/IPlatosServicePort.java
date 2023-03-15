package com.plazoletapowerUp.domain.api;

import com.plazoletapowerUp.domain.model.PlatosModel;
import com.plazoletapowerUp.domain.model.PlatosRestaurantePageableModel;

public interface IPlatosServicePort {

    void savePlatosSP(PlatosModel platosModel, Integer idPropietario);

    PlatosModel updatePlatoByPriceDescriptionSP(PlatosModel platosModel);
    PlatosModel updatePlatoActiveSP(Integer idPropietario,Integer idPlato, Boolean isActive);
    PlatosRestaurantePageableModel findPlatosByRestaurante(Integer id, Integer initPage, Integer numElementsPage);

}
