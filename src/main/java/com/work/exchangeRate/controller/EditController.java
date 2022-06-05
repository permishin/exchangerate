package com.work.exchangeRate.controller;

import com.work.exchangeRate.service.ExchangeMethods;
import com.work.exchangeRate.service.GiphyClient;
import com.work.exchangeRate.model.Page;
import com.work.exchangeRate.service.PageClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("{getKey}")
public class EditController {


    private final PageClient pageClient;

    private final GiphyClient giphyClient;

    private final ExchangeMethods exchangeMethods;

    public EditController(PageClient pageClient, GiphyClient giphyClient, ExchangeMethods exchangeMethods) {
        this.pageClient = pageClient;
        this.giphyClient = giphyClient;
        this.exchangeMethods = exchangeMethods;
    }

    /**
     * Get метод страницы выбранного валютного значения с курсом валюты за сегодня и вчера
     * @param getKey валютное значение
     * @param model атрибуты, используемые для визуализации представлений
     * @return возвращает данные на страницу details
     */
    @GetMapping
    public String details(@PathVariable(value = "getKey") String getKey, Model model) {
        Map<String, Double> details = new TreeMap<>();
        String yesterday = LocalDate.now().minusDays(1).toString();
        Page pages = pageClient.getHistoricalPage(yesterday, getKey);
        Double oldCurrency = pages.getRates().get(getKey);
        Double todayCurrency = pageClient.getLatestPage().getRates().get(getKey);
        details.put(getKey, todayCurrency);
        Object gif = giphyClient.giphyPic(exchangeMethods.calculator(todayCurrency, oldCurrency))
                .getData()
                .getImages()
                .getOriginal()
                .get("url");
        model.addAttribute("oldCurrency", oldCurrency);
        model.addAttribute("dates", yesterday);
        model.addAttribute("gif", gif);
        model.addAttribute("details", details);
        model.addAttribute("title", "Курсы Валют - сравнение");
        return "details";
    }
}
