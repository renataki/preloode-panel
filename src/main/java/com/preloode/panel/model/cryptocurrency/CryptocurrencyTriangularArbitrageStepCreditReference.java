package com.preloode.panel.model.cryptocurrency;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class CryptocurrencyTriangularArbitrageStepCreditReference {


    @Indexed
    private BigDecimal end;

    @Indexed
    private BigDecimal start;


    public CryptocurrencyTriangularArbitrageStepCreditReference() {


    }


    public CryptocurrencyTriangularArbitrageStepCreditReference(BigDecimal end, BigDecimal start) {

        this.end = end;
        this.start = start;

    }


    public BigDecimal getEnd() {

        return end;

    }


    public void setEnd(BigDecimal end) {

        this.end = end;

    }


    public BigDecimal getStart() {

        return start;

    }


    public void setStart(BigDecimal start) {

        this.start = start;

    }


}
