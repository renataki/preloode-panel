package com.preloode.panel.configuration.api.bittrex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiBittrexUrlConfiguration {


    @Value("${api.bittrex.url.base}")
    private String base;

    @Value("${api.bittrex.url.websocket}")
    private String websocket;


    public String getBase() {

        return base;

    }


    public void setBase(String base) {

        this.base = base;

    }


    public String getWebsocket() {

        return websocket;

    }


    public void setWebsocket(String websocket) {

        this.websocket = websocket;

    }


}
