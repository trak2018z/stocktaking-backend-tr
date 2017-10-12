package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.models.requests.UserCreateRequest;
import com.brotherhood.stocktaking.models.requests.UserUpdateRequest;

import java.util.List;

public interface UserRepository {
    List<UserEntity> getAll();

    UserEntity get(Integer userId);

    UserEntity get(String nick);

    boolean add(UserCreateRequest userCreateRequest);

    boolean update(UserUpdateRequest userUpdateRequest);
}
