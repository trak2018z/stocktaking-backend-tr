package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.LocalizationEntity;
import com.brotherhood.stocktaking.repositories.interfaces.LocalizationRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LocalizationRepositoryImpl extends AbstractRepository implements LocalizationRepository {
    @Override
    public LocalizationEntity get(Integer localizationId) {
        return entityManager.find(LocalizationEntity.class, localizationId);
    }

    @Override
    public LocalizationEntity get(String room) {
        List list = entityManager.createQuery("SELECT localization from LocalizationEntity localization " +
                "where room=:roomName")
                .setParameter("roomName", room).getResultList();
        if (list.isEmpty()) {
            return null;
        } else {
            return ((LocalizationEntity) list.get(0));
        }
    }

    @Override
    public boolean add(String roomName) {
        if (get(roomName) == null) {
            entityManager.persist(new LocalizationEntity().setRoom(roomName));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String roomName) {
        LocalizationEntity localizationEntity = get(roomName);
        if (localizationEntity == null) {
            return false;
        } else {
            entityManager.remove(localizationEntity);
            return true;
        }
    }
}
