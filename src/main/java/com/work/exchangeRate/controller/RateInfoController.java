package com.work.exchangeRate.controller;

import com.work.exchangeRate.service.ExchangeMethods;
import com.work.exchangeRate.ApiInterface.GiphyClient;
import com.work.exchangeRate.model.Rate;
import com.work.exchangeRate.ApiInterface.RateClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
public class RateInfoController {

    private final RateClient rateClient;
    private final GiphyClient giphyClient;

    public RateInfoController(RateClient rateClient, GiphyClient giphyClient) {
        this.rateClient = rateClient;
        this.giphyClient = giphyClient;
    }

    /**
     * Get метод страницы выбранного валютного значения с курсом валюты за сегодня и вчера
     *
     * @param getKey валютное значение
     * @param model  атрибуты, используемые для визуализации представлений
     * @return возвращает данные на страницу details
     */
    @GetMapping("/{getKey}")
    public String details(@PathVariable(value = "getKey") String getKey, Model model) {
        Map<String, Double> details = new TreeMap<>();
        String yesterday = LocalDate.now().minusDays(1).toString();
        Rate pages = rateClient.getHistoricalRate(yesterday, getKey);
        Double oldCurrency = pages.getRates().get(getKey);
        Double todayCurrency = rateClient.getLatestRates().getRates().get(getKey);
        details.put(getKey, todayCurrency);
        Object gif = giphyClient.giphyPic(ExchangeMethods.calculator(todayCurrency, oldCurrency)).getGif();
        model.addAttribute("oldCurrency", oldCurrency);
        model.addAttribute("dates", yesterday);
        model.addAttribute("gif", gif);
        model.addAttribute("details", details);
        model.addAttribute("title", "Курсы Валют - сравнение");
        return "details";
    }
}
