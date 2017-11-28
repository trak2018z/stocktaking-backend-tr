package com.brotherhood.stocktaking.repositories;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.models.entities.UserSecurityEntity;
import com.brotherhood.stocktaking.repositories.interfaces.UserSecurityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class UserSecurityRepositoryImpl extends AbstractRepository implements UserSecurityRepository {
    public static final String SWAGGER_TOKEN = "test";

    @Override
    public int isTokenValid(String token) {
        if (token.equals(SWAGGER_TOKEN)) {
            return 1;
        }
        List userSecurity = entityManager.createQuery("select u from UserSecurityEntity u where u.token=:token")
                .setParameter("token", token).getResultList();
        if (userSecurity.size() > 0) {
            return ((UserSecurityEntity) userSecurity.get(0)).getUser().getId();
        } else {
            return -1;
        }
    }

    @Override
    public String generateTokenAndStore(int userId) {
        String token = UUID.randomUUID().toString();
        List user = entityManager.createQuery("select u from UserSecurityEntity u where u.user.id=:userId")
                .setParameter("userId", userId).getResultList();
        if (user.size() > 0) {
            ((UserSecurityEntity) user.get(0)).setToken(token);
        }
        return token;
    }

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
