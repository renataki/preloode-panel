package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.model.global.BaseResponse;

import java.util.Map;


public class CryptocurrencyBinanceTestConnectionResponse extends BaseResponse {


    private Map<String, Object> content;


    public CryptocurrencyBinanceTestConnectionResponse() {


    }


    public CryptocurrencyBinanceTestConnectionResponse(String response, boolean result, Map<String, Object> content) {

        super(response, result);
        this.content = content;

    }


    public Map<String, Object> getContent() {

        return content;

    }


    public void setContent(Map<String, Object> content) {

        this.content = content;

    }


}
