package com.preloode.panel.configuration.api.bitfinex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiBitfinexConfiguration {


    @Value("${api.bitfinex.api-key}")
    private String apiKey;

    @Autowired
    private ApiBitfinexFeeConfiguration fee;

    @Value("${api.bitfinex.secret-key}")
    private String secretKey;

    @Autowired
    private ApiBitfinexUrlConfiguration url;


    public String getApiKey() {

        return apiKey;

    }


    public void setApiKey(String apiKey) {

        this.apiKey = apiKey;

    }


    public ApiBitfinexFeeConfiguration getFee() {

        return fee;

    }


    public void setFee(ApiBitfinexFeeConfiguration fee) {

        this.fee = fee;

    }


    public String getSecretKey() {

        return secretKey;

    }


    public void setSecretKey(String secretKey) {

        this.secretKey = secretKey;

    }


    public ApiBitfinexUrlConfiguration getUrl() {

        return url;

    }


    public void setUrl(ApiBitfinexUrlConfiguration url) {

        this.url = url;

    }


}
