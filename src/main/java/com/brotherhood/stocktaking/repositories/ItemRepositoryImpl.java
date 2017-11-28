package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.controllers.ItemController;
import com.brotherhood.stocktaking.models.entities.ItemEntity;
import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.repositories.interfaces.ItemRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static com.brotherhood.stocktaking.controllers.ItemController.PAGE_ITEMS_COUNT;

@Repository
@SuppressWarnings("unchecked")
public class ItemRepositoryImpl extends AbstractRepository implements ItemRepository {
    @Override
    public List<ItemEntity> get(int page) {
        Query query = entityManager.createQuery("select item from ItemEntity item ");
        query.setMaxResults(PAGE_ITEMS_COUNT);
        query.setFirstResult(page * PAGE_ITEMS_COUNT);
        return new ArrayList<ItemEntity>(query.getResultList());
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

    @Override
    public boolean update(ItemEntity item) {
        try {
            entityManager.merge(item);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
