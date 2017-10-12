package com.brotherhood.stocktaking.repositories.interfaces;

import com.brotherhood.stocktaking.models.entities.UserGroupEntity;

import java.util.List;

public interface UserGroupRepository {
    UserGroupEntity get(String name);

    List<UserGroupEntity> get();

    boolean add(String name);

    boolean delete(String name);
}
