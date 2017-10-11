package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.UserEntity;

import java.util.List;

public interface UserRepository {
    List<UserEntity> getAll();

    UserEntity get(Integer userId);
}
