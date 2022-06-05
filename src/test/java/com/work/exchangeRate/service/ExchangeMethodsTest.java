package com.work.exchangeRate.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class ExchangeMethodsTest {

    @Autowired
    private ExchangeMethods exchangeMethods;

    @Test
    void calculatorTest() {
        double a, b;
        a = 10.1;
        b = 5.2;
        Assert.assertEquals("rich", exchangeMethods.calculator(a,b));
        Assert.assertEquals("broke", exchangeMethods.calculator(b,a));
    }
}