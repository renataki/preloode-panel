package com.preloode.panel.configuration.api.bittrex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiBittrexConfiguration {


    @Value("${api.bittrex.api-key}")
    private String apiKey;

    @Autowired
    private ApiBittrexFeeConfiguration fee;

    @Value("${api.bittrex.secret-key}")
    private String secretKey;

    @Autowired
    private ApiBittrexUrlConfiguration url;


    public String getApiKey() {

        return apiKey;

    }


    public void setApiKey(String apiKey) {

        this.apiKey = apiKey;

    }


    public ApiBittrexFeeConfiguration getFee() {

        return fee;

    }


    public void setFee(ApiBittrexFeeConfiguration fee) {

        this.fee = fee;

    }


    public String getSecretKey() {

        return secretKey;

    }


    public void setSecretKey(String secretKey) {

        this.secretKey = secretKey;

    }


    public ApiBittrexUrlConfiguration getUrl() {

        return url;

    }


    public void setUrl(ApiBittrexUrlConfiguration url) {

        this.url = url;

    }


}
