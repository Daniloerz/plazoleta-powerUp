package com.plazoletapowerUp.domain.usecase;

import com.plazoletapowerUp.domain.api.IRestauranteServicePort;
import com.plazoletapowerUp.domain.exception.ValidationException;
import com.plazoletapowerUp.domain.model.RestauranteModel;
import com.plazoletapowerUp.domain.model.RestaurantePageable;
import com.plazoletapowerUp.domain.spi.IRestaurantePersistencePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Pattern;

public class RestauranteUseCase implements IRestauranteServicePort {

    public final Logger log = LoggerFactory.getLogger(RestauranteUseCase.class);

    private final IRestaurantePersistencePort restaurantePersistencePort;



    public RestauranteUseCase(IRestaurantePersistencePort restaurantePersistencePort) {
        this.restaurantePersistencePort = restaurantePersistencePort;
    }

    @Override
    public void saveRestauranteSP(RestauranteModel restauranteModel) {

        this.validateRestaurante(restauranteModel);
        restaurantePersistencePort.saveRestaurante(restauranteModel);
    }

    @Override
    public RestaurantePageable findAllRestaurantesSP(Integer page) {
        return  restaurantePersistencePort.findAllRestaurantesPP(page);

    }

    private void validateRestaurante(RestauranteModel restauranteModel) {
        this.validateNombreRestaurante(restauranteModel.getNombre());
        this.validateTelefonoRestaurante(restauranteModel.getTelefono());
        this.validateNit(restauranteModel.getNit());
    }

    private void validateNombreRestaurante(String nombreRestaurante){
        String regex = "^.*\\D+.*$";

        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(nombreRestaurante).matches()){
            log.error("Nombre inválido invalido");

            throw new ValidationException("Nombre inválido invalido");
        }
    }


    private void validateTelefonoRestaurante(String telefonoRestaurante){
        String regex = "[+]?[0-9]{1,13}";

        Pattern pattern = Pattern.compile(regex);
        if(!pattern.matcher(telefonoRestaurante).matches()){
            log.error("Telefono invalido");
            throw new ValidationException("Telefono invalido");
        }
    }

    private void validateNit(String nit){
        String regex = "[0-9]*";

        Pattern pattern = Pattern.compile(regex);

        if(!pattern.matcher(nit).matches()){
            log.error("Nit invalido");
            throw new ValidationException("Nit invalido");
        }
    }
}