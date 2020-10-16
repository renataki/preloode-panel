package com.preloode.panel.configuration.api.binance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiBinanceUrlConfiguration {


    @Value("${api.binance.url.base}")
    private String base;

    @Value("${api.binance.url.websocket}")
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
