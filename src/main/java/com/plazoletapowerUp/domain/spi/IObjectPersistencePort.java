package com.plazoletapowerUp.domain.spi;


import com.plazoletapowerUp.domain.model.ObjectModel;

import java.util.List;

public interface IObjectPersistencePort {
    ObjectModel saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}