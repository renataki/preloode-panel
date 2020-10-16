package com.preloode.panel.model.payment;

import com.preloode.panel.enumeration.payment.PaymentType;
import org.springframework.data.mongodb.core.index.Indexed;


public class PaymentMethodReference {


    @Indexed
    private String id;

    @Indexed
    private String name;

    @Indexed
    private PaymentType type;


    public PaymentMethodReference() {


    }


    public PaymentMethodReference(String id, String name, PaymentType type) {

        this.id = id;
        this.name = name;
        this.type = type;

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


    public PaymentType getType() {

        return type;

    }


    public void setType(PaymentType type) {

        this.type = type;

    }


}
