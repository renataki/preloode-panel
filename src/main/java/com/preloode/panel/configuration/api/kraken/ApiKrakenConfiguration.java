package com.preloode.panel.configuration.api.kraken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiKrakenConfiguration {


    @Value("${api.kraken.api-key}")
    private String apiKey;

    @Autowired
    private ApiKrakenFeeConfiguration fee;

    @Value("${api.kraken.private-key}")
    private String privateKey;

    @Autowired
    private ApiKrakenUrlConfiguration url;


    public String getApiKey() {

        return apiKey;

    }


    public void setApiKey(String apiKey) {

        this.apiKey = apiKey;

    }


    public ApiKrakenFeeConfiguration getFee() {

        return fee;

    }


    public void setFee(ApiKrakenFeeConfiguration fee) {

        this.fee = fee;

    }


    public String getPrivateKey() {

        return privateKey;

    }


    public void setPrivateKey(String privateKey) {

        this.privateKey = privateKey;

    }


    public ApiKrakenUrlConfiguration getUrl() {

        return url;

    }


    public void setUrl(ApiKrakenUrlConfiguration url) {

        this.url = url;

    }


}
