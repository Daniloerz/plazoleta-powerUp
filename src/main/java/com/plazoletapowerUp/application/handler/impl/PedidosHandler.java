package com.plazoletapowerUp.application.handler.impl;

import com.plazoletapowerUp.application.dto.request.PedidoRequestDto;
import com.plazoletapowerUp.application.handler.IPedidosHandler;
import com.plazoletapowerUp.application.mapper.IPedidoRequestMapper;
import com.plazoletapowerUp.domain.api.IPedidosServicePort;
import com.plazoletapowerUp.domain.model.PedidoModel;
import com.plazoletapowerUp.domain.model.PedidoPlatosModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidosHandler implements IPedidosHandler {

    private final IPedidosServicePort pedidosServicePort;
    private final IPedidoRequestMapper pedidoRequestMapper;


    @Override
    public void savePedido(PedidoRequestDto pedidoRequestDto) {
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setIdEmpleado(pedidoRequestDto.getIdEmpleado());
        pedidoModel.setIdCliente(pedidoRequestDto.getIdCliente());
        pedidoModel.setIdRestaurante(pedidoRequestDto.getIdRestaurante());

        List<PedidoPlatosModel> pedidoPlatosModelList =
                pedidoRequestMapper.toPedidoPlatosModel(pedidoRequestDto.getPedidoPlatoRequestDtoList());

        pedidosServicePort.savePedidoSP(pedidoModel, pedidoPlatosModelList);
    }
}
