package com.work.exchangeRate.service;


import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
public class ExchangeMethods {

    /**
     * Сравнение валютного курса за два дня
     * @param costToday валютный курс на сегодня
     * @param costYesterday валютный курс на вчера
     * @return возвращает "rich" или "broke" для подстановки в сервис giphy
     */
    public String calculator(Double costToday, Double costYesterday) {
        String result = (costToday <= costYesterday) ? "broke" : "rich";
        return result;
    }

    /**
     * Фильтрация map по значению, полученного из формы form.html
     * @param filter ключевое значение для фильтрации
     * @param pageClient интерфейс API сервиса openexchangerates.org/api
     * @return отфильтрованная map
     */
    public Map<String, Double> filterMethod(String filter, PageClient pageClient) {
        Map<String, Double> filteredRates = new TreeMap<>();
        if (filter != null && !filter.isEmpty()) {
            for (Map.Entry<String, Double> value : pageClient.getLatestPage().getRates().entrySet()) {
                if (value.getKey().contains(filter.toUpperCase())) {
                    filteredRates.put(value.getKey(), value.getValue());
                }
            }
        } else {
            filteredRates = pageClient.getLatestPage().getRates();
        }
        return filteredRates;
    }



}
