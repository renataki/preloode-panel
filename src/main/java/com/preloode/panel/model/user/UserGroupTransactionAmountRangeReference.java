package com.preloode.panel.model.user;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class UserGroupTransactionAmountRangeReference {


    @Indexed
    private BigDecimal end;

    @Indexed
    private BigDecimal start;


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
