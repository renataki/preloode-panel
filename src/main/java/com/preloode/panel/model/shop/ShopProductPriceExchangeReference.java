package com.preloode.panel.model.shop;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class ShopProductPriceExchangeReference {


    @Indexed
    private BigDecimal buy;

    @Indexed
    private BigDecimal sell;


    public ShopProductPriceExchangeReference() {



    }


    public ShopProductPriceExchangeReference(BigDecimal buy, BigDecimal sell) {

        this.buy = buy;
        this.sell = sell;

    }


    public BigDecimal getBuy() {

        return buy;

    }


    public void setBuy(BigDecimal buy) {

        this.buy = buy;

    }


    public BigDecimal getSell() {

        return sell;

    }


    public void setSell(BigDecimal sell) {

        this.sell = sell;

    }


}
