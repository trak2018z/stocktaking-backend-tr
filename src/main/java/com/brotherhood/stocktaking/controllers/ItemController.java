package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.entities.ItemEntity;
import com.brotherhood.stocktaking.models.requests.ItemCreateRequest;
import com.brotherhood.stocktaking.models.requests.ItemUpdateRequest;
import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "item")
@Api(tags = "item")
@CrossOrigin
public class ItemController {
    public static final int PAGE_ITEMS_COUNT = 7;

    private ItemService itemService;
    private LocalizationService localizationService;
    private ItemTypeService itemTypeService;
    private UserService userService;
    private SecurityService securityService;

    @Autowired
    public ItemController(ItemService itemService, LocalizationService localizationService,
                          ItemTypeService itemTypeService, UserService userService,
                          SecurityService securityService) {
        this.itemService = itemService;
        this.localizationService = localizationService;
        this.itemTypeService = itemTypeService;
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResultResponse getItems(@RequestParam String token, @RequestParam int page) {
        System.out.println("getting items!");
        return new ResultResponse(securityService, token, itemService.get(page));
    }

    @RequestMapping(method = RequestMethod.POST)
    ResultResponse addItem(@RequestParam String token, @RequestBody ItemCreateRequest item) {
        ItemEntity itemEntity = new ItemEntity()
                .setCodeType(item.getCodeType())
                .setCount(item.getCount())
                .setDate(item.getDate())
                .setUser(userService.get(securityService.isTokenValid(token)))
                .setDescription(item.getDescription())
                .setLocalization(localizationService.get(item.getLocalizationId()))
                .setItemType(itemTypeService.get(item.getItemTypeId()))
                .setName(item.getName())
                .setValue(item.getValue());
        itemService.add(itemEntity);
        return new ResultResponse(securityService, token, itemEntity.getId());
    }


    @RequestMapping(method = RequestMethod.PATCH)
    ResultResponse updateItem(@RequestParam String token, @RequestBody ItemUpdateRequest updateRequest) {
        return new ResultResponse(securityService, token, itemService.update(securityService.isTokenValid(token), updateRequest));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResultResponse deleteItem(@RequestParam String token, @RequestParam Integer itemId) {
        return new ResultResponse(securityService, token, itemService.delete(itemId));
    }

}
