package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.requests.LoginRequest;
import com.brotherhood.stocktaking.models.requests.RegisterRequest;
import com.brotherhood.stocktaking.models.requests.UserUpdateRequest;
import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.SecurityService;
import com.brotherhood.stocktaking.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "user")
@Api(tags = "user")
@CrossOrigin
public class UserController {
    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public UserController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/users",
            produces = "application/json")
    public ResultResponse getAllUsers(@RequestParam String token) {
        return new ResultResponse(securityService, token, userService.get());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResultResponse register(@RequestBody RegisterRequest registerRequest) {
        return new ResultResponse(securityService, userService.add(registerRequest));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResultResponse login(@RequestBody LoginRequest loginRequest) {
        return new ResultResponse(securityService, userService.get(loginRequest));
    }

    @RequestMapping(method = RequestMethod.POST, value = "update")
    public ResultResponse update(
            @RequestParam String token,
            @RequestBody UserUpdateRequest userUpdateRequest) {
        return new ResultResponse(securityService, token, userService.update(securityService.isTokenValid(token), userUpdateRequest));
    }
}
