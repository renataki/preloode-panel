package com.preloode.panel.model.cryptocurrency.binance;

import com.preloode.panel.enumeration.cryptocurrency.binance.BinanceLoadStreamMethod;

import java.math.BigInteger;
import java.util.List;


public class CryptocurrencyBinanceStreamMessage {


    private BinanceLoadStreamMethod method;

    private List<String> params;

    private BigInteger id;


    public CryptocurrencyBinanceStreamMessage() {


    }


    public CryptocurrencyBinanceStreamMessage(BinanceLoadStreamMethod method, List<String> params, BigInteger id) {

        this.method = method;
        this.params = params;
        this.id = id;

    }


    public BinanceLoadStreamMethod getMethod() {

        return method;

    }


    public void setMethod(BinanceLoadStreamMethod method) {

        this.method = method;

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
