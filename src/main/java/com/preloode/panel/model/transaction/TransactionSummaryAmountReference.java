package com.preloode.panel.model.transaction;

import java.math.BigDecimal;


public class TransactionSummaryAmountReference {


    private BigDecimal newMemberLastMonth;

    private BigDecimal newMemberThisMonth;

    private BigDecimal newMemberToday;

    private BigDecimal newMemberYesterday;

    private BigDecimal lastMonth;

    private BigDecimal thisMonth;

    private BigDecimal today;

    private BigDecimal yesterday;


    public TransactionSummaryAmountReference() {


    }


    public TransactionSummaryAmountReference(BigDecimal newMemberLastMonth, BigDecimal newMemberThisMonth, BigDecimal newMemberToday, BigDecimal newMemberYesterday, BigDecimal lastMonth, BigDecimal thisMonth, BigDecimal today, BigDecimal yesterday) {

        this.newMemberLastMonth = newMemberLastMonth;
        this.newMemberThisMonth = newMemberThisMonth;
        this.newMemberToday = newMemberToday;
        this.newMemberYesterday = newMemberYesterday;
        this.lastMonth = lastMonth;
        this.thisMonth = thisMonth;
        this.today = today;
        this.yesterday = yesterday;

    }


    public BigDecimal getNewMemberLastMonth() {

        return newMemberLastMonth;

    }


    public void setNewMemberLastMonth(BigDecimal newMemberLastMonth) {

        this.newMemberLastMonth = newMemberLastMonth;

    }


    public BigDecimal getNewMemberThisMonth() {

        return newMemberThisMonth;

    }


    public void setNewMemberThisMonth(BigDecimal newMemberThisMonth) {

        this.newMemberThisMonth = newMemberThisMonth;

    }


    public BigDecimal getNewMemberToday() {

        return newMemberToday;

    }


    public void setNewMemberToday(BigDecimal newMemberToday) {

        this.newMemberToday = newMemberToday;

    }


    public BigDecimal getNewMemberYesterday() {

        return newMemberYesterday;

    }


    public void setNewMemberYesterday(BigDecimal newMemberYesterday) {

        this.newMemberYesterday = newMemberYesterday;

    }


    public BigDecimal getLastMonth() {

        return lastMonth;

    }


    public void setLastMonth(BigDecimal lastMonth) {

        this.lastMonth = lastMonth;

    }


    public BigDecimal getThisMonth() {

        return thisMonth;

    }


    public void setThisMonth(BigDecimal thisMonth) {

        this.thisMonth = thisMonth;

    }


    public BigDecimal getToday() {

        return today;

    }


    public void setToday(BigDecimal today) {

        this.today = today;

    }


    public BigDecimal getYesterday() {

        return yesterday;

    }


    public void setYesterday(BigDecimal yesterday) {

        this.yesterday = yesterday;

    }


}
