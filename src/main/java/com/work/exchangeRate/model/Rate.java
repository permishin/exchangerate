package com.work.exchangeRate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    private HashMap<String, Double> rates;

    public HashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, Double> rates) {
        this.rates = rates;
    }
}