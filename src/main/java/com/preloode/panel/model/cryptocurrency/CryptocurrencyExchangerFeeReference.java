package com.preloode.panel.model.cryptocurrency;

import java.math.BigDecimal;


public class CryptocurrencyExchangerFeeReference {


    private BigDecimal commission;

    private CryptocurrencyExchangerFeeDepositReference deposit;

    private CryptocurrencyExchangerFeeWithdrawalReference withdrawal;


    public CryptocurrencyExchangerFeeReference() {


    }


    public CryptocurrencyExchangerFeeReference(BigDecimal commission, CryptocurrencyExchangerFeeDepositReference deposit, CryptocurrencyExchangerFeeWithdrawalReference withdrawal) {

        this.commission = commission;
        this.deposit = deposit;
        this.withdrawal = withdrawal;

    }


    public BigDecimal getCommission() {

        return commission;

    }


    public void setCommission(BigDecimal commission) {

        this.commission = commission;

    }


    public CryptocurrencyExchangerFeeDepositReference getDeposit() {

        return deposit;

    }


    public void setDeposit(CryptocurrencyExchangerFeeDepositReference deposit) {

        this.deposit = deposit;

    }


    public CryptocurrencyExchangerFeeWithdrawalReference getWithdrawal() {

        return withdrawal;

    }


    public void setWithdrawal(CryptocurrencyExchangerFeeWithdrawalReference withdrawal) {

        this.withdrawal = withdrawal;

    }


}
