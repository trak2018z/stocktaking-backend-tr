package com.brotherhood.stocktaking.services;

import com.brotherhood.stocktaking.models.entities.ItemTypeEntity;
import com.brotherhood.stocktaking.repositories.ItemTypeRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.ItemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemTypeService {
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    public ItemTypeService(ItemTypeRepositoryImpl itemTypeRepository) {
        this.itemTypeRepository = itemTypeRepository;
    }

    public ItemTypeEntity get(Integer itemTypeId) {
        return itemTypeRepository.get(itemTypeId);
    }
}
