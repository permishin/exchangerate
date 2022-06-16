package com.work.exchangeRate.service;

import com.work.exchangeRate.ApiInterface.RateClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExchangeMethods {

    private final RateClient rateClient;

    public ExchangeMethods(RateClient rateClient) {
        this.rateClient = rateClient;
    }

    /**
     * Сравнение валютного курса за два дня
     *
     * @param costToday     валютный курс на сегодня
     * @param costYesterday валютный курс на вчера
     * @return возвращает "rich" или "broke" для подстановки в сервис giphy
     */
    public static String calculator(BigDecimal costToday, BigDecimal costYesterday) {
        return costToday.compareTo(costYesterday) >= 0 ? "broke" : "rich";
    }

    /**
     * Фильтрация map по значению, полученного из формы form.html
     *
     * @param filter ключевое значение для фильтрации
     * @return отфильтрованная map
     */
    public Map<String, BigDecimal> filter(String filter) {
        if (filter != null && !filter.isEmpty()) {
            return rateClient.getLatestRates().getRates().entrySet().stream()
                    .filter(entry -> entry.getKey().contains(filter.toUpperCase()))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        } else {
            return rateClient.getLatestRates().getRates();
        }
    }
}
