package com.preloode.panel.model.transaction;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class TransactionAdditionalCostReference {


    @Indexed
    private BigDecimal administration;

    @Indexed
    private BigDecimal provider;

    @Indexed
    private BigDecimal service;

    @Indexed
    private BigDecimal tax;


    public TransactionAdditionalCostReference() {


    }


    public TransactionAdditionalCostReference(BigDecimal administration, BigDecimal provider, BigDecimal service, BigDecimal tax) {

        this.administration = administration;
        this.provider = provider;
        this.service = service;
        this.tax = tax;

    }


    public BigDecimal getAdministration() {

        return administration;

    }


    public void setAdministration(BigDecimal administration) {

        this.administration = administration;

    }


    public BigDecimal getProvider() {

        return provider;

    }


    public void setProvider(BigDecimal provider) {

        this.provider = provider;

    }


    public BigDecimal getService() {

        return service;

    }


    public void setService(BigDecimal service) {

        this.service = service;

    }


    public BigDecimal getTax() {

        return tax;

    }


    public void setTax(BigDecimal tax) {

        this.tax = tax;

    }


}
