package com.preloode.panel.model.transaction;

public class TransactionPaymentAccountFilter {


    private String methodId;

    private String userId;


    public TransactionPaymentAccountFilter() {


    }


    public TransactionPaymentAccountFilter(String methodId, String userId) {

        this.methodId = methodId;
        this.userId = userId;

    }


    public String getMethodId() {

        return methodId;

    }


    public void setMethodId(String methodId) {

        this.methodId = methodId;

    }


    public String getUserId() {

        return userId;

    }


    public void setUserId(String userId) {

        this.userId = userId;

    }


}
