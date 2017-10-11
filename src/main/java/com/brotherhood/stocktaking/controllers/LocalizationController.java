package com.brotherhood.stocktaking.controllers;

import com.brotherhood.stocktaking.models.responses.ResultResponse;
import com.brotherhood.stocktaking.services.LocalizationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("localization")
@Api(tags = "localization")
public class LocalizationController {
    private LocalizationService localizationService;

    @Autowired
    public LocalizationController(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    ResultResponse createLocalization(@RequestParam String roomName) {
        ResultResponse response = new ResultResponse();
        response.setSuccess(localizationService.add(roomName));
        return response;
    }

    @RequestMapping(path = "/", method = RequestMethod.DELETE)
    ResultResponse deleteLocalization(@RequestParam String roomName) {
        ResultResponse response = new ResultResponse();
        response.setSuccess(localizationService.delete(roomName));
        return response;
    }
}
