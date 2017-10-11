package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/users",
            produces = "application/json")
    public List<UserEntity> getAllUsers() {
        return userService.get();
    }
}
