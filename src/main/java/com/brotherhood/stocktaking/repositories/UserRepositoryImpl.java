package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.models.entities.UserGroupEntity;
import com.brotherhood.stocktaking.models.requests.UserCreateRequest;
import com.brotherhood.stocktaking.models.requests.UserUpdateRequest;
import com.brotherhood.stocktaking.repositories.interfaces.UserGroupRepository;
import com.brotherhood.stocktaking.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {
    private UserGroupRepository userGroupRepository;

    @Autowired
    public UserRepositoryImpl(UserGroupRepositoryImpl userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserEntity> getAll() {
        return new ArrayList<>(entityManager.createQuery("SELECT user FROM UserEntity user").getResultList());
    }

    @Override
    public UserEntity get(Integer userId) {
        return entityManager.find(UserEntity.class, userId);
    }

    @Override
    public UserEntity get(String nick) {
        try {
            return (UserEntity) entityManager.createQuery("select user from UserEntity user where user.nick=:nick")
                    .setParameter("nick", nick)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean add(UserCreateRequest userCreateRequest) {
        UserGroupEntity userGroup = userGroupRepository.get(userCreateRequest.getGroupName());
        if (userGroup == null) {
            return false;
        }
        UserEntity userEntity = get(userCreateRequest.getNick());
        if (userEntity != null) {
            return false;
        }
        userEntity = new UserEntity()
                .setUserGroupEntity(userGroup)
                .setEmail(userCreateRequest.getEmail())
                .setName(userCreateRequest.getName())
                .setSurname(userCreateRequest.getSurname())
                .setNick(userCreateRequest.getNick());
        entityManager.persist(userEntity);
        return true;

    }

    @Override
    public boolean update(UserUpdateRequest userUpdateRequest) {
        UserEntity userEntity = get(userUpdateRequest.getUserId());
        if (userEntity != null) {
            if (userUpdateRequest.getEmail() != null) {
                userEntity.setEmail(userUpdateRequest.getEmail());
            }
            if (userUpdateRequest.getGroupName() != null) {
                UserGroupEntity userGroup = userGroupRepository.get(userUpdateRequest.getGroupName());
                if (userGroup == null) {
                    return false;
                } else {
                    userEntity.setUserGroupEntity(userGroup);
                }
            }
            entityManager.merge(userEntity);
            return true;
        } else {
            return false;
        }
    }
}
