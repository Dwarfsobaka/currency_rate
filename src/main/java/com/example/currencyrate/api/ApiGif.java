package com.example.currencyrate.api;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

@Component
@Qualifier("apiGif")
@FeignClient(value = "gif", url = "${feign.url-gif}")
public interface ApiGif {

    @GetMapping("")
    String getGif(@RequestParam("api_key") String api_key, @RequestParam("q") String q, @RequestParam("limit") String limit);



}
