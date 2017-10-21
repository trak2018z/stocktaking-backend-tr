package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.LocalizationEntity;
import com.brotherhood.stocktaking.repositories.interfaces.LocalizationRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class LocalizationRepositoryImpl extends AbstractRepository implements LocalizationRepository {
    @Override
    public LocalizationEntity get(Integer localizationId) {
        return entityManager.find(LocalizationEntity.class, localizationId);
    }

    @Override
    public LocalizationEntity get(String name) {
        try {
            return (LocalizationEntity) entityManager.createQuery("SELECT localization from LocalizationEntity localization " +
                    "where name=:name")
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<LocalizationEntity> get(List<Integer> ids) {
        List<LocalizationEntity> result = new ArrayList<>();
        for (Integer id : ids) {
            result.add(get(id));
        }
        return result;
    }

    @Override
    public boolean add(String name) {
        if (get(name) == null) {
            entityManager.persist(new LocalizationEntity().setName(name));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String name) {
        LocalizationEntity localizationEntity = get(name);
        if (localizationEntity == null) {
            return false;
        } else {
            entityManager.remove(localizationEntity);
            return true;
        }
    }
}
