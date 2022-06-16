package com.work.exchangeRate.service;

import com.work.exchangeRate.ApiInterface.RateClient;
import com.work.exchangeRate.model.Rate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class ExchangeMethodsTest {

    @Mock
    RateClient rateClient;

    @InjectMocks
    ExchangeMethods exchangeMethods;

    HashMap<String, BigDecimal> rates;
    BigDecimal costToday;
    BigDecimal costYesterday;

    @BeforeEach
    void setUp() {
        costToday = BigDecimal.valueOf(10.97);
        costYesterday = BigDecimal.valueOf(12.3);
        rates = new HashMap<>();
        rates.put("RUB", BigDecimal.valueOf(12.32));
        rates.put("EUR", BigDecimal.valueOf(1.10));
        rates.put("FJD", BigDecimal.valueOf(2.15));
        rates.put("USD", BigDecimal.valueOf(43.9));
        rates.put("MRU", BigDecimal.valueOf(11.1));

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
        String actualDown = exchangeMethods.calculator(costToday, costYesterday);
        String actualUp = exchangeMethods.calculator(costYesterday, costToday);
        String actual = exchangeMethods.calculator(costToday, costToday);
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
        Map<String, BigDecimal> actualRub = exchangeMethods.filter("RUB");
        Map<String, BigDecimal> actualEmpty = exchangeMethods.filter("");
        Map<String, BigDecimal> actualRu = exchangeMethods.filter("ru");
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