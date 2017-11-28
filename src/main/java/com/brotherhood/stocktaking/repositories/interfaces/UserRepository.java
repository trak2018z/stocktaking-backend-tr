package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.models.requests.LoginRequest;
import com.brotherhood.stocktaking.models.requests.RegisterRequest;
import com.brotherhood.stocktaking.models.requests.UserUpdateRequest;

import java.util.List;

public interface UserRepository {
    List<UserEntity> getAll();

    UserEntity get(Integer userId);

    UserEntity get(String nick);

    int add(RegisterRequest registerRequest);

    boolean update(int userId, UserUpdateRequest userUpdateRequest);

    UserEntity get(LoginRequest loginRequest);
}
