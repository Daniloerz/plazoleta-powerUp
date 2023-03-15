package com.plazoletapowerUp.infrastructure.out.jpa.adapter;

import com.plazoletapowerUp.domain.model.PedidoModel;
import com.plazoletapowerUp.domain.model.PedidoPlatosModel;
import com.plazoletapowerUp.domain.spi.IPedidosPersistencePort;
import com.plazoletapowerUp.infrastructure.enums.PedidoEstadoEnum;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.PedidoEntity;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.PedidoPlatoEntity;
import com.plazoletapowerUp.infrastructure.out.jpa.mapper.IPedidosEntityMapper;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IPedidosPlatosRepository;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IPedidosRepository;
import com.plazoletapowerUp.infrastructure.out.jpa.repository.IPlatosRepository;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class PedidosJpaAdapter implements IPedidosPersistencePort {

    private final IPedidosRepository pedidosRepository;
    private final IPedidosPlatosRepository pedidosPlatosRepository;
    private final IPlatosRepository platosRepository;
    private final IPedidosEntityMapper pedidosEntityMapper;

    @Override
    public void savePedidosPP(PedidoModel pedidoModel, List<PedidoPlatosModel> pedidoPlatosModelList) {
        PedidoEntity pedidoEntity = new PedidoEntity(null, pedidoModel.getIdCliente(),
                new Date(System.currentTimeMillis()), PedidoEstadoEnum.PENDIENTE.getDbValue(),
                pedidoModel.getIdEmpleado(), pedidoModel.getIdRestaurante());
        PedidoEntity pedidoEntitySave = pedidosRepository.save(pedidoEntity);
        final Map<Integer, Integer> platoCantidad = pedidoPlatosModelList.stream().collect(Collectors
                .toMap(PedidoPlatosModel::getIdPlato, PedidoPlatosModel::getCantidad));

            platosRepository.findAllById(platoCantidad.keySet())
                    .forEach(plato -> pedidosPlatosRepository
                            .save(new PedidoPlatoEntity(null,
                                    pedidoEntitySave.getIdPedido(), plato.getId(), platoCantidad.get(plato.getId()))));

    }

    @Override
    public List<PedidoModel> findPedidosByIdClientePP(Integer idCliente) {
        List<PedidoEntity> pedidoEntity = pedidosRepository.findByIdCliente(idCliente);
        return pedidosEntityMapper.toPedidosModel(pedidoEntity);
    }

}
