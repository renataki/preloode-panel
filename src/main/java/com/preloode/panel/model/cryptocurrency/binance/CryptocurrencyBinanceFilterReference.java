package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceFilterType;

import java.math.BigDecimal;
import java.math.BigInteger;


public class CryptocurrencyBinanceFilterReference {


    private BinanceFilterType filterType;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private BigDecimal tickSize;

    private BigDecimal multiplierUp;

    private BigDecimal multiplierDown;

    private BigDecimal avgPriceMins;

    private BigDecimal minQty;

    private BigDecimal maxQty;

    private BigDecimal stepSize;

    private BigDecimal minNotional;

    private boolean applyToMarket;

    private BigInteger limit;

    private BigInteger maxNumOrders;

    private BigInteger maxNumAlgoOrders;


    public CryptocurrencyBinanceFilterReference() {


    }


    public CryptocurrencyBinanceFilterReference(BinanceFilterType filterType, BigDecimal minPrice, BigDecimal maxPrice, BigDecimal tickSize, BigDecimal multiplierUp, BigDecimal multiplierDown, BigDecimal avgPriceMins, BigDecimal minQty, BigDecimal maxQty, BigDecimal stepSize, BigDecimal minNotional, boolean applyToMarket, BigInteger limit, BigInteger maxNumOrders, BigInteger maxNumAlgoOrders) {

        this.filterType = filterType;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.tickSize = tickSize;
        this.multiplierUp = multiplierUp;
        this.multiplierDown = multiplierDown;
        this.avgPriceMins = avgPriceMins;
        this.minQty = minQty;
        this.maxQty = maxQty;
        this.stepSize = stepSize;
        this.minNotional = minNotional;
        this.applyToMarket = applyToMarket;
        this.limit = limit;
        this.maxNumOrders = maxNumOrders;
        this.maxNumAlgoOrders = maxNumAlgoOrders;

    }


    public BinanceFilterType getFilterType() {

        return filterType;

    }


    public void setFilterType(BinanceFilterType filterType) {

        this.filterType = filterType;

    }


    public BigDecimal getMinPrice() {

        return minPrice;

    }


    public void setMinPrice(BigDecimal minPrice) {

        this.minPrice = minPrice;

    }


    public BigDecimal getMaxPrice() {

        return maxPrice;

    }


    public void setMaxPrice(BigDecimal maxPrice) {

        this.maxPrice = maxPrice;

    }


    public BigDecimal getTickSize() {

        return tickSize;

    }


    public void setTickSize(BigDecimal tickSize) {

        this.tickSize = tickSize;

    }


    public BigDecimal getMultiplierUp() {

        return multiplierUp;

    }


    public void setMultiplierUp(BigDecimal multiplierUp) {

        this.multiplierUp = multiplierUp;

    }


    public BigDecimal getMultiplierDown() {

        return multiplierDown;

    }


    public void setMultiplierDown(BigDecimal multiplierDown) {

        this.multiplierDown = multiplierDown;

    }


    public BigDecimal getAvgPriceMins() {

        return avgPriceMins;

    }


    public void setAvgPriceMins(BigDecimal avgPriceMins) {

        this.avgPriceMins = avgPriceMins;

    }


    public BigDecimal getMinQty() {

        return minQty;

    }


    public void setMinQty(BigDecimal minQty) {

        this.minQty = minQty;

    }


    public BigDecimal getMaxQty() {

        return maxQty;

    }


    public void setMaxQty(BigDecimal maxQty) {

        this.maxQty = maxQty;

    }


    public BigDecimal getStepSize() {

        return stepSize;

    }


    public void setStepSize(BigDecimal stepSize) {

        this.stepSize = stepSize;

    }


    public BigDecimal getMinNotional() {

        return minNotional;

    }


    public void setMinNotional(BigDecimal minNotional) {

        this.minNotional = minNotional;

    }


    public boolean isApplyToMarket() {

        return applyToMarket;

    }


    public void setApplyToMarket(boolean applyToMarket) {

        this.applyToMarket = applyToMarket;

    }


    public BigInteger getLimit() {

        return limit;

    }


    public void setLimit(BigInteger limit) {

        this.limit = limit;

    }


    public BigInteger getMaxNumOrders() {

        return maxNumOrders;

    }


    public void setMaxNumOrders(BigInteger maxNumOrders) {

        this.maxNumOrders = maxNumOrders;

    }


    public BigInteger getMaxNumAlgoOrders() {

        return maxNumAlgoOrders;

    }


    public void setMaxNumAlgoOrders(BigInteger maxNumAlgoOrders) {

        this.maxNumAlgoOrders = maxNumAlgoOrders;

    }


}
