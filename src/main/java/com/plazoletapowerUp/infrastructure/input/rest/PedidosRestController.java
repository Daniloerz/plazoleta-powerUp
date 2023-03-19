package com.plazoletapowerUp.infrastructure.input.rest;

import com.plazoletapowerUp.application.dto.request.PedidoRequestDto;
import com.plazoletapowerUp.application.dto.response.PedidoPageResponseDto;
import com.plazoletapowerUp.application.handler.IPedidosHandler;
import com.plazoletapowerUp.infrastructure.enums.PedidoEstadoEnum;
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
@RequestMapping("/api/v1/pedidos")
@RequiredArgsConstructor
@Validated
public class PedidosRestController {

    private final IPedidosHandler pedidosHandler;

    @Operation(summary = "Add a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Order already exists", content = @Content),
            @ApiResponse(responseCode = "200", description = "Order updated correctly", content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<Void> createPedido(@RequestBody PedidoRequestDto pedidoRequestDto) {
        try {
            pedidosHandler.savePedido(pedidoRequestDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{idRestaurante}")
    public ResponseEntity<PedidoPageResponseDto> getPedidosByEstado (@PathVariable Integer idRestaurante,
                                                                     @RequestParam (name = "id") Integer idEmpleado,
                                                                     @RequestParam(defaultValue = "pendiente")
                                                                                 String estado,
                                                                     @RequestParam (defaultValue = "1")
                                                                         @Min(1) Integer page,
                                                                     @RequestParam (defaultValue = "10")
                                                                         @Min(1) Integer numElementsPage){
        PedidoPageResponseDto pedidoPageResponseDto =
                pedidosHandler.findPedidosByEstado(idEmpleado, idRestaurante, estado, page, numElementsPage);
        if ( pedidoPageResponseDto != null){
            return ResponseEntity.ok(pedidoPageResponseDto);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping("/preparar-pedido/{idPedido}")
    public ResponseEntity<Void> updateEmpleadoAndEstado (@PathVariable Integer idPedido,
                                                         @RequestParam Integer idChef){
        try {
            pedidosHandler.updateIdEmpleado(idChef, idPedido, PedidoEstadoEnum.EN_PREPARACION.getDbValue());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/asignar-pedido-listo/{idPedido}")
    public ResponseEntity<Void> updatePedidoToReady (@PathVariable Integer idPedido){
        try {
            pedidosHandler.updatePedidoToReady(idPedido);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/entregar-pedido/{idPedido}/{idCliente}/{codigoEntrega}")
    public ResponseEntity<Void> updatePedidoToDelivered (@PathVariable Integer idPedido,
                                                         @PathVariable Integer idCliente,
                                                         @PathVariable String codigoEntrega){
        try {
            pedidosHandler.updatePedidoToDelivered(idPedido, idCliente, codigoEntrega);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
