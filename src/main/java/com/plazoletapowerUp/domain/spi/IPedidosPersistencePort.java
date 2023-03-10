package com.plazoletapowerUp.domain.spi;

import com.plazoletapowerUp.domain.model.PedidoModel;
import com.plazoletapowerUp.domain.model.PedidoPlatosModel;

import java.util.List;

public interface IPedidosPersistencePort {
    void savePedidosPP(PedidoModel pedidoModel, List<PedidoPlatosModel> pedidoPlatosModelList);
}
