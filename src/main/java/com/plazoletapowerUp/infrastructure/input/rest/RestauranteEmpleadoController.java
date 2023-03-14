package com.plazoletapowerUp.infrastructure.input.rest;

import com.plazoletapowerUp.application.dto.request.PlatosRequestDto;
import com.plazoletapowerUp.application.dto.request.RestauranteEmpleadoRequestDto;
import com.plazoletapowerUp.application.handler.IRestauranteEmpleado;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurante-empleado")
@RequiredArgsConstructor
public class RestauranteEmpleadoController {

    private final IRestauranteEmpleado restauranteEmpleado;

    @Operation(summary = "Add a new restaurant employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant employee already exists", content = @Content),
            @ApiResponse(responseCode = "200", description = "Restaurant employee updated correctly",
                    content = @Content),
    })


    @PostMapping("/")
    public ResponseEntity<Void> restaurantEmployeeLink (
            @RequestBody RestauranteEmpleadoRequestDto restauranteEmpleadoRequestDto) {
        try {
            restauranteEmpleado.saveRestauranteEmpleado(restauranteEmpleadoRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
