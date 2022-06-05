package com.work.exchangeRate.service;

import com.work.exchangeRate.model.Page;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PageClientTest {

    @Autowired
    private PageClient pageClient;

    @Test
    void returnValueNotNull() {
        Page actualValue = pageClient.getLatestPage();
        Page oldValue = pageClient.getHistoricalPage("2020-10-10","rich");
        Assert.assertNotNull(actualValue);
        Assert.assertNotNull(oldValue);
    }
}