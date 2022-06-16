package com.work.exchangeRate.model;


import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Map;

@JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.CUSTOM)
public class Images {

    private Map<String, String> original;

    public Map<String, String> getOriginal() {
        return original;
    }


}
