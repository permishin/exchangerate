package com.work.exchangeRate.service;

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

    @Test
    void giphyPic() {
        Giphy giphy = giphyClient.giphyPic("test");
        Assert.assertNotNull(giphy);
    }
}