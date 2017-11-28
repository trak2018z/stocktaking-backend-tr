package com.brotherhood.stocktaking.services;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.models.requests.LoginRequest;
import com.brotherhood.stocktaking.models.requests.RegisterRequest;
import com.brotherhood.stocktaking.models.requests.UserUpdateRequest;
import com.brotherhood.stocktaking.repositories.UserRepositoryImpl;
import com.brotherhood.stocktaking.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> get() {
        return userRepository.getAll();
    }

    public UserEntity get(Integer userId) {
        return userRepository.get(userId);
    }

    public UserEntity get(LoginRequest loginRequest) {
        return userRepository.get(loginRequest);
    }

    public int add(RegisterRequest registerRequest) {
        return userRepository.add(registerRequest);
    }

    public boolean update(int userId, UserUpdateRequest userUpdateRequest) {
        return userRepository.update(userId, userUpdateRequest);
    }
}
