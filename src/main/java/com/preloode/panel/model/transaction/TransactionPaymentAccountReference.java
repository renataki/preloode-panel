package com.preloode.panel.model.transaction;

import com.preloode.panel.model.payment.PaymentAccountReference;
import com.preloode.panel.model.payment.PaymentMethodReference;


public class TransactionPaymentAccountReference {


    private PaymentAccountReference account;

    private PaymentMethodReference method;


    public TransactionPaymentAccountReference() {


    }


    public TransactionPaymentAccountReference(PaymentAccountReference account, PaymentMethodReference method) {

        this.account = account;
        this.method = method;

    }


    public PaymentAccountReference getAccount() {

        return account;

    }


    public void setAccount(PaymentAccountReference account) {

        this.account = account;

    }


    public PaymentMethodReference getMethod() {

        return method;

    }


    public void setMethod(PaymentMethodReference method) {

        this.method = method;

    }


}
