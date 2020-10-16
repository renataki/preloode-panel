package com.preloode.panel.model.setting;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.math.BigInteger;


public class SettingTransactionReference {


    @Indexed
    private BigInteger averageTime;

    @Indexed
    private BigDecimal maximum;

    @Indexed
    private BigDecimal minimum;


    public SettingTransactionReference() {


    }


    public SettingTransactionReference(BigInteger averageTime, BigDecimal maximum, BigDecimal minimum) {

        this.averageTime = averageTime;
        this.maximum = maximum;
        this.minimum = minimum;

    }


    public BigInteger getAverageTime() {

        return averageTime;

    }


    public void setAverageTime(BigInteger averageTime) {

        this.averageTime = averageTime;

    }


    public BigDecimal getMaximum() {

        return maximum;

    }


    public void setMaximum(BigDecimal maximum) {

        this.maximum = maximum;

    }


    public BigDecimal getMinimum() {

        return minimum;

    }


    public void setMinimum(BigDecimal minimum) {

        this.minimum = minimum;

    }


}
