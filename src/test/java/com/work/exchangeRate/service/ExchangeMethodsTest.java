package com.work.exchangeRate.service;

import com.work.exchangeRate.ApiInterface.RateClient;
import com.work.exchangeRate.model.Rate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ExchangeMethodsTest {

    RateClient rateClient;
    HashMap<String, Double> rates;
    Double costToday;
    Double costYesterday;

    @BeforeEach
    void setUp() {
        costToday = 10.97;
        costYesterday = 12.3;

        rates = new HashMap<>();
        rates.put("RUB", 12.32);
        rates.put("EUR", 1.10);
        rates.put("FJD", 2.15);
        rates.put("USD", 43.9);
        rates.put("MRU", 11.1);

        rateClient = new RateClient() {
            @Override
            public Rate getLatestRates() {
                Rate rate = new Rate();
                rate.setRates(rates);
                return rate;
            }

            @Override
            public Rate getHistoricalRate(String userDate, String value) {
                return null;
            }
        };
    }

    /**
     * Тест на проверку возвращаемого значения метода calculator с условием падения курса валюты,
     * роста, отсутствия изменений и возврата значения null
     */
    @Test
    public void testCalculator() {
        String actualDown = ExchangeMethods.calculator(costToday, costYesterday);
        String actualUp = ExchangeMethods.calculator(costYesterday, costToday);
        String actual = ExchangeMethods.calculator(costToday, costToday);
        String expectedDown = "broke";
        String expectedUp = "rich";
        Assertions.assertEquals(expectedDown, actualDown);
        Assertions.assertEquals(expectedUp, actualUp);
        Assertions.assertEquals(expectedDown, actual);
        try {
            Object actualNull = ExchangeMethods.calculator(null, costToday);
            Assertions.assertNull(actualNull);
        } catch (NullPointerException ex) {
            ex.getStackTrace();
        }
    }

    /**
     * Тест на проверку фильтрации валют по ключевому слову, пустой строке, и частичному запросу
     */
    @Test
    public void testFilter() {
        Map<String, Double> actualRub = ExchangeMethods.filter("RUB", rateClient);
        Map<String, Double> actualEmpty = ExchangeMethods.filter("", rateClient);
        Map<String, Double> actualRu = ExchangeMethods.filter("ru", rateClient);
        Map<String, Double> expectedRub = new HashMap<>();
        expectedRub.put("RUB", 12.32);
        Map<String, Double> expectedRu = new HashMap<>();
        expectedRu.put("RUB", 12.32);
        expectedRu.put("MRU", 11.1);
        Assertions.assertEquals(expectedRub, actualRub);
        Assertions.assertEquals(rates, actualEmpty);
        Assertions.assertEquals(expectedRu, actualRu);
    }
}