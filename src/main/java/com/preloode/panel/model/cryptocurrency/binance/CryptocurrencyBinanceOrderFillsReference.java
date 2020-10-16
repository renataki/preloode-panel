package com.preloode.panel.model.cryptocurrency.binance;

import java.math.BigDecimal;
import java.math.BigInteger;


public class CryptocurrencyBinanceOrderFillsReference {


    private BigDecimal price;

    private BigDecimal qty;

    private BigDecimal commission;

    private String commissionAsset;

    private BigInteger tradeId;


    public CryptocurrencyBinanceOrderFillsReference() {


    }


    public CryptocurrencyBinanceOrderFillsReference(BigDecimal price, BigDecimal qty, BigDecimal commission, String commissionAsset, BigInteger tradeId) {

        this.price = price;
        this.qty = qty;
        this.commission = commission;
        this.commissionAsset = commissionAsset;
        this.tradeId = tradeId;

    }


    public BigDecimal getPrice() {

        return price;

    }


    public void setPrice(BigDecimal price) {

        this.price = price;

    }


    public BigDecimal getQty() {

        return qty;

    }


    public void setQty(BigDecimal qty) {

        this.qty = qty;

    }


    public BigDecimal getCommission() {

        return commission;

    }


    public void setCommission(BigDecimal commission) {

        this.commission = commission;

    }


    public String getCommissionAsset() {

        return commissionAsset;

    }


    public void setCommissionAsset(String commissionAsset) {

        this.commissionAsset = commissionAsset;

    }


    public BigInteger getTradeId() {

        return tradeId;

    }


    public void setTradeId(BigInteger tradeId) {

        this.tradeId = tradeId;

    }


}
