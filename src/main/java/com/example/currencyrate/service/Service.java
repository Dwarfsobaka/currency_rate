package com.example.currencyrate.service;

import com.example.currencyrate.config.ConfigProperties;
import com.example.currencyrate.api.ApiCurrency;
import com.example.currencyrate.api.ApiGif;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private Map<String, Double> latestRateMap;
    private Map <String, Double> yesterdayRateMap;
    private Double rateLatest;
    private Double rateYesterday;

    public Double getRateLatest() {
        return rateLatest;
    }

    public void setRateLatest(Double rateLatest) {
        this.rateLatest = rateLatest;
    }

    public Double getRateYesterday() {
        return rateYesterday;
    }

    public void setRateYesterday(Double rateYesterday) {
        this.rateYesterday = rateYesterday;
    }

    public Map<String, Double> getLatest() {
        return latestRateMap;
    }

    public Map<String, Double> getYesterdayRate() {
        return yesterdayRateMap;
    }

    public void setLatest(Map<String, Double> latestRate) {
        this.latestRateMap = latestRate;
    }

    public void setYesterday(Map<String, Double> yesterdayRate) {
        this.yesterdayRateMap = yesterdayRate;
    }

    @Autowired
    public Service(@Qualifier("apiCurrency") ApiCurrency proxy, ConfigProperties configProperties, @Qualifier("apiGif")ApiGif apiGif) {
        this.proxy = proxy;
        this.configProperties = configProperties;
        this.apiGif = apiGif;
    }

    //парсим json, получаем карту последних значений
    public Map <String, Double> getMapFromJsonLatest(){
      Map <String, Double> ratesLatest = new TreeMap<>();
      String json = proxy.getLatestRate(configProperties.getAppId());
      JSONObject obj;
      try {
          obj = (JSONObject) JSONValue.parseWithException(json);
          JSONObject rates = (JSONObject)obj.get("rates");
          ratesLatest = new ObjectMapper().readValue(rates.toJSONString(), new TypeReference<Map<String, Double>>(){});
      } catch (JsonProcessingException | ParseException e) {
          e.printStackTrace();
      }
      return ratesLatest;
  }

    //парсим json, получаем карту вчерашних значений
    public Map <String, Double> getMapFromJsonYesterday(){
        Map <String, Double> ratesYestarday = new TreeMap<>();
        String json = proxy.getHistoricalRate(configProperties.getAppId(), getYesterday());
        JSONObject obj;
        try {
            obj = (JSONObject) JSONValue.parseWithException(json);
            JSONObject rates = (JSONObject)obj.get("rates");
            ratesYestarday = new ObjectMapper().readValue(rates.toJSONString(), new TypeReference<Map<String, Double>>(){});
        } catch (JsonProcessingException | ParseException e) {
            e.printStackTrace();
        }
        return ratesYestarday;
    }

    public String getYesterday(){
        SimpleDateFormat formatOfDate = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date yesterday = cal.getTime();
        return formatOfDate.format(yesterday);
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
