package com.plazoletapowerUp.domain.client;

import com.plazoletapowerUp.domain.responseDtoModel.UsuarioResponseDtoModel;


public interface IUsuarioClientPort {

    public UsuarioResponseDtoModel findUsuarioById (Integer idUsuario);

}
