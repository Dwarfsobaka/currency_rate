package com.example.currencyrate.controller;

import com.example.currencyrate.config.ConfigProperties;
import com.example.currencyrate.service.Currency;
import com.example.currencyrate.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@RequestMapping(value = "/rate")
public class CurrencyController {

    private final Service service;


    @Autowired
    public CurrencyController(Service service) {
        this.service = service;
    }

    @GetMapping()
    public String formCurrency(Model model) {
      Currency currency = new Currency();
     model.addAttribute("currency", currency);
        return "formCurrency";
    }

    @PostMapping("")
    public String submitForm(@ModelAttribute("currency") Currency currency) {
        if(isHigher(currency.getCurrency())) {
            return "gifFileRich";
        }
        else {
            return "gifFilePoor";}
    }

    public boolean isHigher (String currency){

       service.setLatest(service.getMapFromJsonLatest());
       service.setYesterday(service.getMapFromJsonYesterday());

        Map<String, Double> latest = service.getLatest();
        Map <String, Double> yesterday = service.getYesterdayRate();

        for (String key: latest.keySet()) {
            if(key.equalsIgnoreCase(currency)) {
                service.setRateLatest(latest.get(key));
            }}

        for (String key: yesterday.keySet()) {
            if(key.equalsIgnoreCase(currency)) {
                service.setRateYesterday(yesterday.get(key));
            }}

        int compareValue = service.getRateLatest().compareTo( service.getRateYesterday());
        return compareValue > 0;
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
