package com.plazoletapowerUp.infrastructure.out.jpa.repository;

import com.plazoletapowerUp.infrastructure.out.jpa.entity.PedidosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidosRepository extends JpaRepository<PedidosEntity, Integer> {
}
