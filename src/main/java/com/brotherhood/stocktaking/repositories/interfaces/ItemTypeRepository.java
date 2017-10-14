package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.ItemTypeEntity;

public interface ItemTypeRepository {
    ItemTypeEntity get(Integer itemTypeId);

    ItemTypeEntity get(String type);

    boolean add(String type);

    boolean delete(String type);
}
