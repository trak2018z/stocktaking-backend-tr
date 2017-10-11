package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.ItemTypeEntity;
import com.brotherhood.stocktaking.repositories.interfaces.ItemTypeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ItemTypeRepositoryImpl extends AbstractRepository implements ItemTypeRepository {
    @Override
    public ItemTypeEntity get(Integer itemTypeId) {
        return entityManager.find(ItemTypeEntity.class, itemTypeId);
    }
}
