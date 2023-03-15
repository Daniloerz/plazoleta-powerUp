package com.plazoletapowerUp.domain.spi;

import com.plazoletapowerUp.domain.model.PedidoModel;
import com.plazoletapowerUp.domain.model.PedidoPlatosModel;
import com.plazoletapowerUp.domain.model.PedidosPageableModel;

import java.util.List;

public interface IPedidosPersistencePort {
    void savePedidosPP(PedidoModel pedidoModel, List<PedidoPlatosModel> pedidoPlatosModelList);
    List<PedidoModel> findPedidosByIdClientePP(Integer idCliente);
    PedidosPageableModel findPedidoByEstado
            (Integer idEmpleado, Integer idRestaurante, String estado, Integer page, Integer numElemPage);
}
