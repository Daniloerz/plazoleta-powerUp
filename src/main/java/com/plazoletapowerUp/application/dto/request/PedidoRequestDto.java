package com.plazoletapowerUp.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PedidoRequestDto {

    private Integer idCliente;
    private Integer idChef;
    private Integer idRestaurante;
    private List<PedidoPlatoRequestDto> pedidoPlatoRequestDtoList;
}
