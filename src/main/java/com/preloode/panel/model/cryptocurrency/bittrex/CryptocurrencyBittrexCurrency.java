package com.preloode.panel.model.cryptocurrency.bittrex;

import com.preloode.panel.enumeration.cryptocurrency.bittrex.BittrexCurrencyStatus;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


public class CryptocurrencyBittrexCurrency extends Base {


    private String symbol;

    private String name;

    private String coinType;

    private BittrexCurrencyStatus status;

    private BigInteger minConfirmations;

    private String notice;

    private BigDecimal txFee;

    private String logoUrl;

    private List<String> prohibitedIn;


    public CryptocurrencyBittrexCurrency() {


    }


    public CryptocurrencyBittrexCurrency(String id, TimestampReference created, TimestampReference modified, String symbol, String name, String coinType, BittrexCurrencyStatus status, BigInteger minConfirmations, String notice, BigDecimal txFee, String logoUrl, List<String> prohibitedIn) {

        super(id, created, modified);
        this.symbol = symbol;
        this.name = name;
        this.coinType = coinType;
        this.status = status;
        this.minConfirmations = minConfirmations;
        this.notice = notice;
        this.txFee = txFee;
        this.logoUrl = logoUrl;
        this.prohibitedIn = prohibitedIn;

    }


    public String getSymbol() {

        return symbol;

    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public String getCoinType() {

        return coinType;

    }


    public void setCoinType(String coinType) {

        this.coinType = coinType;

    }


    public BittrexCurrencyStatus getStatus() {

        return status;

    }


    public void setStatus(BittrexCurrencyStatus status) {

        this.status = status;

    }


    public BigInteger getMinConfirmations() {

        return minConfirmations;

    }


    public void setMinConfirmations(BigInteger minConfirmations) {

        this.minConfirmations = minConfirmations;

    }


    public String getNotice() {

        return notice;

    }


    public void setNotice(String notice) {

        this.notice = notice;

    }


    public BigDecimal getTxFee() {

        return txFee;

    }


    public void setTxFee(BigDecimal txFee) {

        this.txFee = txFee;

    }


    public String getLogoUrl() {

        return logoUrl;

    }


    public void setLogoUrl(String logoUrl) {

        this.logoUrl = logoUrl;

    }


    public List<String> getProhibitedIn() {

        return prohibitedIn;

    }


    public void setProhibitedIn(List<String> prohibitedIn) {

        this.prohibitedIn = prohibitedIn;

    }


}
