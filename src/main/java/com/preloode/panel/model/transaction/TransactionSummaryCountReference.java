package com.preloode.panel.model.transaction;

import java.math.BigInteger;


public class TransactionSummaryCountReference {


    private BigInteger newMemberLastMonth;

    private BigInteger newMemberThisMonth;

    private BigInteger newMemberToday;

    private BigInteger newMemberYesterday;

    private BigInteger lastMonth;

    private BigInteger thisMonth;

    private BigInteger today;

    private BigInteger yesterday;


    public TransactionSummaryCountReference() {



    }


    public TransactionSummaryCountReference(BigInteger newMemberLastMonth, BigInteger newMemberThisMonth, BigInteger newMemberToday, BigInteger newMemberYesterday, BigInteger lastMonth, BigInteger thisMonth, BigInteger today, BigInteger yesterday) {

        this.newMemberLastMonth = newMemberLastMonth;
        this.newMemberThisMonth = newMemberThisMonth;
        this.newMemberToday = newMemberToday;
        this.newMemberYesterday = newMemberYesterday;
        this.lastMonth = lastMonth;
        this.thisMonth = thisMonth;
        this.today = today;
        this.yesterday = yesterday;

    }


    public BigInteger getNewMemberLastMonth() {

        return newMemberLastMonth;

    }


    public void setNewMemberLastMonth(BigInteger newMemberLastMonth) {

        this.newMemberLastMonth = newMemberLastMonth;

    }


    public BigInteger getNewMemberThisMonth() {

        return newMemberThisMonth;

    }


    public void setNewMemberThisMonth(BigInteger newMemberThisMonth) {

        this.newMemberThisMonth = newMemberThisMonth;

    }


    public BigInteger getNewMemberToday() {

        return newMemberToday;

    }


    public void setNewMemberToday(BigInteger newMemberToday) {

        this.newMemberToday = newMemberToday;

    }


    public BigInteger getNewMemberYesterday() {

        return newMemberYesterday;

    }


    public void setNewMemberYesterday(BigInteger newMemberYesterday) {

        this.newMemberYesterday = newMemberYesterday;

    }


    public BigInteger getLastMonth() {

        return lastMonth;

    }


    public void setLastMonth(BigInteger lastMonth) {

        this.lastMonth = lastMonth;

    }


    public BigInteger getThisMonth() {

        return thisMonth;

    }


    public void setThisMonth(BigInteger thisMonth) {

        this.thisMonth = thisMonth;

    }


    public BigInteger getToday() {

        return today;

    }


    public void setToday(BigInteger today) {

        this.today = today;

    }


    public BigInteger getYesterday() {

        return yesterday;

    }


    public void setYesterday(BigInteger yesterday) {

        this.yesterday = yesterday;

    }


}
