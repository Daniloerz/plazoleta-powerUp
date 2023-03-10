package com.plazoletapowerUp.infrastructure.out.jpa.repository;

import com.plazoletapowerUp.infrastructure.out.jpa.entity.PlatosEntity;
import com.plazoletapowerUp.infrastructure.out.jpa.entity.custom.IPlatoRestaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPlatosRepository extends JpaRepository<PlatosEntity,Integer> {

    @Query(nativeQuery = true, value = "select c.nombre as nombreCategoria, p.nombre as nombrePlato, p.precio, p.descripcion as descripcionPlato,p.url_imagen as urlImagen, r.nombre as nombreRestaurante  from platos p \n" +
            "inner join categoria c on p.id_categoria  = c.id_categoria \n" +
            "inner join restaurantes r on p.id_restaurante = r.id \n" +
            "where p.id_restaurante = :idRestaurante\n" +
            "group by p.nombre , p.precio ,p.descripcion ,p.url_imagen , c.nombre ")
    Page<IPlatoRestaurante> getPlatosByRestaurante (@Param("idRestaurante") Integer idRestaurante, Pageable page);
}
