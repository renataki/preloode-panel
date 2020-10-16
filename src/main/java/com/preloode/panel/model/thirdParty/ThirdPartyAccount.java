package com.preloode.panel.model.thirdParty;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.math.BigInteger;


public class ThirdPartyAccount extends Base {


    private CompanyListReference company;

    @Indexed
    private BigDecimal credit;

    private String description;

    private String password;

    private String pin;

    private ThirdPartyProviderReference provider;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private Status status;

    @Indexed
    private String username;


    public ThirdPartyAccount() {


    }


    public ThirdPartyAccount(String id, TimestampReference created, TimestampReference modified, CompanyListReference company, BigDecimal credit, String description, String password, String pin, ThirdPartyProviderReference provider, BigInteger sequence, Status status, String username) {

        super(id, created, modified);
        this.company = company;
        this.credit = credit;
        this.description = description;
        this.password = password;
        this.pin = pin;
        this.provider = provider;
        this.sequence = sequence;
        this.status = status;
        this.username = username;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

    }


    public BigDecimal getCredit() {

        return credit;

    }


    public void setCredit(BigDecimal credit) {

        this.credit = credit;

    }


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public String getPassword() {

        return password;

    }


    public void setPassword(String password) {

        this.password = password;

    }


    public String getPin() {

        return pin;

    }


    public void setPin(String pin) {

        this.pin = pin;

    }


    public ThirdPartyProviderReference getProvider() {

        return provider;

    }


    public void setProvider(ThirdPartyProviderReference provider) {

        this.provider = provider;

    }


    public BigInteger getSequence() {

        return sequence;

    }


    public void setSequence(BigInteger sequence) {

        this.sequence = sequence;

    }


    public Status getStatus() {

        return status;

    }


    public void setStatus(Status status) {

        this.status = status;

    }


    public String getUsername() {

        return username;

    }


    public void setUsername(String username) {

        this.username = username;

    }


}
