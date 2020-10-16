package com.preloode.panel.configuration.api.kraken;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiKrakenUrlConfiguration {


    @Value("${api.kraken.url.base}")
    private String base;

    @Value("${api.kraken.url.websocket}")
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
