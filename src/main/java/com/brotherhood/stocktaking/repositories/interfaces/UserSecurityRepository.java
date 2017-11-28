package com.brotherhood.stocktaking.repositories.interfaces;

public interface UserSecurityRepository {
    int isTokenValid(String token);

    String generateTokenAndStore(int userId);

    String generateToken();
}
