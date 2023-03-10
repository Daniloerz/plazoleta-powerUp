package com.plazoletapowerUp.infrastructure.out.jpa.adapter;

import com.plazoletapowerUp.domain.model.PedidoModel;
import com.plazoletapowerUp.domain.model.PedidoPlatosModel;
import com.plazoletapowerUp.domain.spi.IPedidosPersistencePort;
import com.plazoletapowerUp.infrastructure.enums.PlatoEstadoEnum;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.PedidoPlatosEntity;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.PedidosEntity;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IPedidosPlatosRepository;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IPedidosRepository;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IPlatosRepository;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PedidosJpaAdapter implements IPedidosPersistencePort {

    private IPedidosRepository pedidosRepository;
    private IPedidosPlatosRepository pedidosPlatosRepository;
    private IPlatosRepository platosRepository;

    public PedidosJpaAdapter(IPedidosRepository pedidosRepository,
                             IPedidosPlatosRepository pedidosPlatosRepository,
                             IPlatosRepository platosRepository) {
        this.pedidosRepository = pedidosRepository;
        this.pedidosPlatosRepository = pedidosPlatosRepository;
        this.platosRepository = platosRepository;
    }

    @Override
    public void savePedidosPP(PedidoModel pedidoModel, List<PedidoPlatosModel> pedidoPlatosModelList) {
        PedidosEntity pedidosEntity = new PedidosEntity(null, pedidoModel.getIdCliente(),
                new Date(System.currentTimeMillis()), PlatoEstadoEnum.PENDIENTE.getDbValue(),
                pedidoModel.getIdChef(), pedidoModel.getIdRestaurante());
        PedidosEntity pedidosEntitySave = pedidosRepository.save(pedidosEntity);
        final Map<Integer, Integer> platoCantidad = pedidoPlatosModelList.stream().collect(Collectors
                .toMap(PedidoPlatosModel::getIdPlato, PedidoPlatosModel::getCantidad));

            platosRepository.findAllById(platoCantidad.keySet())
                    .forEach(plato -> pedidosPlatosRepository
                            .save(new PedidoPlatosEntity(null,
                                    pedidosEntitySave.getIdPedido(), plato.getId(), platoCantidad.get(plato.getId()))));

    }
}
