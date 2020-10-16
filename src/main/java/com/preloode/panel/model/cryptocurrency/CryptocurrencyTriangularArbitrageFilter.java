package com.preloode.panel.model.cryptocurrency;

import com.preloode.panel.enumeration.cryptocurrency.Exchanger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


public class CryptocurrencyTriangularArbitrageFilter {


    private String asset;

    private Exchanger exchanger;

    private BigDecimal lotSize;

    private BigDecimal minimumPercentage;

    private List<String> streamList;

    private BigInteger websocketId;


    public CryptocurrencyTriangularArbitrageFilter() {


    }


    public CryptocurrencyTriangularArbitrageFilter(String asset, Exchanger exchanger, BigDecimal lotSize, BigDecimal minimumPercentage, List<String> streamList, BigInteger websocketId) {

        this.asset = asset;
        this.exchanger = exchanger;
        this.lotSize = lotSize;
        this.minimumPercentage = minimumPercentage;
        this.streamList = streamList;
        this.websocketId = websocketId;

    }


    public String getAsset() {

        return asset;

    }


    public void setAsset(String asset) {

        this.asset = asset;

    }


    public Exchanger getExchanger() {

        return exchanger;

    }


    public void setExchanger(Exchanger exchanger) {

        this.exchanger = exchanger;

    }


    public BigDecimal getLotSize() {

        return lotSize;

    }


    public void setLotSize(BigDecimal lotSize) {

        this.lotSize = lotSize;

    }


    public BigDecimal getMinimumPercentage() {

        return minimumPercentage;

    }


    public void setMinimumPercentage(BigDecimal minimumPercentage) {

        this.minimumPercentage = minimumPercentage;

    }


    public List<String> getStreamList() {

        return streamList;

    }


    public void setStreamList(List<String> streamList) {

        this.streamList = streamList;

    }


    public BigInteger getWebsocketId() {

        return websocketId;

    }


    public void setWebsocketId(BigInteger websocketId) {

        this.websocketId = websocketId;

    }


}
