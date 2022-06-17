package com.work.exchangeRate.service;

import com.work.exchangeRate.ApiInterface.RateClient;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class ExchangeMethods {

    /**
     * Сравнение валютного курса за два дня
     *
     * @param costToday     валютный курс на сегодня
     * @param costYesterday валютный курс на вчера
     * @return возвращает "rich" или "broke" для подстановки в сервис giphy
     */
    public static String calculator(Double costToday, Double costYesterday) {
        return (costToday <= costYesterday) ? "broke" : "rich";
    }

    /**
     * Фильтрация map по значению, полученного из формы form.html
     *
     * @param filter     ключевое значение для фильтрации
     * @param rateClient интерфейс API сервиса openexchangerates.org/api
     * @return отфильтрованная map
     */
    public static Map<String, Double> filter(String filter, RateClient rateClient) {
        Map<String, Double> filteredRates = new TreeMap<>();
        if (filter != null && !filter.isEmpty()) {
            for (Map.Entry<String, Double> value : rateClient.getLatestRates().getRates().entrySet()) {
                if (value.getKey().contains(filter.toUpperCase())) {
                    filteredRates.put(value.getKey(), value.getValue());
                }
            }
        } else {
            filteredRates = rateClient.getLatestRates().getRates();
        }
        return filteredRates;
    }
}
