package com.preloode.panel.configuration.api.kraken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


@Configuration
public class ApiKrakenFeeConfiguration {


    @Value("${api.kraken.fee.commission}")
    private BigDecimal commission;

    @Autowired
    private ApiKrakenFeeDepositConfiguration deposit;

    @Autowired
    private ApiKrakenFeeWithdrawalConfiguration withdrawal;


    public BigDecimal getCommission() {

        return commission;

    }


    public void setCommission(BigDecimal commission) {

        this.commission = commission;

    }


    public ApiKrakenFeeDepositConfiguration getDeposit() {

        return deposit;

    }


    public void setDeposit(ApiKrakenFeeDepositConfiguration deposit) {

        this.deposit = deposit;

    }


    public ApiKrakenFeeWithdrawalConfiguration getWithdrawal() {

        return withdrawal;

    }


    public void setWithdrawal(ApiKrakenFeeWithdrawalConfiguration withdrawal) {

        this.withdrawal = withdrawal;

    }


}
