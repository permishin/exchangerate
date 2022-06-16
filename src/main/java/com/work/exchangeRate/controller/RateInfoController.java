package com.work.exchangeRate.controller;

import com.work.exchangeRate.service.ExchangeMethods;
import com.work.exchangeRate.ApiInterface.GiphyClient;
import com.work.exchangeRate.model.Rate;
import com.work.exchangeRate.ApiInterface.RateClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class RateInfoController {

    private final RateClient rateClient;
    private final GiphyClient giphyClient;

    private final ExchangeMethods exchangeMethods;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public RateInfoController(RateClient rateClient, GiphyClient giphyClient, ExchangeMethods exchangeMethods) {
        this.rateClient = rateClient;
        this.giphyClient = giphyClient;
        this.exchangeMethods = exchangeMethods;
    }

    /**
     * Get метод страницы выбранного валютного значения с курсом валюты за сегодня и вчера
     *
     * @param currency валютное значение
     * @param model  атрибуты, используемые для визуализации представлений
     * @return возвращает данные на страницу details
     */
    @GetMapping("/{getKey}")
    public String details(@PathVariable(value = "getKey") String currency, Model model) {
        Map<String, BigDecimal> details = new TreeMap<>();
        String yesterday = formatter.format(LocalDate.now().minusDays(1));
        Rate pages = rateClient.getHistoricalRate(yesterday, currency);
        BigDecimal oldCurrency = pages.getRates().get(currency);
        BigDecimal todayCurrency = rateClient.getLatestRates().getRates().get(currency);
        details.put(currency, todayCurrency);
        Object gif = giphyClient.giphyPic(exchangeMethods.calculate(todayCurrency, oldCurrency)).getGif();
        model.addAttribute("oldCurrency", oldCurrency);
        model.addAttribute("dates", yesterday);
        model.addAttribute("gif", gif);
        model.addAttribute("details", details);
        model.addAttribute("title", "Курсы Валют - сравнение");
        return "details";
    }
}
