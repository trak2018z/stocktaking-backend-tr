package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.ItemEntity;

import java.util.List;

public interface ItemRepository {
    List<ItemEntity> get(int page);

    ItemEntity getItem(Integer itemId);

    void add(ItemEntity itemEntity);

    void delete(ItemEntity itemEntity);

    boolean update(ItemEntity item);
}
