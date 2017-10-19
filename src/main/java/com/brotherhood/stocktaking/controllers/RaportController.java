package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.entities.RaportEntity;
import com.brotherhood.stocktaking.models.entities.RaportOrderEntity;
import com.brotherhood.stocktaking.services.RaportService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "raports")
@Api(tags = "raports")
public class RaportController {
    private RaportService raportService;

    @Autowired
    public RaportController(RaportService raportService) {
        this.raportService = raportService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "order")
    public void createOrder() {
        raportService.addRaportOrder();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void createRaport() {
        raportService.addRaport();
    }

    @RequestMapping(method = RequestMethod.GET, path = "order")
    public List<RaportOrderEntity> getUserOrders(@RequestParam Integer userId) {
        return raportService.getAllRaportsOrders(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<RaportEntity> getUserRaports(@RequestParam Integer userId) {
        return raportService.getAllRaports(userId);
    }
}