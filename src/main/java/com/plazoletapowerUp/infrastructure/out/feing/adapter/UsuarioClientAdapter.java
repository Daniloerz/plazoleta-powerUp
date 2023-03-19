package com.plazoletapowerUp.infrastructure.out.feing.adapter;

import com.plazoletapowerUp.domain.client.IUsuarioClientPort;
import com.plazoletapowerUp.domain.responseDtoModel.UsuarioResponseDtoModel;
import com.plazoletapowerUp.infrastructure.out.feing.restClient.IUsuarioRestClient;
import com.plazoletapowerUp.infrastructure.out.feing.mapper.IUsuarioResponseMapper;

public class UsuarioClientAdapter implements IUsuarioClientPort {

    private IUsuarioRestClient usuarioRestClient;
    private IUsuarioResponseMapper usuarioResponseMapper;

    public UsuarioClientAdapter(IUsuarioRestClient usuarioRestClient, IUsuarioResponseMapper usuarioResponseMapper) {
        this.usuarioRestClient = usuarioRestClient;
        this.usuarioResponseMapper = usuarioResponseMapper;
    }

    @Override
    public UsuarioResponseDtoModel findUsuarioById(Integer idUsuario) {
        return usuarioResponseMapper.toUsuarioResponseModel(usuarioRestClient.findUsuarioById(idUsuario));
    }
}
