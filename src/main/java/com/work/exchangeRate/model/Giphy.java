package com.work.exchangeRate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Giphy {

    private Request request;
    private Images images;

    public Request getResponse() {
        return request;
    }

    public Images getImages() {
        return images;
    }
}
