package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.models.entities.UserGroupEntity;
import com.brotherhood.stocktaking.models.entities.UserSecurityEntity;
import com.brotherhood.stocktaking.models.requests.LoginRequest;
import com.brotherhood.stocktaking.models.requests.RegisterRequest;
import com.brotherhood.stocktaking.models.requests.UserUpdateRequest;
import com.brotherhood.stocktaking.repositories.interfaces.UserGroupRepository;
import com.brotherhood.stocktaking.repositories.interfaces.UserRepository;
import com.brotherhood.stocktaking.repositories.interfaces.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {
    private UserGroupRepository userGroupRepository;
    private UserSecurityRepository userSecurityRepository;

    @Autowired
    public UserRepositoryImpl(UserGroupRepository userGroupRepository, UserSecurityRepository userSecurityRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userSecurityRepository = userSecurityRepository;
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
    public int add(RegisterRequest registerRequest) {
        UserGroupEntity userGroup = userGroupRepository.get(registerRequest.getGroupName());
        if (userGroup == null) {
            return -1;
        }
        UserEntity userEntity = get(registerRequest.getLogin());
        if (userEntity != null) {
            return -1;
        }
        userEntity = new UserEntity()
                .setUserGroupEntity(userGroup)
                .setEmail(registerRequest.getEmail())
                .setName(registerRequest.getName())
                .setSurname(registerRequest.getSurname())
                .setNick(registerRequest.getLogin());
        entityManager.persist(userEntity);
        UserSecurityEntity userSecurity = new UserSecurityEntity()
                .setLogin(registerRequest.getLogin())
                .setPassword(registerRequest.getPassword())
                .setUser(userEntity)
                .setToken(userSecurityRepository.generateToken());
        entityManager.persist(userSecurity);
        return userEntity.getId();

    }

    @Override
    public boolean update(int userId, UserUpdateRequest userUpdateRequest) {
        if (userId < 0) {
            return false;
        }
        UserEntity userEntity = get(userId);
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

    @Override
    public UserEntity get(LoginRequest loginRequest) {
        List user = entityManager.createQuery("select u from UserSecurityEntity u where u.login=:login AND u.password=:password")
                .setParameter("login", loginRequest.getLogin())
                .setParameter("password", loginRequest.getPassword()).getResultList();
        if (user.size() > 0) {
            return ((UserSecurityEntity) user.get(0)).getUser();
        }
        return null;
    }
}
