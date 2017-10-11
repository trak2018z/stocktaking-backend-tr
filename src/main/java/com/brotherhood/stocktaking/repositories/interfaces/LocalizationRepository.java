package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.LocalizationEntity;

public interface LocalizationRepository {
    LocalizationEntity get(Integer localizationId);

    LocalizationEntity get(String room);

    boolean add(String roomName);

    boolean delete(String roomName);
}
