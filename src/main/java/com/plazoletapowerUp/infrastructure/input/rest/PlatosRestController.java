package com.plazoletapowerUp.infrastructure.input.rest;

import com.plazoletapowerUp.application.dto.request.PlatosRequestDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestPatchDto;
import com.plazoletapowerUp.application.dto.response.PlatosPageResponseDto;
import com.plazoletapowerUp.application.handler.IPlatosHandler;
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

@RestController
@RequestMapping("/api/v1/platos")
@RequiredArgsConstructor
@Validated
public class PlatosRestController {

    private final IPlatosHandler platosHandler;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Dish already exists", content = @Content),
            @ApiResponse(responseCode = "200", description = "Dish updated correctly", content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Void> createPlato(@RequestBody PlatosRequestDto platosRequestDto) {
        try {
            platosHandler.savePlatos(platosRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/price-description/{id}")
    public ResponseEntity<Void> updateDishPriceDescription(@PathVariable Integer id, @RequestBody PlatosRequestPatchDto platosRequestPatchDto){

        try {
            platosRequestPatchDto.setId(id);
            platosHandler.updatePlatoByPriceDescription(platosRequestPatchDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/active/{id}")
    public ResponseEntity<Void> updateDishActive(@PathVariable Integer id, @RequestParam Boolean isActive){
        try {
            platosHandler.updatePlatoActive(id, isActive);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id_restaurante}")
    public ResponseEntity<PlatosPageResponseDto> findAllRestaurantes(@PathVariable Integer id_restaurante,
                                                                     @RequestParam @Min(1) Integer initPage,
                                                                     @RequestParam @Min(1) Integer numElementsPage){
        final PlatosPageResponseDto platosByIdRestaurante = platosHandler.findPlatosByIdRestaurante(id_restaurante, initPage, numElementsPage);
        if (platosByIdRestaurante != null){
            return ResponseEntity.ok(platosByIdRestaurante);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}