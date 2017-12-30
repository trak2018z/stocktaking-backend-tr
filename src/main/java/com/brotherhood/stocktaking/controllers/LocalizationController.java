package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.LocalizationService;
import com.brotherhood.stocktaking.services.SecurityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "localization")
@Api(tags = "localization")
@CrossOrigin
public class LocalizationController {
    private LocalizationService localizationService;
    private SecurityService securityService;

    @Autowired
    public LocalizationController(LocalizationService localizationService, SecurityService securityService) {
        this.localizationService = localizationService;
        this.securityService = securityService;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResultResponse getLocalizations(@RequestParam String token, @RequestParam int page) {
        return new ResultResponse(securityService, token, localizationService.getPage(page));
    }

    @RequestMapping(method = RequestMethod.GET, path = "all")
    ResultResponse getAllLocalizations(@RequestParam String token) {
        return new ResultResponse(securityService, token, localizationService.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    ResultResponse createLocalization(@RequestParam String token, @RequestParam String roomName) {
        return new ResultResponse(securityService, token, localizationService.add(roomName));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    ResultResponse deleteLocalization(@RequestParam String token, @RequestParam String roomName) {
        System.out.println("DELETE LOCALIZATION: " + roomName);
        return new ResultResponse(securityService, token, localizationService.delete(roomName));
    }
}
