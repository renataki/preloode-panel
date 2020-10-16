package com.preloode.panel.configuration.api.bitfinex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiBitfinexUrlConfiguration {


    @Value("${api.bitfinex.url.base-authenticated}")
    private String baseAuthenticated;


    @Value("${api.bitfinex.url.base-public}")
    private String basePublic;

    @Value("${api.bitfinex.url.websocket}")
    private String websocket;


    public String getBaseAuthenticated() {

        return baseAuthenticated;

    }


    public void setBaseAuthenticated(String baseAuthenticated) {

        this.baseAuthenticated = baseAuthenticated;

    }


    public String getBasePublic() {

        return basePublic;

    }


    public void setBasePublic(String basePublic) {

        this.basePublic = basePublic;

    }


    public String getWebsocket() {

        return websocket;

    }


    public void setWebsocket(String websocket) {

        this.websocket = websocket;

    }


}
