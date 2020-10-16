package com.preloode.panel.model.payment;

import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;


public class PaymentMethodBusinessHourRangeReference {


    @Indexed
    private Date end;

    @Indexed
    private Date start;


    public PaymentMethodBusinessHourRangeReference() {


    }


    public PaymentMethodBusinessHourRangeReference(Date end, Date start) {

        this.end = end;
        this.start = start;

    }


    public Date getEnd() {

        return end;

    }


    public void setEnd(Date end) {

        this.end = end;

    }


    public Date getStart() {

        return start;

    }


    public void setStart(Date start) {

        this.start = start;

    }


}
