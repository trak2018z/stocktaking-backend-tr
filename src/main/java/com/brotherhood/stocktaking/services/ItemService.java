package com.brotherhood.stocktaking.services;

import com.brotherhood.stocktaking.models.entities.ItemEntity;
import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.repositories.ItemRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.ItemRepository;
import com.brotherhood.stocktaking.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepositoryImpl itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemEntity> get(Integer userId) {
        return itemRepository.get(userId);
    }

    public void add(ItemEntity itemEntity) {
        itemRepository.add(itemEntity);
    }

    public void delete(Integer itemId) {
        ItemEntity item = itemRepository.getItem(itemId);
        if (item != null) {
            itemRepository.delete(item);
        }
    }
}
