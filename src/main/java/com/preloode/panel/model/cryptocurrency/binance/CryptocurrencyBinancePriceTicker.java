package com.preloode.panel.model.cryptocurrency.binance;

import java.math.BigDecimal;


public class CryptocurrencyBinancePriceTicker {


    private String symbol;

    private BigDecimal price;


    public CryptocurrencyBinancePriceTicker() {


    }


    public CryptocurrencyBinancePriceTicker(String symbol, BigDecimal price) {

        this.symbol = symbol;
        this.price = price;

    }


    public String getSymbol() {

        return symbol;

    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;

    }


    public BigDecimal getPrice() {

        return price;

    }


    public void setPrice(BigDecimal price) {

        this.price = price;

    }


}
