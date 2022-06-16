package com.work.exchangeRate.model;

public class Request {

    private Giphy data;

    public Giphy getData() {
        return data;
    }

    public String getGif() {
        return getData()
                .getImages()
                .getOriginal()
                .get("url");
    }
}
