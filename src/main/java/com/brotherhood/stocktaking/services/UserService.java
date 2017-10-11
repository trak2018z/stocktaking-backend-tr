package com.brotherhood.stocktaking.services;

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

    public List get() {
        return userRepository.getAll();
    }
}
