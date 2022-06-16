package com.work.exchangeRate.ApiInterface;

import com.work.exchangeRate.model.Rate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "PageClient",
        url = "${page.ribbon.listOfServers}")
public interface RateClient {

    @GetMapping
            (
            value = "/latest.json?app_id=${api.valueId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
            )
    Rate getLatestRates();

    @GetMapping
            (
            value = "/historical/{userDate}.json?app_id=${api.valueId}&symbols={value}",
            consumes = MediaType.APPLICATION_JSON_VALUE
            )
    Rate getHistoricalRate(@PathVariable String userDate, @PathVariable String value);
}

