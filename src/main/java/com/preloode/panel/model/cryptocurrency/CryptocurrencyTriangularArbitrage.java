package com.preloode.panel.model.cryptocurrency;

import com.preloode.panel.enumeration.cryptocurrency.CryptocurrencyArbitrageStatus;
import com.preloode.panel.enumeration.cryptocurrency.Exchanger;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class CryptocurrencyTriangularArbitrage extends Base {


    private CryptocurrencyTriangularArbitrageStepReference end;

    @Indexed
    private Exchanger exchanger;

    private CryptocurrencyTriangularArbitrageStepReference middle;

    @Indexed
    private BigDecimal percentage;

    private CryptocurrencyTriangularArbitrageStepReference start;

    private CryptocurrencyArbitrageStatus status;


    public CryptocurrencyTriangularArbitrage() {


    }


    public CryptocurrencyTriangularArbitrage(String id, TimestampReference created, TimestampReference modified, CryptocurrencyTriangularArbitrageStepReference end, Exchanger exchanger, CryptocurrencyTriangularArbitrageStepReference middle, BigDecimal percentage, CryptocurrencyTriangularArbitrageStepReference start, CryptocurrencyArbitrageStatus status) {

        super(id, created, modified);
        this.end = end;
        this.exchanger = exchanger;
        this.middle = middle;
        this.percentage = percentage;
        this.start = start;
        this.status = status;

    }


    public CryptocurrencyTriangularArbitrageStepReference getEnd() {

        return end;

    }


    public void setEnd(CryptocurrencyTriangularArbitrageStepReference end) {

        this.end = end;

    }


    public Exchanger getExchanger() {

        return exchanger;

    }


    public void setExchanger(Exchanger exchanger) {

        this.exchanger = exchanger;

    }


    public CryptocurrencyTriangularArbitrageStepReference getMiddle() {

        return middle;

    }


    public void setMiddle(CryptocurrencyTriangularArbitrageStepReference middle) {

        this.middle = middle;

    }


    public BigDecimal getPercentage() {

        return percentage;

    }


    public void setPercentage(BigDecimal percentage) {

        this.percentage = percentage;

    }


    public CryptocurrencyTriangularArbitrageStepReference getStart() {

        return start;

    }


    public void setStart(CryptocurrencyTriangularArbitrageStepReference start) {

        this.start = start;

    }


    public CryptocurrencyArbitrageStatus getStatus() {

        return status;

    }


    public void setStatus(CryptocurrencyArbitrageStatus status) {

        this.status = status;

    }


}
