package com.plazoletapowerUp.domain.usecase;

import com.plazoletapowerUp.domain.api.IObjectServicePort;
import com.plazoletapowerUp.domain.model.ObjectModel;
import com.plazoletapowerUp.domain.spi.IObjectPersistencePort;

import java.util.List;

public class ObjectUseCase implements IObjectServicePort {

    private final IObjectPersistencePort objectPersistencePort;

    public ObjectUseCase(IObjectPersistencePort objectPersistencePort) {
        this.objectPersistencePort = objectPersistencePort;
    }

    @Override
    public void saveObject(ObjectModel objectModel) {
        objectPersistencePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectModel> getAllObjects() {
        return objectPersistencePort.getAllObjects();
    }
}