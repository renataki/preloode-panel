package com.preloode.panel.model.payment;

import java.math.BigDecimal;


public class PaymentMethodFeeReference {


    private BigDecimal provider;

    private boolean providerFix;

    private BigDecimal service;

    private boolean serviceFix;


    public PaymentMethodFeeReference() {


    }


    public PaymentMethodFeeReference(BigDecimal provider, boolean providerFix, BigDecimal service, boolean serviceFix) {

        this.provider = provider;
        this.providerFix = providerFix;
        this.service = service;
        this.serviceFix = serviceFix;

    }


    public BigDecimal getProvider() {

        return provider;

    }


    public void setProvider(BigDecimal provider) {

        this.provider = provider;

    }


    public boolean isProviderFix() {

        return providerFix;

    }


    public void setProviderFix(boolean providerFix) {

        this.providerFix = providerFix;

    }


    public BigDecimal getService() {

        return service;

    }


    public void setService(BigDecimal service) {

        this.service = service;

    }


    public boolean isServiceFix() {

        return serviceFix;

    }


    public void setServiceFix(boolean serviceFix) {

        this.serviceFix = serviceFix;

    }


}
