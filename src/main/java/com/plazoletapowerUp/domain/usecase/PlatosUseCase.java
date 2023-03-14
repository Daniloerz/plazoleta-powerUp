package com.plazoletapowerUp.domain.usecase;

import com.plazoletapowerUp.domain.api.IPlatosServicePort;
import com.plazoletapowerUp.domain.client.IUsuarioClientPort;
import com.plazoletapowerUp.domain.exception.ValidationException;
import com.plazoletapowerUp.domain.model.CategoriaModel;
import com.plazoletapowerUp.domain.model.PlatosModel;
import com.plazoletapowerUp.domain.model.PlatosRestaurantePageableModel;
import com.plazoletapowerUp.domain.model.RestauranteModel;
import com.plazoletapowerUp.domain.responseDtoModel.UsuarioResponseDtoModel;
import com.plazoletapowerUp.domain.spi.ICategoriaPersistencePort;
import com.plazoletapowerUp.domain.spi.IPlatosPersistencePort;
import com.plazoletapowerUp.domain.spi.IRestaurantePersistencePort;
import com.plazoletapowerUp.infrastructure.enums.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlatosUseCase implements IPlatosServicePort {

    public final Logger log = LoggerFactory.getLogger(RestauranteUseCase.class);
    private final IPlatosPersistencePort platosPersistencePort;
    private final ICategoriaPersistencePort categoriaPersistencePort;
    private final IRestaurantePersistencePort restaurantePersistencePort;
    private final IUsuarioClientPort usuarioClientPort;

    public PlatosUseCase(IPlatosPersistencePort platosPersistencePort,
                         ICategoriaPersistencePort categoriaPersistencePort,
                         IRestaurantePersistencePort restaurantePersistencePort,
                         IUsuarioClientPort usuarioClientPort) {
        this.platosPersistencePort = platosPersistencePort;
        this.categoriaPersistencePort = categoriaPersistencePort;
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.usuarioClientPort = usuarioClientPort;
    }


    @Override
    public void savePlatosSP(PlatosModel platosModel, Integer idPropietario) {
        this.validateRole(idPropietario);
        platosModel.setActivo(true);

        CategoriaModel categoriaModel = categoriaPersistencePort.findById(platosModel.getIdCategoria());
        RestauranteModel restauranteModel = restaurantePersistencePort.findByIdPP(platosModel.getIdRestaurante());
        this.validatePrecio(platosModel.getPrecio());

        platosModel.setIdCategoria(categoriaModel.getId());
        platosModel.setIdRestaurante(restauranteModel.getId());
        platosPersistencePort.savePlatoPP(platosModel);
    }

    @Override
    public PlatosModel updatePlatoByPriceDescriptionSP(PlatosModel platosModel) {
        PlatosModel platosModel1 = platosPersistencePort.findPlatoById(platosModel);
        platosModel1.setPrecio(platosModel.getPrecio());
        platosModel1.setDescripcion(platosModel.getDescripcion());
        return platosPersistencePort.savePlatoPP(platosModel1);
    }

    @Override
    public PlatosModel updatePlatoActiveSP(Integer id, Boolean isActive) {
        PlatosModel platosModel1 = platosPersistencePort.findPlatoById(id);
        platosModel1.setActivo(isActive);
        return platosPersistencePort.savePlatoPP(platosModel1);
    }

    @Override
    public PlatosRestaurantePageableModel findPlatosByRestaurante(Integer id, Integer initPage, Integer numElementsPage) {
        return platosPersistencePort.findPlatosByIdAndPageable(id, initPage, numElementsPage);
    }

    private void validatePrecio(Integer precio) {
        if (precio <= 0) {
            log.error("El precio no debe ser menor o igual a $0");
            throw new ValidationException("El precio no debe ser menor o igual a $0");
        }
    }

    private void validateRole(Integer idPropietario) {
        final UsuarioResponseDtoModel usuarioById = usuarioClientPort.findUsuarioById(idPropietario);
        if (!usuarioById.getRole().getNombre().equalsIgnoreCase(RoleEnum.PROPIETARIO.getDbName())) {
            log.error("Role invalido {}", usuarioById.getRole().getNombre());
            throw new ValidationException("Role no valido para crear plato");
        }
    }
}
