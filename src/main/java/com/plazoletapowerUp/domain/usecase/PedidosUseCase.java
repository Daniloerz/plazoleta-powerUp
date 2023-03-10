package com.plazoletapowerUp.domain.usecase;

import com.plazoletapowerUp.domain.api.IPedidosServicePort;
import com.plazoletapowerUp.domain.model.PedidoModel;
import com.plazoletapowerUp.domain.model.PedidoPlatosModel;
import com.plazoletapowerUp.domain.spi.IPedidosPersistencePort;

import java.util.List;

public class PedidosUseCase implements IPedidosServicePort {

    private IPedidosPersistencePort pedidosPersistencePort;

    public PedidosUseCase(IPedidosPersistencePort pedidosPersistencePort) {
        this.pedidosPersistencePort = pedidosPersistencePort;
    }

    @Override
    public void savePedidoSP(PedidoModel pedidoModel, List<PedidoPlatosModel> pedidoPlatosModelList) {
        pedidosPersistencePort.savePedidosPP(pedidoModel, pedidoPlatosModelList);
    }
}
