package com.work.exchangeRate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {

    private HashMap<String, BigDecimal> rates;

    public HashMap<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(HashMap<String, BigDecimal> rates) {
        this.rates = rates;
    }
}