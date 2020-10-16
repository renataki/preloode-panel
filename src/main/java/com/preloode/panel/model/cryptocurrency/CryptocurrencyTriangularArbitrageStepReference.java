package com.preloode.panel.model.cryptocurrency;

import com.preloode.panel.enumeration.cryptocurrency.CryptocurrencyAction;
import com.preloode.panel.enumeration.cryptocurrency.CryptocurrencyArbitrageStatus;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.math.BigInteger;


public class CryptocurrencyTriangularArbitrageStepReference {


    @Indexed
    private CryptocurrencyAction action;

    private CryptocurrencyTriangularArbitrageStepAssetReference asset;

    private CryptocurrencyTriangularArbitrageStepCreditReference credit;

    @Indexed
    private BigInteger orderId;

    @Indexed
    private BigDecimal price;

    private CryptocurrencyTriangularArbitrageStepQuantityReference quantity;

    private CryptocurrencyArbitrageStatus status;

    @Indexed
    private String symbol;

    @Indexed
    private String type;


    public CryptocurrencyTriangularArbitrageStepReference() {


    }


    public CryptocurrencyTriangularArbitrageStepReference(CryptocurrencyAction action, CryptocurrencyTriangularArbitrageStepAssetReference asset, CryptocurrencyTriangularArbitrageStepCreditReference credit, BigInteger orderId, BigDecimal price, CryptocurrencyTriangularArbitrageStepQuantityReference quantity, CryptocurrencyArbitrageStatus status, String symbol, String type) {

        this.action = action;
        this.asset = asset;
        this.credit = credit;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.symbol = symbol;
        this.type = type;

    }


    public CryptocurrencyAction getAction() {

        return action;

    }


    public void setAction(CryptocurrencyAction action) {

        this.action = action;

    }


    public CryptocurrencyTriangularArbitrageStepAssetReference getAsset() {

        return asset;

    }


    public void setAsset(CryptocurrencyTriangularArbitrageStepAssetReference asset) {

        this.asset = asset;

    }


    public CryptocurrencyTriangularArbitrageStepCreditReference getCredit() {

        return credit;

    }


    public void setCredit(CryptocurrencyTriangularArbitrageStepCreditReference credit) {

        this.credit = credit;

    }


    public BigInteger getOrderId() {

        return orderId;

    }


    public void setOrderId(BigInteger orderId) {

        this.orderId = orderId;

    }


    public BigDecimal getPrice() {

        return price;

    }


    public void setPrice(BigDecimal price) {

        this.price = price;

    }


    public CryptocurrencyTriangularArbitrageStepQuantityReference getQuantity() {

        return quantity;

    }


    public void setQuantity(CryptocurrencyTriangularArbitrageStepQuantityReference quantity) {

        this.quantity = quantity;

    }


    public CryptocurrencyArbitrageStatus getStatus() {

        return status;

    }


    public void setStatus(CryptocurrencyArbitrageStatus status) {

        this.status = status;

    }


    public String getSymbol() {

        return symbol;

    }


    public void setSymbol(String symbol) {

        this.symbol = symbol;

    }


    public String getType() {

        return type;

    }


    public void setType(String type) {

        this.type = type;

    }


}
