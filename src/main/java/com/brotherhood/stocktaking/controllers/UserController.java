package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.entities.UserEntity;
import com.brotherhood.stocktaking.models.requests.UserCreateRequest;
import com.brotherhood.stocktaking.models.requests.UserUpdateRequest;
import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
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

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResultResponse register(@RequestBody UserCreateRequest userCreateRequest) {
        return new ResultResponse().setSuccess(userService.add(userCreateRequest));
    }

    @RequestMapping(method = RequestMethod.POST, value = "update")
    public ResultResponse update(@RequestBody UserUpdateRequest userUpdateRequest) {
        return new ResultResponse().setSuccess(userService.update(userUpdateRequest));
    }
}
