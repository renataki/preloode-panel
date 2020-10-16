package com.preloode.panel.model.cryptocurrency.bittrex;

import java.math.BigDecimal;


public class CryptocurrencyBittrexMarketTicker {


    private String symbol;

    private BigDecimal lastTradeRate;

    private BigDecimal bidRate;

    private BigDecimal askRate;


    public CryptocurrencyBittrexMarketTicker() {


    }


    public CryptocurrencyBittrexMarketTicker(String symbol, BigDecimal lastTradeRate, BigDecimal bidRate, BigDecimal askRate) {

        this.symbol = symbol;
        this.lastTradeRate = lastTradeRate;
        this.bidRate = bidRate;
        this.askRate = askRate;

    }


    public String getSymbol() {

        return symbol;

    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;

    }


    public BigDecimal getLastTradeRate() {

        return lastTradeRate;

    }


    public void setLastTradeRate(BigDecimal lastTradeRate) {

        this.lastTradeRate = lastTradeRate;

    }


    public BigDecimal getBidRate() {

        return bidRate;

    }


    public void setBidRate(BigDecimal bidRate) {

        this.bidRate = bidRate;

    }


    public BigDecimal getAskRate() {

        return askRate;

    }


    public void setAskRate(BigDecimal askRate) {

        this.askRate = askRate;

    }


}
