package com.preloode.panel.configuration.api.bitfinex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


@Configuration
public class ApiBitfinexFeeConfiguration {


    @Value("${api.bitfinex.fee.commission}")
    private BigDecimal commission;

    @Autowired
    private ApiBitfinexFeeDepositConfiguration deposit;

    @Autowired
    private ApiBitfinexFeeWithdrawalConfiguration withdrawal;


    public BigDecimal getCommission() {

        return commission;

    }


    public void setCommission(BigDecimal commission) {

        this.commission = commission;

    }


    public ApiBitfinexFeeDepositConfiguration getDeposit() {

        return deposit;

    }


    public void setDeposit(ApiBitfinexFeeDepositConfiguration deposit) {

        this.deposit = deposit;

    }


    public ApiBitfinexFeeWithdrawalConfiguration getWithdrawal() {

        return withdrawal;

    }


    public void setWithdrawal(ApiBitfinexFeeWithdrawalConfiguration withdrawal) {

        this.withdrawal = withdrawal;

    }


}
