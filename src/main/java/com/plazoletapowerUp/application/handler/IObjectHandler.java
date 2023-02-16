package com.plazoletapowerUp.application.handler;



import com.plazoletapowerUp.application.dto.request.ObjectRequestDto;
import com.plazoletapowerUp.application.dto.response.ObjectResponseDto;

import java.util.List;

public interface IObjectHandler {

    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();
}