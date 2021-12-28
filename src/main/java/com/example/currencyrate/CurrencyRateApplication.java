package com.example.currencyrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
//@EnableConfigurationProperties(ConfigProperties.class)
@EnableFeignClients
public class CurrencyRateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyRateApplication.class, args);

    }

}
