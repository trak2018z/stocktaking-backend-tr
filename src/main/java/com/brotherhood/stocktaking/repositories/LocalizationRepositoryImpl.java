package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.LocalizationEntity;
import com.brotherhood.stocktaking.repositories.interfaces.LocalizationRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static com.brotherhood.stocktaking.controllers.ItemController.PAGE_ITEMS_COUNT;

@Repository
public class LocalizationRepositoryImpl extends AbstractRepository implements LocalizationRepository {
    @Override
    public LocalizationEntity get(Integer localizationId) {
        return entityManager.find(LocalizationEntity.class, localizationId);
    }

    @Override
    public LocalizationEntity get(String name) {
        List result = entityManager.createQuery("SELECT localization " +
                "from LocalizationEntity localization " +
                "where name=:name")
                .setParameter("name", name).getResultList();
        if (result.size() > 0) {
            return ((LocalizationEntity) result.get(0));
        } else {
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
    public List get() {
        return entityManager.createQuery("select e from LocalizationEntity e").getResultList();
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
        System.out.println(localizationEntity);
        if (localizationEntity == null) {
            return false;
        } else {
            entityManager.remove(localizationEntity);
            return true;
        }
    }

    @Override
    public List<LocalizationEntity> getPage(int page) {
        Query query = entityManager.createQuery("select item from LocalizationEntity item ");
        query.setMaxResults(PAGE_ITEMS_COUNT);
        query.setFirstResult(page * PAGE_ITEMS_COUNT);
        return new ArrayList<LocalizationEntity>(query.getResultList());
    }
}
