package com.work.exchangeRate.service;

import com.work.exchangeRate.ApiInterface.RateClient;
import com.work.exchangeRate.model.Rate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class RateClientTest {

    @Autowired
    private RateClient rateClient;

    /**
     * Проверка стоимости валюты на конкретную дату и подсчет общего количества валют
     */
    @Test
    void testRates() {
        Rate actualValue = rateClient.getLatestRates();
        Rate oldValue = rateClient.getHistoricalRate("2020-10-10","RUB");
        Map<String, Double> oldValueMap = new HashMap<>();
        int currentCountRates = 169;
        oldValueMap.put("RUB", 76.7662);
        Assertions.assertEquals(oldValueMap, oldValue.getRates());
        Assertions.assertEquals(currentCountRates, actualValue.getRates().size());
    }
}