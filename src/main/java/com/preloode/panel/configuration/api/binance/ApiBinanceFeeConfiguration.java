package com.preloode.panel.configuration.api.binance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


@Configuration
public class ApiBinanceFeeConfiguration {


    @Value("${api.binance.fee.commission}")
    private BigDecimal commission;

    @Autowired
    private ApiBinanceFeeDepositConfiguration deposit;

    @Autowired
    private ApiBinanceFeeWithdrawalConfiguration withdrawal;


    public BigDecimal getCommission() {

        return commission;

    }


    public void setCommission(BigDecimal commission) {

        this.commission = commission;

    }


    public ApiBinanceFeeDepositConfiguration getDeposit() {

        return deposit;

    }


    public void setDeposit(ApiBinanceFeeDepositConfiguration deposit) {

        this.deposit = deposit;

    }


    public ApiBinanceFeeWithdrawalConfiguration getWithdrawal() {

        return withdrawal;

    }


    public void setWithdrawal(ApiBinanceFeeWithdrawalConfiguration withdrawal) {

        this.withdrawal = withdrawal;

    }


}
