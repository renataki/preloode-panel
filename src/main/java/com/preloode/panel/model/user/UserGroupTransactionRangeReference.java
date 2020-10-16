package com.preloode.panel.model.user;

public class UserGroupTransactionRangeReference {


    private UserGroupTransactionAmountRangeReference amount;

    private UserGroupTransactionCountRangeReference count;


    public UserGroupTransactionAmountRangeReference getAmount() {

        return amount;

    }


    public void setAmount(UserGroupTransactionAmountRangeReference amount) {

        this.amount = amount;

    }


    public UserGroupTransactionCountRangeReference getCount() {

        return count;

    }


    public void setCount(UserGroupTransactionCountRangeReference count) {

        this.count = count;

    }


}
