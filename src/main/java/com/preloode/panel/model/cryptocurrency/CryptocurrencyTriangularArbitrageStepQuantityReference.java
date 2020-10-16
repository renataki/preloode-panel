package com.preloode.panel.model.cryptocurrency;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class CryptocurrencyTriangularArbitrageStepQuantityReference {


    @Indexed
    private BigDecimal minimum;

    @Indexed
    private BigDecimal order;


    public CryptocurrencyTriangularArbitrageStepQuantityReference() {


    }


    public CryptocurrencyTriangularArbitrageStepQuantityReference(BigDecimal minimum, BigDecimal order) {

        this.minimum = minimum;
        this.order = order;

    }


    public BigDecimal getMinimum() {

        return minimum;

    }


    public void setMinimum(BigDecimal minimum) {

        this.minimum = minimum;

    }


    public BigDecimal getOrder() {

        return order;

    }


    public void setOrder(BigDecimal order) {

        this.order = order;

    }


}
