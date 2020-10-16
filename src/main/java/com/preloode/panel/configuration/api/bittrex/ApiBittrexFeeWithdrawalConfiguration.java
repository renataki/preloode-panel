package com.preloode.panel.configuration.api.bittrex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


@Configuration
public class ApiBittrexFeeWithdrawalConfiguration {


    @Value("${api.bittrex.fee.withdrawal.btc}")
    private BigDecimal btc;

    @Value("${api.bittrex.fee.withdrawal.eth}")
    private BigDecimal eth;

    @Value("${api.bittrex.fee.withdrawal.eur}")
    private BigDecimal eur;


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
