package com.plazoletapowerUp.infrastructure.out.feing;

import com.plazoletapowerUp.infrastructure.out.feing.dto.UsuarioResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-powerup")
public interface IUsuarioRestClient {

    @GetMapping("/api/v1/usuario/{idUsuario}")
    public UsuarioResponseDto findUsuarioById (@PathVariable Integer idUsuario);

}
