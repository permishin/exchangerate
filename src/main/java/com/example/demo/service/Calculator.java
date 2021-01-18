package com.example.demo.service;


import org.springframework.stereotype.Service;

@Service
public class Calculator {

    public String result(Double today, Double old) {
        String res = (today > old) ? "rich" : "broke";
        return res;
    }

}
