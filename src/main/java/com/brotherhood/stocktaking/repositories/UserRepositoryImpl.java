package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.repositories.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {
    @Override
    @SuppressWarnings("unchecked")
    public List<UserEntity> getAll() {
        return new ArrayList<>(entityManager.createQuery("SELECT user FROM UserEntity user").getResultList());
    }

    @Override
    public UserEntity get(Integer userId) {
        return entityManager.find(UserEntity.class, userId);
    }
}
