package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.LocalizationEntity;

import java.util.List;

public interface LocalizationRepository {
    LocalizationEntity get(Integer localizationId);

    LocalizationEntity get(String room);

    List<LocalizationEntity> get(List<Integer> ids);

    List get();

    boolean add(String roomName);

    boolean delete(String roomName);

    List<LocalizationEntity> getPage(int page);
}
