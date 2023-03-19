package com.plazoletapowerUp.domain.usecase;

import com.plazoletapowerUp.domain.api.IPedidosServicePort;
import com.plazoletapowerUp.domain.client.ITwilioClientPort;
import com.plazoletapowerUp.domain.client.IUsuarioClientPort;
import com.plazoletapowerUp.domain.exception.ValidationException;
import com.plazoletapowerUp.domain.model.*;
import com.plazoletapowerUp.domain.responseDtoModel.UsuarioResponseDtoModel;
import com.plazoletapowerUp.domain.spi.IPedidosPersistencePort;
import com.plazoletapowerUp.domain.spi.IRestauranteEmpleadoPersistencePort;
import com.plazoletapowerUp.domain.spi.IRestaurantePersistencePort;
import com.plazoletapowerUp.infrastructure.enums.PedidoEstadoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PedidosUseCase implements IPedidosServicePort {

    public final Logger log = LoggerFactory.getLogger(RestauranteUseCase.class);

    private IPedidosPersistencePort pedidosPersistencePort;
    private IRestaurantePersistencePort restaurantePersistencePort;
    private IRestauranteEmpleadoPersistencePort restauranteEmpleadoPersistencePort;
    private ITwilioClientPort twilioClientPort;
    private IUsuarioClientPort usuarioClientPort;


    public PedidosUseCase(IPedidosPersistencePort pedidosPersistencePort,
                          IRestaurantePersistencePort restaurantePersistencePort,
                          IRestauranteEmpleadoPersistencePort restauranteEmpleadoPersistencePort,
                          ITwilioClientPort twilioClientPort, IUsuarioClientPort usuarioClientPort) {
        this.pedidosPersistencePort = pedidosPersistencePort;
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.restauranteEmpleadoPersistencePort = restauranteEmpleadoPersistencePort;
        this.twilioClientPort = twilioClientPort;
        this.usuarioClientPort = usuarioClientPort;
    }

    @Override
    public void savePedidoSP(PedidoModel pedidoModel, List<PedidoPlatosModel> pedidoPlatosModelList) {
       this.validatePedido(pedidoModel);
        pedidosPersistencePort.savePedidosPP(pedidoModel, pedidoPlatosModelList);
    }

    @Override
    public PedidosPageableModel findPedidosByEstado(Integer idEmpleado,
                                                    Integer idRestaurante,
                                                    String estado,
                                                    Integer page,
                                                    Integer numElemPage) {
        this.validateRestauranteEmpleado(idEmpleado, idRestaurante);
        return pedidosPersistencePort.findPedidoByEstado
                (idEmpleado, idRestaurante, estado, page, numElemPage);
    }

    @Override
    public void updateEmpleadoAndEstado(Integer idEmpleado, Integer idPedido, String estado) {
        RestauranteEmpleadoModel restauranteEmpleadoModel =
                this.validateRestauranteEmpleado(idEmpleado);
        PedidoModel pedidoModel = pedidosPersistencePort.findPedidoById(idPedido);
        if(!(restauranteEmpleadoModel.getIdRestaurante() == pedidoModel.getIdRestaurante())){
            log.error("Empleado no autorizado");
            throw new ValidationException("Empleado no autorizado");
        }
        pedidoModel.setIdEmpleado(idEmpleado);
        pedidoModel.setEstado(estado);

        pedidosPersistencePort.savePedido(pedidoModel);
    }

    @Override
    public void updatePedidoToReady(Integer idPedido) {
        PedidoModel pedidoById = pedidosPersistencePort.findPedidoById(idPedido);
        pedidoById.setEstado(PedidoEstadoEnum.LISTO.getDbValue());
        pedidoById.setCodigoEntrega(buildTwilioCode());
        pedidosPersistencePort.savePedido(pedidoById);

        this.sendMessage(pedidoById.getIdCliente(), pedidoById.getCodigoEntrega());
    }

    @Override
    public void updatePedidoToDelivered(Integer idPedido, Integer idCliente, String codigoEntrega) {
        PedidoModel pedidoModel = pedidosPersistencePort.findPedidoById(idPedido);
        this.validatePedidoDeCliente(pedidoModel.getIdCliente(), idCliente);
        this.isPedidoListo(pedidoModel);
        pedidosPersistencePort.findPedidoByCodigoEntrega(codigoEntrega);
        pedidoModel.setEstado(PedidoEstadoEnum.ENTREGADO.getDbValue());
        pedidosPersistencePort.savePedido(pedidoModel);
    }

    public void validatePedido(PedidoModel pedidoModel){
        this.validateRestaurante(pedidoModel);
        this.validatePedidosEnProcesoCliente(pedidoModel);
    }

    public void validatePedidosEnProcesoCliente(PedidoModel pedidoModel){

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

    private void validateRestauranteEmpleado(Integer idEmpleado, Integer idRestaurante){
        RestauranteEmpleadoModel restauranteEmpleadoModel =
                restauranteEmpleadoPersistencePort.findEmpleadoById(idEmpleado);
        if(restauranteEmpleadoModel == null){
            log.error("Empleado no encontrado");
            throw new ValidationException("Empleado no encontrado");
        }
        if (!(restauranteEmpleadoModel.getIdRestaurante() == idRestaurante)){
            log.error("Empleado no autorizado para obtener pedidos de este restautante");
            throw new ValidationException("Empleado no autorizado para obtener pedidos de este restautante");
        }
    }

    private RestauranteEmpleadoModel validateRestauranteEmpleado(Integer idEmpleado){
        RestauranteEmpleadoModel restauranteEmpleadoModel =
                restauranteEmpleadoPersistencePort.findEmpleadoById(idEmpleado);
        if(restauranteEmpleadoModel == null){
            log.error("Empleado no encontrado");
            throw new ValidationException("Empleado no encontrado");
        }
        return restauranteEmpleadoModel;
    }


    private String buildTwilioCode() {
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String cadena = "";
        for (int x = 0; x < 5; x++) {
            int indiceAleatorio = getNumeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }

    private int getNumeroAleatorioEnRango(int minimo, int maximo) {
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

    private void sendMessage (Integer idCliente, String codigo){
        try{
            UsuarioResponseDtoModel usuarioById = usuarioClientPort.findUsuarioById(idCliente);
            MessageModel messageModel = new MessageModel(usuarioById.getCelular(), codigo);
            twilioClientPort.sendMessage(messageModel);
        } catch (Exception e){
            log.error("Error invocando twilio service", e);
            throw new RuntimeException(e);
        }

    }

    private void validatePedidoDeCliente (Integer idClientePedido, Integer idCliente){
        if(idClientePedido != idCliente){
            log.error("El cliente no se corresponde con el pedido");
            throw new ValidationException("El cliente no se corresponde con el pedido");
        }
    }

    private void isPedidoListo (PedidoModel pedidoModel){
        if(!pedidoModel.getEstado().equals(PedidoEstadoEnum.LISTO.getDbValue())){
            log.error("El estado del pedido debe ser " + PedidoEstadoEnum.LISTO.getDbValue().toUpperCase());
            throw new ValidationException("El estado del pedido debe ser "
                    + PedidoEstadoEnum.LISTO.getDbValue().toUpperCase());
        }
    }


}
