package com.preloode.panel.model.cryptocurrency.bitfinex;

import com.preloode.panel.model.global.BaseResponse;

import java.util.List;


public class CryptocurrencyBitfinexResponse extends BaseResponse {


    private List<CryptocurrencyBitfinexSymbolDetail> cryptocurrencyBitfinexSymbolDetailList;

    private List<List<Object>> cryptocurrencyBitfinexTickerList;


    public CryptocurrencyBitfinexResponse() {


    }


    public CryptocurrencyBitfinexResponse(String response, boolean result, List<CryptocurrencyBitfinexSymbolDetail> cryptocurrencyBitfinexSymbolDetailList, List<List<Object>> cryptocurrencyBitfinexTickerList) {

        super(response, result);
        this.cryptocurrencyBitfinexSymbolDetailList = cryptocurrencyBitfinexSymbolDetailList;
        this.cryptocurrencyBitfinexTickerList = cryptocurrencyBitfinexTickerList;

    }


    public List<CryptocurrencyBitfinexSymbolDetail> getCryptocurrencyBitfinexSymbolDetailList() {

        return cryptocurrencyBitfinexSymbolDetailList;

    }


    public void setCryptocurrencyBitfinexSymbolDetailList(List<CryptocurrencyBitfinexSymbolDetail> cryptocurrencyBitfinexSymbolDetailList) {

        this.cryptocurrencyBitfinexSymbolDetailList = cryptocurrencyBitfinexSymbolDetailList;

    }


    public List<List<Object>> getCryptocurrencyBitfinexTickerList() {

        return cryptocurrencyBitfinexTickerList;

    }


    public void setCryptocurrencyBitfinexTickerList(List<List<Object>> cryptocurrencyBitfinexTickerList) {

        this.cryptocurrencyBitfinexTickerList = cryptocurrencyBitfinexTickerList;

    }


}
