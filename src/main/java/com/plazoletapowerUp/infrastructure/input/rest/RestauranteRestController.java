package com.plazoletapowerUp.infrastructure.input.rest;

import com.plazoletapowerUp.application.dto.request.RestauranteRequestDto;
import com.plazoletapowerUp.application.dto.response.RestaurantePageResponseDto;
import com.plazoletapowerUp.application.dto.response.RestauranteResponseDto;
import com.plazoletapowerUp.application.handler.IRestauranteHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurante")
@RequiredArgsConstructor
@Validated
public class RestauranteRestController {

    private final IRestauranteHandler restauranteHandler;

    @Operation(summary = "Add a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @PostMapping("/create-restaurante")
    public ResponseEntity<Void> createRestaurante(@RequestBody RestauranteRequestDto restauranteRequestDto) {
        try {
            restauranteHandler.saveRestaurante(restauranteRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-restaurant")
    public ResponseEntity<RestaurantePageResponseDto> findAllRestaurantes(@RequestParam @Min(1) Integer page){
       if (restauranteHandler.findAllRestaurantes(page) != null){
           return ResponseEntity.ok(restauranteHandler.findAllRestaurantes(page));
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
}