package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.LocalizationService;
import com.brotherhood.stocktaking.services.SecurityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("localization")
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

    @RequestMapping(path = "/", method = RequestMethod.POST)
    ResultResponse createLocalization(@RequestParam String token, @RequestParam String roomName) {
        return new ResultResponse(securityService, token, localizationService.add(roomName));
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    ResultResponse deleteLocalization(@RequestParam String token, @RequestParam String roomName) {
        return new ResultResponse(securityService, token, localizationService.delete(roomName));
    }
}
