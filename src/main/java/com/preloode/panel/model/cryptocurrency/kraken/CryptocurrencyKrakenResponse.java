package com.preloode.panel.model.cryptocurrency.kraken;

import com.preloode.panel.model.global.BaseResponse;

import java.util.Map;


public class CryptocurrencyKrakenResponse extends BaseResponse {


    private Map<String, Object> cryptocurrencyKrakenAssetPairList;

    private Map<String, Object> cryptocurrencyKrakenTickerInformationList;


    public CryptocurrencyKrakenResponse() {


    }


    public CryptocurrencyKrakenResponse(String response, boolean result, Map<String, Object> cryptocurrencyKrakenAssetPairList, Map<String, Object> cryptocurrencyKrakenTickerInformationList) {

        super(response, result);
        this.cryptocurrencyKrakenAssetPairList = cryptocurrencyKrakenAssetPairList;
        this.cryptocurrencyKrakenTickerInformationList = cryptocurrencyKrakenTickerInformationList;

    }


    public Map<String, Object> getCryptocurrencyKrakenAssetPairList() {

        return cryptocurrencyKrakenAssetPairList;

    }


    public void setCryptocurrencyKrakenAssetPairList(Map<String, Object> cryptocurrencyKrakenAssetPairList) {

        this.cryptocurrencyKrakenAssetPairList = cryptocurrencyKrakenAssetPairList;

    }


    public Map<String, Object> getCryptocurrencyKrakenTickerInformationList() {

        return cryptocurrencyKrakenTickerInformationList;

    }


    public void setCryptocurrencyKrakenTickerInformationList(Map<String, Object> cryptocurrencyKrakenTickerInformationList) {

        this.cryptocurrencyKrakenTickerInformationList = cryptocurrencyKrakenTickerInformationList;

    }


}
