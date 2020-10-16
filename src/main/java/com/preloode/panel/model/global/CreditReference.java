package com.preloode.panel.model.global;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class CreditReference {


    @Indexed
    private BigDecimal main;

    @Indexed
    private BigDecimal promotion;


    public CreditReference() {


    }


    public CreditReference(BigDecimal main, BigDecimal promotion) {

        this.main = main;
        this.promotion = promotion;

    }


    public BigDecimal getMain() {

        return main;

    }


    public void setMain(BigDecimal main) {

        this.main = main;

    }


    public BigDecimal getPromotion() {

        return promotion;

    }


    public void setPromotion(BigDecimal promotion) {

        this.promotion = promotion;

    }


}
