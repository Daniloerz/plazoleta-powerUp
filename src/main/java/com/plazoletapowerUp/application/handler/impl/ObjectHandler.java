package com.plazoletapowerUp.application.handler.impl;

import com.plazoletapowerUp.application.dto.request.ObjectRequestDto;
import com.plazoletapowerUp.application.dto.response.ObjectResponseDto;
import com.plazoletapowerUp.application.handler.IObjectHandler;
import com.plazoletapowerUp.application.mapper.IObjectRequestMapper;
import com.plazoletapowerUp.application.mapper.IObjectResponseMapper;
import com.plazoletapowerUp.domain.api.IObjectServicePort;
import com.plazoletapowerUp.domain.model.ObjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ObjectHandler implements IObjectHandler {

    private final IObjectServicePort objectServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveObject(ObjectRequestDto objectRequestDto) {
        ObjectModel objectModel = objectRequestMapper.toObject(objectRequestDto);
        objectServicePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectResponseDto> getAllObjects() {
        return objectResponseMapper.toResponseList(objectServicePort.getAllObjects());
    }
}