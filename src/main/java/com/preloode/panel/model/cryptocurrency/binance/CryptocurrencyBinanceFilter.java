package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceLoadStreamMethod;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


public class CryptocurrencyBinanceFilter {


    private List<String> asset;

    private BigDecimal lotSize;

    private BinanceLoadStreamMethod method;

    private BigDecimal minimumPercentage;

    private List<String> params;

    private BigInteger id;


    public CryptocurrencyBinanceFilter() {


    }


    public CryptocurrencyBinanceFilter(List<String> asset, BigDecimal lotSize, BinanceLoadStreamMethod method, BigDecimal minimumPercentage, List<String> params, BigInteger id) {

        this.asset = asset;
        this.lotSize = lotSize;
        this.method = method;
        this.minimumPercentage = minimumPercentage;
        this.params = params;
        this.id = id;

    }


    public List<String> getAsset() {

        return asset;

    }


    public void setAsset(List<String> asset) {

        this.asset = asset;

    }


    public BigDecimal getLotSize() {

        return lotSize;

    }


    public void setLotSize(BigDecimal lotSize) {

        this.lotSize = lotSize;

    }


    public BinanceLoadStreamMethod getMethod() {

        return method;

    }


    public void setMethod(BinanceLoadStreamMethod method) {

        this.method = method;

    }


    public BigDecimal getMinimumPercentage() {

        return minimumPercentage;

    }


    public void setMinimumPercentage(BigDecimal minimumPercentage) {

        this.minimumPercentage = minimumPercentage;

    }


    public List<String> getParams() {

        return params;

    }


    public void setParams(List<String> params) {

        this.params = params;

    }


    public BigInteger getId() {

        return id;

    }


    public void setId(BigInteger id) {

        this.id = id;

    }


}
