package com.preloode.panel.model.cryptocurrency;

import java.math.BigDecimal;


public class CryptocurrencyExchangerFeeDepositReference {


    private BigDecimal btc;

    private BigDecimal eth;

    private BigDecimal eur;


    public CryptocurrencyExchangerFeeDepositReference() {


    }


    public CryptocurrencyExchangerFeeDepositReference(BigDecimal btc, BigDecimal eth, BigDecimal eur) {

        this.btc = btc;
        this.eth = eth;
        this.eur = eur;

    }


    public BigDecimal getBtc() {

        return btc;

    }


    public void setBtc(BigDecimal btc) {

        this.btc = btc;

    }


    public BigDecimal getEth() {

        return eth;

    }


    public void setEth(BigDecimal eth) {

        this.eth = eth;

    }


    public BigDecimal getEur() {

        return eur;

    }


    public void setEur(BigDecimal eur) {

        this.eur = eur;

    }


}
