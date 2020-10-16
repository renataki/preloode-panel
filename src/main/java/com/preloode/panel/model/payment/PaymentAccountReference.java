package com.preloode.panel.model.payment;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class PaymentAccountReference {


    @Indexed
    private BigDecimal credit;

    @Indexed
    private String id;

    @Indexed
    private String name;

    @Indexed
    private String number;


    public PaymentAccountReference() {


    }


    public PaymentAccountReference(BigDecimal credit, String id, String name, String number) {

        this.credit = credit;
        this.id = id;
        this.name = name;
        this.number = number;

    }


    public BigDecimal getCredit() {

        return credit;

    }


    public void setCredit(BigDecimal credit) {

        this.credit = credit;

    }


    public String getId() {

        return id;

    }


    public void setId(String id) {

        this.id = id;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public String getNumber() {

        return number;

    }


    public void setNumber(String number) {

        this.number = number;

    }


}
