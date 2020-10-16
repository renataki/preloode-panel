package com.preloode.panel.model.transaction;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class TransactionUserAccountReference {


    @Indexed
    private BigDecimal credit;

    private TransactionUserThirdPartyReference thirdParty;

    @Indexed
    private String username;


    public TransactionUserAccountReference() {


    }


    public TransactionUserAccountReference(BigDecimal credit, TransactionUserThirdPartyReference thirdParty, String username) {

        this.credit = credit;
        this.thirdParty = thirdParty;
        this.username = username;

    }


    public BigDecimal getCredit() {

        return credit;

    }


    public void setCredit(BigDecimal credit) {

        this.credit = credit;

    }


    public TransactionUserThirdPartyReference getThirdParty() {

        return thirdParty;

    }


    public void setThirdParty(TransactionUserThirdPartyReference thirdParty) {

        this.thirdParty = thirdParty;

    }


    public String getUsername() {

        return username;

    }


    public void setUsername(String username) {

        this.username = username;

    }


}
