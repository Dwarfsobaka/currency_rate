package com.example.currencyrate.controller;

import com.example.currencyrate.config.ConfigProperties;
import com.example.currencyrate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/rate")
public class CurrencyController {

    private final Service service;
    private final ConfigProperties configProperties;

    @Autowired
    public CurrencyController(Service service, ConfigProperties configProperties) {
        this.service = service;
        this.configProperties = configProperties;
    }


    @GetMapping()
    public String getRate(@RequestParam("currency") String currency) {
        if (!currency.equalsIgnoreCase(configProperties.getCurrency())){
            return HttpStatus.BAD_REQUEST.toString();
        }
        if(service.isHigher()) {
            return "gifFileRich";
        }
        else {
            return "gifFilePoor";}

    }


}
