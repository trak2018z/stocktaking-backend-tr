package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.entities.UserGroupEntity;
import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.UserGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "userGroup")
@Api(tags = "userGroup")
public class UserGroupController {
    private UserGroupService userGroupService;

    @Autowired
    public UserGroupController(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    ResultResponse add(@RequestParam String name) {
        return new ResultResponse().setSuccess(userGroupService.add(name));
    }


    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    ResultResponse delete(@RequestParam String name) {
        return new ResultResponse().setSuccess(userGroupService.delete(name));
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    List<UserGroupEntity> getAll() {
        return userGroupService.get();
    }
}
