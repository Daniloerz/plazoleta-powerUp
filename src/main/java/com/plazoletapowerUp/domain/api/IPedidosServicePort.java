package com.plazoletapowerUp.domain.api;

import com.plazoletapowerUp.domain.model.PedidoModel;
import com.plazoletapowerUp.domain.model.PedidoPlatosModel;

import java.util.List;

public interface IPedidosServicePort {
    void savePedidoSP(PedidoModel pedidoModel, List<PedidoPlatosModel> pedidoPlatosModelList);
}
