package com.plazoletapowerUp.infrastructure.input.rest;

import com.plazoletapowerUp.application.dto.request.PlatosRequestDto;
import com.plazoletapowerUp.application.dto.request.PlatosRequestPatchDto;
import com.plazoletapowerUp.application.handler.IPlatosHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/platos")
@RequiredArgsConstructor
public class PlatosRestController {

    private final IPlatosHandler platosHandler;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Dish already exists", content = @Content),
            @ApiResponse(responseCode = "200", description = "Dish updated correctly", content = @Content),
    })
    @PostMapping("/create-plato")
    public ResponseEntity<Void> createPlato(@RequestBody PlatosRequestDto platosRequestDto) {
        try {
            platosHandler.savePlatos(platosRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody PlatosRequestPatchDto platosRequestPatchDto){

        try {
            platosRequestPatchDto.setId(id);
            platosHandler.updatePlato(platosRequestPatchDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}