package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.UserGroupEntity;
import com.brotherhood.stocktaking.repositories.interfaces.UserGroupRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserGroupRepositoryImpl extends AbstractRepository implements UserGroupRepository {
    @Override
    public UserGroupEntity get(String name) {
        try {
            return (UserGroupEntity) entityManager.createQuery("select usergroup from UserGroupEntity usergroup where usergroup.name=:name")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (IllegalStateException | NoResultException e) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserGroupEntity> get() {
        return entityManager.createQuery("select uge from UserGroupEntity uge").getResultList();
    }

    @Override
    public boolean add(String name) {
        if (get(name) != null) {
            return false;
        } else {
            entityManager.persist(new UserGroupEntity().setName(name));
            return true;
        }
    }

    @Override
    public boolean delete(String name) {
        if (get(name) == null) {
            return false;
        } else {
            entityManager.remove(get(name));
            return true;
        }
    }
}
