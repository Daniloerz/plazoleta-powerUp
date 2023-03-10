package com.plazoletapowerUp.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "Pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidosEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_pedido")
    private Integer idPedido;
    private Integer idCliente;
    private Date fecha;
    private String estado;
    private Integer idChef;
    private Integer idRestaurante;
}
