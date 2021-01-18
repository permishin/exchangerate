package com.example.demo.service;

import com.example.demo.controller.EditController;
import com.example.demo.model.Page;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class PageClientTest {

    @Autowired
    private PageClient pageClient;

    @Test
    void returnValueNotNull() {
        Page actualValye = pageClient.getLatestPage();
        Page oldValye = pageClient.getHistoricalPage("2020-10-10","rich");
        Assert.assertNotNull(actualValye);
        Assert.assertNotNull(oldValye);
    }
}