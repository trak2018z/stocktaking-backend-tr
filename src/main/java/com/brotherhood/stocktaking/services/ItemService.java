package com.brotherhood.stocktaking.services;

import com.brotherhood.stocktaking.models.entities.ItemEntity;
import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.models.requests.ItemUpdateRequest;
import com.brotherhood.stocktaking.repositories.ItemRepositoryImpl;
import com.brotherhood.stocktaking.repositories.ItemTypeRepositoryImpl;
import com.brotherhood.stocktaking.repositories.LocalizationRepositoryImpl;
import com.brotherhood.stocktaking.repositories.UserRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.ItemRepository;
import com.brotherhood.stocktaking.repositories.interfaces.ItemTypeRepository;
import com.brotherhood.stocktaking.repositories.interfaces.LocalizationRepository;
import com.brotherhood.stocktaking.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemService {
    private ItemRepository itemRepository;
    private LocalizationRepository localizationRepository;
    private UserRepository userRepository;
    private ItemTypeRepository itemTypeRepository;

    @Autowired
    public ItemService(ItemRepositoryImpl itemRepository,
                       LocalizationRepositoryImpl localizationRepository,
                       UserRepositoryImpl userRepository,
                       ItemTypeRepositoryImpl itemTypeRepository) {
        this.itemRepository = itemRepository;
        this.localizationRepository = localizationRepository;
        this.userRepository = userRepository;
        this.itemTypeRepository = itemTypeRepository;
    }

    public List<ItemEntity> get(int page) {
        return itemRepository.get(page);
    }

    public ItemEntity getItem(Integer id) {
        return itemRepository.getItem(id);
    }

    public void add(ItemEntity itemEntity) {
        itemRepository.add(itemEntity);
    }

    public boolean delete(Integer itemId) {
        ItemEntity item = itemRepository.getItem(itemId);
        if (item != null) {
            itemRepository.delete(item);
            return true;
        }
        return false;
    }

    public boolean update(int userFromToken, ItemUpdateRequest request) {
        ItemEntity item = itemRepository.getItem(request.getItemId());
        if (item == null) {
            return false;
        }
        try {
            item
                    .setDescription(request.getDescription())
                    .setDate(request.getDate())
                    .setValue(request.getValue())
                    .setCodeType(request.getCodeType())
                    .setUser(userRepository.get(userFromToken))
                    .setCount(request.getCount())
                    .setLocalization(localizationRepository.get(request.getLocalizationName()))
                    .setName(request.getName())
                    .setItemType(itemTypeRepository.get(request.getItemTypeId()));
            return itemRepository.update(item);
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }
}
