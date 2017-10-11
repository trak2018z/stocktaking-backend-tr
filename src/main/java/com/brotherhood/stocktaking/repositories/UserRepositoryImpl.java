package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.repositories.interfaces.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {
    @Override
    public List getAll() {
        return entityManager.createQuery("SELECT user FROM UserEntity user").getResultList();
    }
}
