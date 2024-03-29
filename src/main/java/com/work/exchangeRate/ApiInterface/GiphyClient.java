package com.work.exchangeRate.ApiInterface;

import com.work.exchangeRate.model.Giphy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "GiphyClient",
        url = "${giphy.ribbon.listOfServers}"
)
public interface GiphyClient {

    @GetMapping
            (
                    value = "?api_key=${api.giphyId}&tag={value}&rating=g",
                    consumes = MediaType.APPLICATION_JSON_VALUE
            )
    Giphy giphyPic(@PathVariable() String value);
}
