package com.preloode.panel.model.user;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;


public class UserGroupTransactionCountRangeReference {


    @Indexed
    private BigInteger end;

    @Indexed
    private BigInteger start;


    public BigInteger getEnd() {

        return end;

    }


    public void setEnd(BigInteger end) {

        this.end = end;

    }


    public BigInteger getStart() {

        return start;

    }


    public void setStart(BigInteger start) {

        this.start = start;

    }


}
