package com.plazoletapowerUp.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantePageResponseDto {

    private Integer page;
    private Integer pageSize;
    private List<RestauranteResponseDto> restauranteResponseDtoList;
    private Integer totalPages;

    public RestaurantePageResponseDto(Integer page, Integer pageSize, List<RestauranteResponseDto> restauranteResponseDtoList, Integer totalPages) {
        this.page = page;
        this.pageSize = pageSize;
        this.restauranteResponseDtoList = restauranteResponseDtoList;
        this.totalPages = totalPages;
    }
}
