package com.preloode.panel.model.cryptocurrency.binance;

import java.math.BigDecimal;


public class CryptocurrencyBinanceOrderBookTicker {


    private String symbol;

    private BigDecimal bidPrice;

    private BigDecimal bidQty;

    private BigDecimal askPrice;

    private BigDecimal askQty;


    public CryptocurrencyBinanceOrderBookTicker() {


    }


    public CryptocurrencyBinanceOrderBookTicker(String symbol, BigDecimal bidPrice, BigDecimal bidQty, BigDecimal askPrice, BigDecimal askQty) {

        this.symbol = symbol;
        this.bidPrice = bidPrice;
        this.bidQty = bidQty;
        this.askPrice = askPrice;
        this.askQty = askQty;

    }


    public String getSymbol() {

        return symbol;

    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;

    }


    public BigDecimal getBidPrice() {

        return bidPrice;

    }


    public void setBidPrice(BigDecimal bidPrice) {

        this.bidPrice = bidPrice;

    }


    public BigDecimal getBidQty() {

        return bidQty;

    }


    public void setBidQty(BigDecimal bidQty) {

        this.bidQty = bidQty;

    }


    public BigDecimal getAskPrice() {

        return askPrice;

    }


    public void setAskPrice(BigDecimal askPrice) {

        this.askPrice = askPrice;

    }


    public BigDecimal getAskQty() {

        return askQty;

    }


    public void setAskQty(BigDecimal askQty) {

        this.askQty = askQty;

    }


}
