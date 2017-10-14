package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.entities.ItemEntity;
import com.brotherhood.stocktaking.models.requests.ItemCreateRequest;
import com.brotherhood.stocktaking.models.requests.ItemUpdateRequest;
import com.brotherhood.stocktaking.services.ItemService;
import com.brotherhood.stocktaking.services.ItemTypeService;
import com.brotherhood.stocktaking.services.LocalizationService;
import com.brotherhood.stocktaking.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("item")
@Api(tags = "item")
public class ItemController {
    private ItemService itemService;
    private LocalizationService localizationService;
    private ItemTypeService itemTypeService;
    private UserService userService;

    @Autowired
    public ItemController(ItemService itemService, LocalizationService localizationService,
                          ItemTypeService itemTypeService, UserService userService) {
        this.itemService = itemService;
        this.localizationService = localizationService;
        this.itemTypeService = itemTypeService;
        this.userService = userService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    List<ItemEntity> getItems(@RequestParam Integer userId) {
        return itemService.get(userId);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    void addItem(@RequestBody ItemCreateRequest item) {
        ItemEntity itemEntity = new ItemEntity()
                .setCodeType(item.getCodeType())
                .setCount(item.getCount())
                .setDate(item.getDate())
                .setDescription(item.getDescription())
                .setLocalization(localizationService.get(item.getLocalizationId()))
                .setItemType(itemTypeService.get(item.getItemTypeId()))
                .setName(item.getName())
                .setUser(userService.get(item.getUserId()))
                .setValue(item.getValue());
        itemService.add(itemEntity);
    }


    @RequestMapping(path = "/", method = RequestMethod.PATCH)
    boolean updateItem(@RequestBody ItemUpdateRequest updateRequest) {
        return itemService.update(updateRequest);
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    boolean deleteItem(@RequestParam Integer itemId) {
        return itemService.delete(itemId);
    }

}
