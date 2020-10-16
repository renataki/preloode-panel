package com.preloode.panel.model.cryptocurrency;

import com.preloode.panel.enumeration.cryptocurrency.Exchanger;

import java.util.List;


public class CryptocurrencyFilter {


    private String asset;

    private Exchanger exchanger;

    private List<String> tradeInformationList;


    public CryptocurrencyFilter() {


    }


    public CryptocurrencyFilter(String asset, Exchanger exchanger, List<String> tradeInformationList) {

        this.asset = asset;
        this.exchanger = exchanger;
        this.tradeInformationList = tradeInformationList;

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


    public List<String> getTradeInformationList() {

        return tradeInformationList;

    }


    public void setTradeInformationList(List<String> tradeInformationList) {

        this.tradeInformationList = tradeInformationList;

    }


}
