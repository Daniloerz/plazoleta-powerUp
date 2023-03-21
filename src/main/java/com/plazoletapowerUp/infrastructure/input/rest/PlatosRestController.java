package com.plazoletapowerUp.infrastructure.input.rest;

import com.plazoletapowerUp.application.dto.request.PlatosRequestDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestPatchDto;
import com.plazoletapowerUp.application.dto.response.PlatosPageResponseDto;
import com.plazoletapowerUp.application.handler.IPlatosHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/platos")
@RequiredArgsConstructor
@Validated
public class PlatosRestController {

    private final IPlatosHandler platosHandler;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content)
    })
    @PostMapping("/{idPropietario}")
    public ResponseEntity<Void> createPlato(@RequestBody PlatosRequestDto platosRequestDto,
                                            @PathVariable Integer idPropietario) {
        platosHandler.savePlatos(platosRequestDto, idPropietario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update dish price and dish description")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Dish price and dish description updated correctly",
                    content = @Content)
    })
    @PatchMapping("/price-description/{id}")
    public ResponseEntity<Void> updateDishPriceDescription(@PathVariable Integer id,
                                                           @RequestBody PlatosRequestPatchDto platosRequestPatchDto){

        platosRequestPatchDto.setId(id);
        platosHandler.updatePlatoByPriceDescription(platosRequestPatchDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update dish active")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Dish active updated correctly",
                    content = @Content)
    })
    @PatchMapping("/active/{idPropietario}/{idPlato}")
    public ResponseEntity<Void> updateDishActive(@PathVariable Integer idPropietario,
                                                 @PathVariable Integer idPlato,
                                                 @RequestParam Boolean isActive){
        platosHandler.updatePlatoActive(idPropietario, idPlato, isActive);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get all dishes from a restaurant with pagination")
    @ApiResponses(value = {

            @ApiResponse(responseCode = "200", description = "Dishes from a specific restaurant found",
                    content = @Content(schema = @Schema(implementation = PlatosPageResponseDto.class)))
    })
    @GetMapping("/{id_restaurante}")
    public ResponseEntity<PlatosPageResponseDto> findAllByRestaurante(@PathVariable Integer id_restaurante,
                                                                     @RequestParam (defaultValue = "1")
                                                                     @Min(1) Integer initPage,
                                                                     @RequestParam (defaultValue = "10")
                                                                         @Min(1) Integer numElementsPage){
        final PlatosPageResponseDto platosByIdRestaurante = platosHandler.findPlatosByIdRestaurante(id_restaurante, initPage, numElementsPage);
        if (platosByIdRestaurante != null){
            return ResponseEntity.ok(platosByIdRestaurante);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}