package com.work.exchangeRate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Giphy {

    private Giphy data;
    private Images images;

    public Object getGif() {
        return getData()
                .getImages()
                .getOriginal()
                .get("url");
    }

    public Giphy getData() {
        return data;
    }

    public Images getImages() {
        return images;
    }
}
