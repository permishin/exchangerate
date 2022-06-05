package com.work.exchangeRate.controller;

import com.work.exchangeRate.service.ExchangeMethods;
import com.work.exchangeRate.service.PageClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    private final PageClient pageClient;

    private final ExchangeMethods exchangeMethods;

    public MainController(PageClient pageClient, ExchangeMethods exchangeMethods) {
        this.pageClient = pageClient;
        this.exchangeMethods = exchangeMethods;
    }

    /**
     * Get метод главной страницы. Отображает список всех валют.
     * @param model атрибуты, используемые для визуализации представлений
     * @return возвращает данные на страницу main
     */
    @GetMapping("/")
    public String main(Model model) {
        model.addAttribute("page", pageClient.getLatestPage().getRates());
        model.addAttribute("title", "Курсы Валют - тестовое задание");
        return "main";
    }

    /**
     * Post метод формы с фильтром. Позволяет отфильтровать map по ключевому слову или букве
     * @param filter параметр с ключевым словом
     * @param model атрибуты, используемые для визуализации представлений
     * @return возвращает данные на страницу main
     */
    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Model model) {
        model.addAttribute("page", exchangeMethods.filterMethod(filter, pageClient));
        return "main";
    }
}
