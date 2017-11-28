package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.ItemTypeService;
import com.brotherhood.stocktaking.services.SecurityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "itemType")
@Api(tags = "itemType")
@CrossOrigin
public class ItemTypeController {
    private ItemTypeService itemTypeService;
    private SecurityService securityService;

    @Autowired
    public ItemTypeController(ItemTypeService itemTypeService, SecurityService securityService) {
        this.itemTypeService = itemTypeService;
        this.securityService = securityService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    ResultResponse addItemType(@RequestParam String token, @RequestParam String type) {
        return new ResultResponse(securityService, token, itemTypeService.add(type)).setMessage("Type exists!");
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    ResultResponse deleteItemType(@RequestParam String token, @RequestParam String type) {
        return new ResultResponse(securityService, token, itemTypeService.delete(type)).setMessage("Type not exists!");
    }
}
