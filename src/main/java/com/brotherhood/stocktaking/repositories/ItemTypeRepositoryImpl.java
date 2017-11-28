package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.ItemTypeEntity;
import com.brotherhood.stocktaking.repositories.interfaces.ItemTypeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class ItemTypeRepositoryImpl extends AbstractRepository implements ItemTypeRepository {
    @Override
    public ItemTypeEntity get(Integer itemTypeId) {
        return entityManager.find(ItemTypeEntity.class, itemTypeId);
    }

    @Override
    public ItemTypeEntity get(String type) {
        try {
            return (ItemTypeEntity) entityManager.createQuery("select item from ItemTypeEntity item " +
                    "where item.type=:typeValue")
                    .setParameter("typeValue", type)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean add(String type) {
        List list = entityManager.createQuery("select item from ItemTypeEntity item " +
                "where item.type=:typeValue")
                .setParameter("typeValue", type)
                .getResultList();
        if (list.isEmpty()) {
            entityManager.persist(new ItemTypeEntity().setType(type));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String type) {
        try {
            ItemTypeEntity itemTypeEntity = (ItemTypeEntity) entityManager.createQuery("select item from ItemTypeEntity item " +
                    "where item.type=:typeValue")
                    .setParameter("typeValue", type)
                    .getSingleResult();
            entityManager.remove(itemTypeEntity);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
}
