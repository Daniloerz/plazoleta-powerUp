package com.plazoletapowerUp.domain.usecase;

import com.plazoletapowerUp.domain.api.IPlatosServicePort;
import com.plazoletapowerUp.domain.exception.ValidationException;
import com.plazoletapowerUp.domain.model.CategoriaModel;
import com.plazoletapowerUp.domain.model.PlatosModel;
import com.plazoletapowerUp.domain.model.RestauranteModel;
import com.plazoletapowerUp.domain.spi.ICategoriaPersistencePort;
import com.plazoletapowerUp.domain.spi.IPlatosPersistencePort;
import com.plazoletapowerUp.domain.spi.IRestaurantePersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlatosUseCase implements IPlatosServicePort {

    public final Logger log = LoggerFactory.getLogger(RestauranteUseCase.class);
    private final IPlatosPersistencePort platosPersistencePort;
    private final ICategoriaPersistencePort categoriaPersistencePort;
    private final IRestaurantePersistencePort restaurantePersistencePort;


    public PlatosUseCase(IPlatosPersistencePort platosPersistencePort, ICategoriaPersistencePort categoriaPersistencePort, IRestaurantePersistencePort restaurantePersistencePort) {
        this.platosPersistencePort = platosPersistencePort;
        this.categoriaPersistencePort = categoriaPersistencePort;
        this.restaurantePersistencePort = restaurantePersistencePort;
    }

    @Override
    public void savePlatosSP(PlatosModel platosModel) {
        platosModel.setActivo(true);

        CategoriaModel categoriaModel = categoriaPersistencePort.findById(platosModel.getId_categoria());
        RestauranteModel restauranteModel = restaurantePersistencePort.findByIdPP(platosModel.getId_restaurante());
        this.validatePrecio(platosModel.getPrecio());

        platosModel.setId_categoria(categoriaModel.getId());
        platosModel.setId_restaurante(restauranteModel.getId());
        platosPersistencePort.savePlatoPP(platosModel);
    }

    @Override
    public PlatosModel updatePlatoByPriceDescriptionSP(PlatosModel platosModel) {
        PlatosModel platosModel1= platosPersistencePort.findPlatoById(platosModel);
        platosModel1.setPrecio(platosModel.getPrecio());
        platosModel1.setDescripcion(platosModel.getDescripcion());
        return platosPersistencePort.savePlatoPP(platosModel1);
    }

    @Override
    public PlatosModel updatePlatoActiveSP(PlatosModel platosModel) {
        PlatosModel platosModel1= platosPersistencePort.findPlatoById(platosModel);
        platosModel1.setActivo(platosModel.isActivo());
        return platosPersistencePort.savePlatoPP(platosModel1);
    }

    private void validatePrecio(Integer precio){
        if(precio <= 0) {
            log.error("El precio no debe ser menor o igual a $0");
            throw new ValidationException("El precio no debe ser menor o igual a $0");
        }
    }
}
