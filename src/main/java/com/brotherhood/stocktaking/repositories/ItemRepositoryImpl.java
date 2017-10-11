package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.ItemEntity;
import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.repositories.interfaces.ItemRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@SuppressWarnings("unchecked")
public class ItemRepositoryImpl extends AbstractRepository implements ItemRepository {
    @Override
    public List<ItemEntity> get() {
        return new ArrayList<ItemEntity>(entityManager.createQuery("select item from ItemEntity item").getResultList());
    }

    @Override
    public List<ItemEntity> get(Integer userId) {
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);
        return new ArrayList<>(userEntity.getItemEntities());
    }

    @Override
    public ItemEntity getItem(Integer itemId) {
        return entityManager.find(ItemEntity.class, itemId);
    }

    @Override
    public void add(ItemEntity itemEntity) {
        entityManager.persist(itemEntity);
    }

    @Override
    public void delete(ItemEntity itemEntity) {
        entityManager.remove(itemEntity);
    }
}
