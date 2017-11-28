package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.entities.UserGroupEntity;
import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.SecurityService;
import com.brotherhood.stocktaking.services.UserGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "userGroup")
@Api(tags = "userGroup")
@CrossOrigin
public class UserGroupController {
    private UserGroupService userGroupService;
    private SecurityService securityService;

    @Autowired
    public UserGroupController(UserGroupService userGroupService, SecurityService securityService) {
        this.userGroupService = userGroupService;
        this.securityService = securityService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    ResultResponse add(@RequestParam String token, @RequestParam String name) {
        return new ResultResponse(securityService, token,    userGroupService.add(name));
    }


    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    ResultResponse delete(@RequestParam String token, @RequestParam String name) {
        return new ResultResponse(securityService, token, userGroupService.delete(name));
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    ResultResponse getAll(@RequestParam String token) {
        return new ResultResponse(securityService, token, userGroupService.get());
    }
}
