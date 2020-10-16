package com.preloode.panel.model.cryptocurrency.bittrex;

import java.math.BigDecimal;
import java.util.Date;


public class CryptocurrencyBittrexBalance {


    private String currencySymbol;

    private BigDecimal total;

    private BigDecimal available;

    private Date updatedAt;


    public CryptocurrencyBittrexBalance() {


    }


    public CryptocurrencyBittrexBalance(String currencySymbol, BigDecimal total, BigDecimal available, Date updatedAt) {

        this.currencySymbol = currencySymbol;
        this.total = total;
        this.available = available;
        this.updatedAt = updatedAt;

    }


    public String getCurrencySymbol() {

        return currencySymbol;

    }


    public void setCurrencySymbol(String currencySymbol) {

        this.currencySymbol = currencySymbol;

    }


    public BigDecimal getTotal() {

        return total;

    }


    public void setTotal(BigDecimal total) {

        this.total = total;

    }


    public BigDecimal getAvailable() {

        return available;

    }


    public void setAvailable(BigDecimal available) {

        this.available = available;

    }


    public Date getUpdatedAt() {

        return updatedAt;

    }


    public void setUpdatedAt(Date updatedAt) {

        this.updatedAt = updatedAt;

    }


}
