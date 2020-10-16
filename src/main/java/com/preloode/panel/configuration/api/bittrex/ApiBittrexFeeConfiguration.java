package com.preloode.panel.configuration.api.bittrex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


@Configuration
public class ApiBittrexFeeConfiguration {


    @Value("${api.bittrex.fee.commission}")
    private BigDecimal commission;

    @Autowired
    private ApiBittrexFeeDepositConfiguration deposit;

    @Autowired
    private ApiBittrexFeeWithdrawalConfiguration withdrawal;


    public BigDecimal getCommission() {

        return commission;

    }


    public void setCommission(BigDecimal commission) {

        this.commission = commission;

    }


    public ApiBittrexFeeDepositConfiguration getDeposit() {

        return deposit;

    }


    public void setDeposit(ApiBittrexFeeDepositConfiguration deposit) {

        this.deposit = deposit;

    }


    public ApiBittrexFeeWithdrawalConfiguration getWithdrawal() {

        return withdrawal;

    }


    public void setWithdrawal(ApiBittrexFeeWithdrawalConfiguration withdrawal) {

        this.withdrawal = withdrawal;

    }


}
