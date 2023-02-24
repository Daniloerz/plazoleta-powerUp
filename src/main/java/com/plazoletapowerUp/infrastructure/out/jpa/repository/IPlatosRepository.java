package com.plazoletapowerUp.infrastructure.out.jpa.repository;

import com.plazoletapowerUp.infrastructure.out.jpa.entity.PlatosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlatosRepository extends JpaRepository<PlatosEntity,Integer> {
}
