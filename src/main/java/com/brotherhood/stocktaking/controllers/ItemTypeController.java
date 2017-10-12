package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.ItemTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "itemType")
@Api(tags = "itemType")
public class ItemTypeController {
    private ItemTypeService itemTypeService;

    @Autowired
    public ItemTypeController(ItemTypeService itemTypeService) {
        this.itemTypeService = itemTypeService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    ResultResponse addItemType(@RequestParam String type) {
        return new ResultResponse().setSuccess(itemTypeService.add(type));
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    ResultResponse deleteItemType(@RequestParam String type) {
        return new ResultResponse().setSuccess(itemTypeService.delete(type));
    }
}
