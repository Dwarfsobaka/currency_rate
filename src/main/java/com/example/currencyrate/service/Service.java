package com.example.currencyrate.service;

import com.example.currencyrate.config.ConfigProperties;
import com.example.currencyrate.api.ApiCurrency;
import com.example.currencyrate.api.ApiGif;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


import java.text.SimpleDateFormat;
import java.util.*;

@org.springframework.stereotype.Service
public class Service {

    private final ApiCurrency proxy;
    private final ConfigProperties configProperties;
    private final ApiGif apiGif;


    @Autowired
    public Service(@Qualifier("apiCurrency") ApiCurrency proxy, ConfigProperties configProperties, @Qualifier("apiGif")ApiGif apiGif) {
        this.proxy = proxy;
        this.configProperties = configProperties;
        this.apiGif = apiGif;
    }

    public boolean isHigher (){
        Double latestRate = parseJsonRate(proxy.getLatestRate(configProperties.getAppId()));
        Double historicalRate = parseJsonRate(proxy.getHistoricalRate(configProperties.getAppId(),getYestarday()));
        int compareValue = latestRate.compareTo(historicalRate);

        return compareValue > 0;
    }

    public String getYestarday(){
        SimpleDateFormat formatOfDate = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yestarday = cal.getTime();
        return formatOfDate.format(yestarday);
    }

    //парсим json, получаем ставку по валюте
    public Double parseJsonRate (String json) {
        JSONObject obj;
        Double currency = 0.0;
        try {
            obj = (JSONObject) JSONValue.parseWithException(json);
            JSONObject rates = (JSONObject)obj.get("rates");
            currency = (Double) rates.get(configProperties.getCurrency());
        }
        catch (ParseException e){
            System.out.println("Invalid Json!");
        }
        return currency;
    }

    //парсим json, получаем url гифки
    public String parseJsonGifUp( ) {
        String json = apiGif.getGif(configProperties.getApiKey(),configProperties.getRequestWordUp(), configProperties.getLimitGif());
        String urlStr = null;
        JSONParser jsonParser = new JSONParser();
        Object object;
        try {
            object= jsonParser.parse(json);
            JSONObject jsonObject = (JSONObject) object;
            JSONArray data = (JSONArray) jsonObject.get("data");

            Iterator i = data.iterator();
            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                JSONObject images = (JSONObject) slide.get("images");
                JSONObject original = (JSONObject) images.get("original");
                urlStr= (String)original.get("url");
            }
        } catch (ParseException e) {
            System.out.println("Invalid Json!");
        }

        return urlStr;
    }

    public String parseJsonGifDown( ) {
        String json = apiGif.getGif(configProperties.getApiKey(),configProperties.getRequestWordDown(), configProperties.getLimitGif());
        String url = null;
        JSONParser jsonParser = new JSONParser();
        Object object;
        try {
            object= jsonParser.parse(json);
            JSONObject jsonObject = (JSONObject) object;
            JSONArray data = (JSONArray) jsonObject.get("data");

            Iterator i = data.iterator();
            while (i.hasNext()) {
                JSONObject slide = (JSONObject) i.next();
                JSONObject images = (JSONObject) slide.get("images");
                JSONObject original = (JSONObject) images.get("original");
                url= (String)original.get("url");
            }
        } catch (ParseException e) {
            System.out.println("Invalid Json!");
        }
        return url;
    }

}
