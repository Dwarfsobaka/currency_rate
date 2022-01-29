package com.example.currencyrate.controller;

import com.example.currencyrate.config.ConfigProperties;
import com.example.currencyrate.service.Currency;
import com.example.currencyrate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    public String formCurrency(Model model) {
     Currency currency = new Currency();
     model.addAttribute("currency", currency);
//      ArrayList<String> list = new ArrayList<>(service.getMapFromJsonLatest().keySet());
//      model.addAttribute("listOfCurrency", list);
      //Collections.sort(list);
        return "formCurrency";
    }

    @PostMapping("")
    public String submitForm(@ModelAttribute("currency") Currency currency) {
        System.out.println(currency.getCurrency());
        return "test";
    }


//    @GetMapping()
//    public String getRate(@RequestParam("currency") String currency) {
//        if (!currency.equalsIgnoreCase(configProperties.getCurrency())){
//            return HttpStatus.BAD_REQUEST.toString();
//        }
//        if(service.isHigher()) {
//            return "gifFileRich";
//        }
//        else {
//            return "gifFilePoor";}
//
//    }


}
