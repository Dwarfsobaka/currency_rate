package com.example.currencyrate.service;

public class Currency {
    private String currency;
    private Double rateLatest;
    private Double rateYesterday;

    public Double getRateYesterday() {
        return rateYesterday;
    }

    public void setRateYesterday(Double rateYesterday) {
        this.rateYesterday = rateYesterday;
    }

    public Double getRate() {
        return rateLatest;
    }

    public void setRate(Double rate) {
        this.rateLatest = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
