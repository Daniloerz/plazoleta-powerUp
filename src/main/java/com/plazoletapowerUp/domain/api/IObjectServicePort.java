package com.plazoletapowerUp.domain.api;



import com.plazoletapowerUp.domain.model.ObjectModel;

import java.util.List;

public interface IObjectServicePort {

    void saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}