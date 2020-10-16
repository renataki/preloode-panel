package com.preloode.panel.configuration.api.binance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiBinanceConfiguration {


    @Value("${api.binance.api-key}")
    private String apiKey;

    @Autowired
    private ApiBinanceFeeConfiguration fee;

    @Value("${api.binance.recv-window}")
    private int recvWindow;

    @Value("${api.binance.secret-key}")
    private String secretKey;

    @Autowired
    private ApiBinanceUrlConfiguration url;


    public String getApiKey() {

        return apiKey;

    }


    public void setApiKey(String apiKey) {

        this.apiKey = apiKey;

    }


    public ApiBinanceFeeConfiguration getFee() {

        return fee;

    }


    public void setFee(ApiBinanceFeeConfiguration fee) {

        this.fee = fee;

    }


    public int getRecvWindow() {

        return recvWindow;

    }


    public void setRecvWindow(int recvWindow) {

        this.recvWindow = recvWindow;

    }


    public String getSecretKey() {

        return secretKey;

    }


    public void setSecretKey(String secretKey) {

        this.secretKey = secretKey;

    }


    public ApiBinanceUrlConfiguration getUrl() {

        return url;

    }


    public void setUrl(ApiBinanceUrlConfiguration url) {

        this.url = url;

    }


}
