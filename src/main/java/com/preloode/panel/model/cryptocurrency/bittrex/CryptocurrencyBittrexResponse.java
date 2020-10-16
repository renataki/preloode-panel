package com.preloode.panel.model.cryptocurrency.bittrex;

import com.preloode.panel.model.global.BaseResponse;

import java.util.List;


public class CryptocurrencyBittrexResponse extends BaseResponse {


    private List<CryptocurrencyBittrexBalance> balanceList;

    private List<CryptocurrencyBittrexCurrency> currencyList;

    private List<CryptocurrencyBittrexMarketTicker> marketTickerList;


    public CryptocurrencyBittrexResponse() {


    }


    public CryptocurrencyBittrexResponse(String response, boolean result, List<CryptocurrencyBittrexBalance> balanceList, List<CryptocurrencyBittrexCurrency> currencyList, List<CryptocurrencyBittrexMarketTicker> marketTickerList) {

        super(response, result);
        this.balanceList = balanceList;
        this.currencyList = currencyList;
        this.marketTickerList = marketTickerList;

    }


    public List<CryptocurrencyBittrexBalance> getBalanceList() {

        return balanceList;

    }


    public void setBalanceList(List<CryptocurrencyBittrexBalance> balanceList) {

        this.balanceList = balanceList;

    }


    public List<CryptocurrencyBittrexCurrency> getCurrencyList() {

        return currencyList;

    }


    public void setCurrencyList(List<CryptocurrencyBittrexCurrency> currencyList) {

        this.currencyList = currencyList;

    }


    public List<CryptocurrencyBittrexMarketTicker> getMarketTickerList() {

        return marketTickerList;

    }


    public void setMarketTickerList(List<CryptocurrencyBittrexMarketTicker> marketTickerList) {

        this.marketTickerList = marketTickerList;

    }


}
