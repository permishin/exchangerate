package com.work.exchangeRate.service;

import com.work.exchangeRate.ApiInterface.GiphyClient;
import com.work.exchangeRate.model.Giphy;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class GiphyClientTest {

    @Autowired
    private GiphyClient giphyClient;

    /**
     * Проверка возвращаемой строки - начало URL содержит "https://media",
     * а конец "rid=giphy.gif&ct=g"
     */
    @Test
    void giphyPic() {
        Giphy giphy = giphyClient.giphyPic("rich");
        String q = giphy.getResponse().getGif();
        String start = q.substring(0, 13);
        String end = q.substring(q.length() - 18);
        String gifLinkStart = "https://media";
        String gifLinkEnd = "rid=giphy.gif&ct=g";
        Assert.assertEquals(gifLinkStart, start);
        Assert.assertEquals(gifLinkEnd, end);
    }
}