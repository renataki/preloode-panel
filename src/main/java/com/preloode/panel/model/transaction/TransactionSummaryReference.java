package com.preloode.panel.model.transaction;

public class TransactionSummaryReference {


    private TransactionSummaryDepositReference deposit;

    private TransactionSummaryWithdrawalReference withdrawal;


    public TransactionSummaryReference() {


    }


    public TransactionSummaryReference(TransactionSummaryDepositReference deposit, TransactionSummaryWithdrawalReference withdrawal) {

        this.deposit = deposit;
        this.withdrawal = withdrawal;

    }


    public TransactionSummaryDepositReference getDeposit() {

        return deposit;

    }


    public void setDeposit(TransactionSummaryDepositReference deposit) {

        this.deposit = deposit;

    }


    public TransactionSummaryWithdrawalReference getWithdrawal() {

        return withdrawal;

    }


    public void setWithdrawal(TransactionSummaryWithdrawalReference withdrawal) {

        this.withdrawal = withdrawal;

    }


}
