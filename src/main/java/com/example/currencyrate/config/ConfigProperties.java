package com.example.currencyrate.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "feign")
public class ConfigProperties {

    private String urlRate;
    private String currency;
    private String appId;

    private String urlGif;
    private String apiKey;
    private String requestWordUp;
    private String requestWordDown;
    private String limitGif;

    public String getRequestWordUp() {
        return requestWordUp;
    }

    public String getRequestWordDown() {
        return requestWordDown;
    }

    public String getLimitGif() {
        return limitGif;
    }

    public void setRequestWordUp(String requestWordUp) {
        if (requestWordUp!=null) {
            this.requestWordUp = requestWordUp;
        }
        else throw new NullPointerException("The field cannot be empty");
    }

    public void setRequestWordDown(String requestWordDown) {
        if (requestWordDown!=null) {
            this.requestWordDown = requestWordDown;
        }
        else throw new NullPointerException("The field cannot be empty");
    }

    public void setLimitGif(String limitGif) {
        if (limitGif!=null) {
            this.limitGif = limitGif;
        }
        else throw new NullPointerException("The field cannot be empty");
    }

    public String getUrlGif() {
        return urlGif;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setUrlGif(String urlGif) {
        if (urlGif!=null) {
            this.urlGif = urlGif;
        }
        else throw new NullPointerException("The field cannot be empty");
    }

    public void setApiKey(String apiKey) {
        if (apiKey!=null) {
            this.apiKey = apiKey;
        }
        else throw new NullPointerException("The field cannot be empty");
    }

    public void setUrlRate(String urlRate) {
        if (urlRate!=null) {
            this.urlRate = urlRate;
        }
        else throw new NullPointerException("The field cannot be empty");
    }

    public void setCurrency(String currency) {
        if (currency!=null) {
            this.currency = currency;
        }
        else throw new NullPointerException("The field cannot be empty");
    }

    public void setAppId(String appId) {
        if (appId!=null) {
            this.appId = appId;
        }
        else throw new NullPointerException("The field cannot be empty");
    }

    public String getUrlRate() {
        return urlRate;
    }

    public String getCurrency() {
        return currency;
    }

    public String getAppId() {
        return appId;
    }
}
