package com.brotherhood.stocktaking.services;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.repositories.UserSecurityRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.UserSecurityRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private UserSecurityRepository securityRepository;

    public SecurityService(UserSecurityRepositoryImpl securityRepository) {
        this.securityRepository = securityRepository;
    }

    /**
     * Returns -1 if token is invalid else - returns userId with this token.
     * @param token
     * @return
     */
    public int isTokenValid(String token) {
        return securityRepository.isTokenValid(token);
    }

    public String generateToken(int userId) {
        return securityRepository.generateTokenAndStore(userId);
    }
}
