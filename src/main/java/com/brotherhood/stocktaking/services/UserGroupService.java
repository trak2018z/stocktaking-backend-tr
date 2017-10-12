package com.brotherhood.stocktaking.services;

import com.brotherhood.stocktaking.models.entities.UserGroupEntity;
import com.brotherhood.stocktaking.repositories.UserGroupRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.UserGroupRepository;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService {
    private UserGroupRepository userGroupRepository;

    @Autowired
    public UserGroupService(UserGroupRepositoryImpl userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    public List<UserGroupEntity> get() {
        return userGroupRepository.get();
    }

    public UserGroupEntity get(String name) {
        return userGroupRepository.get(name);
    }

    public boolean add(String name) {
        return userGroupRepository.add(name);
    }

    public boolean delete(String name) {
        return userGroupRepository.delete(name);
    }
}
