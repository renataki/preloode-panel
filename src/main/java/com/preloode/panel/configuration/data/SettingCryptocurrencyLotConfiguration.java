package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


@Configuration
public class SettingCryptocurrencyLotConfiguration {


    @Value("${setting.cryptocurrency.lot.btc}")
    private BigDecimal btc;

    @Value("${setting.cryptocurrency.lot.eur}")
    private BigDecimal eur;

    @Value("${setting.cryptocurrency.lot.usd}")
    private BigDecimal usd;


    public BigDecimal getBtc() {

        return btc;

    }


    public void setBtc(BigDecimal btc) {

        this.btc = btc;

    }


    public BigDecimal getEur() {

        return eur;

    }


    public void setEur(BigDecimal eur) {

        this.eur = eur;

    }


    public BigDecimal getUsd() {

        return usd;

    }


    public void setUsd(BigDecimal usd) {

        this.usd = usd;

    }


}
