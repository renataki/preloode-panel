package com.preloode.panel.model.transaction;


public class TransactionSourceReference {


    private TransactionPaymentAccountReference payment;

    private TransactionUserReference user;


    public TransactionSourceReference() {


    }


    public TransactionSourceReference(TransactionPaymentAccountReference payment, TransactionUserReference user) {

        this.payment = payment;
        this.user = user;

    }


    public TransactionPaymentAccountReference getPayment() {

        return payment;

    }


    public void setPayment(TransactionPaymentAccountReference payment) {

        this.payment = payment;

    }


    public TransactionUserReference getUser() {

        return user;

    }


    public void setUser(TransactionUserReference user) {

        this.user = user;

    }


}
