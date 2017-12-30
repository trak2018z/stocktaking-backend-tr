package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.requests.CreateRaportOrderRequest;
import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.RaportService;
import com.brotherhood.stocktaking.services.SecurityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "raports")
@Api(tags = "raports")
@CrossOrigin
public class RaportController {
    private RaportService raportService;
    private SecurityService securityService;

    @Autowired
    public RaportController(RaportService raportService, SecurityService securityService) {
        this.raportService = raportService;
        this.securityService = securityService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "order")
    public ResultResponse createRaportOrder(@RequestParam String token, @RequestBody CreateRaportOrderRequest createRaportOrderRequest) {
        return new ResultResponse(securityService, token,
                raportService.addRaportOrder(securityService.isTokenValid(token), createRaportOrderRequest));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "order")
    public ResultResponse deleteRaport(@RequestParam String token, @RequestParam Integer raportId) {
        return new ResultResponse(securityService, token, raportService.deleteRaport(raportId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "order")
    public ResultResponse getUserOrders(@RequestParam String token) {
        return new ResultResponse(securityService, token, raportService.getAllRaportsOrders(securityService.isTokenValid(token)));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResultResponse getUserRaports(@RequestParam String token, @RequestParam int page) {
        return new ResultResponse(securityService, token, raportService.getAllRaports(securityService.isTokenValid(token), page));
    }
}
