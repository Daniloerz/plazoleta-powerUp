package com.plazoletapowerUp.infrastructure.out.jpa.entity;
import lombok.*;


import javax.persistence.*;

@Entity
@Table(name = "Restaurante_Empleado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestauranteEmpleadoEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private Integer idRestaurante;
    private Integer idUsuario;

}
