package com.example.currencyrate.api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Qualifier("apiCurrency")
@FeignClient(value = "currencyRate", url = "${feign.url-rate}")
public interface ApiCurrency {

       @GetMapping("/latest.json")
       String getLatestRate(@RequestParam("app_id") String app_id);

       @GetMapping("/historical/{date}.json")
       String getHistoricalRate(@RequestParam("app_id") String app_id, @PathVariable("date") String date);



}
