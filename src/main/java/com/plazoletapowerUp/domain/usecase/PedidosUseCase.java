package com.plazoletapowerUp.domain.usecase;

import com.plazoletapowerUp.domain.api.IPedidosServicePort;
import com.plazoletapowerUp.domain.client.IUsuarioClientPort;
import com.plazoletapowerUp.domain.exception.ValidationException;
import com.plazoletapowerUp.domain.model.PedidoModel;
import com.plazoletapowerUp.domain.model.PedidoPlatosModel;
import com.plazoletapowerUp.domain.model.RestauranteModel;
import com.plazoletapowerUp.domain.responseDtoModel.UsuarioResponseDtoModel;
import com.plazoletapowerUp.domain.spi.IPedidosPersistencePort;
import com.plazoletapowerUp.domain.spi.IRestaurantePersistencePort;
import com.plazoletapowerUp.infrastructure.enums.PedidoEstadoEnum;
import com.plazoletapowerUp.infrastructure.enums.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PedidosUseCase implements IPedidosServicePort {

    public final Logger log = LoggerFactory.getLogger(RestauranteUseCase.class);

    private IPedidosPersistencePort pedidosPersistencePort;
    private IRestaurantePersistencePort restaurantePersistencePort;
    private IUsuarioClientPort usuarioClientPort;

    public PedidosUseCase(IPedidosPersistencePort pedidosPersistencePort,
                          IRestaurantePersistencePort restaurantePersistencePort,
                          IUsuarioClientPort usuarioClientPort) {
        this.pedidosPersistencePort = pedidosPersistencePort;
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.usuarioClientPort = usuarioClientPort;
    }

    @Override
    public void savePedidoSP(PedidoModel pedidoModel, List<PedidoPlatosModel> pedidoPlatosModelList) {
       this.validatePedido(pedidoModel);
        pedidosPersistencePort.savePedidosPP(pedidoModel, pedidoPlatosModelList);
    }

    public void validatePedido(PedidoModel pedidoModel){
        this.validateRestaurante(pedidoModel);
        this.validatePedidosCliente(pedidoModel);
        this.validateEmpleado(pedidoModel);
    }

    public void validatePedidosCliente (PedidoModel pedidoModel){

        List<PedidoModel> pedidoModelList = pedidosPersistencePort
                .findPedidosByIdClientePP(pedidoModel.getIdCliente());
        boolean pedidoEncontrado =  pedidoModelList.stream().
                anyMatch(pedidoCliente -> (pedidoCliente.getEstado()
                        .equals(PedidoEstadoEnum.PENDIENTE.getDbValue())
                        || pedidoCliente.getEstado()
                        .equals(PedidoEstadoEnum.EN_PREPARACION.getDbValue())
                        || pedidoCliente.getEstado()
                        .equals(PedidoEstadoEnum.LISTO.getDbValue())));
        if(pedidoEncontrado){
            log.error("El cliente tiene un pedido en proceso");
            throw new ValidationException("El cliente tiene un pedido en proceso");
        }
    }

    private void validateRestaurante(PedidoModel pedidoModel) {
        RestauranteModel restauranteModel = restaurantePersistencePort.findByIdPP(pedidoModel.getIdRestaurante());
        if (restauranteModel == null){
            log.error("Restaurante no encontrado");
            throw new ValidationException("Restaurante no encontrado");
        }
    }

    private void validateEmpleado(PedidoModel pedidoModel){
        final UsuarioResponseDtoModel usuarioById = usuarioClientPort.findUsuarioById(pedidoModel.getIdEmpleado());
        if(usuarioById == null){
            log.error("Empleado no encontrado");
            throw new ValidationException("Empleado no encontrado");
        }
        if(!usuarioById.getRole().getNombre().equalsIgnoreCase(RoleEnum.EMPLEADO.getDbName())){
            log.error("Role invalido {}", usuarioById.getRole().getNombre());
            throw new ValidationException("Id empleado no se corresponde con el rol \"Empleado\"");
        }
    }

}
