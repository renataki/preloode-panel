package com.preloode.panel.model.thirdParty;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;


public class ThirdPartyProviderLogData extends Base {


    private CompanyListReference company;

    private String description;

    @Indexed(unique = true)
    private String name;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private Status status;

    @Indexed
    private String thirdPartyProviderId;


    public ThirdPartyProviderLogData() {


    }


    public ThirdPartyProviderLogData(String id, TimestampReference created, TimestampReference modified, CompanyListReference company, String description, String name, BigInteger sequence, Status status, String thirdPartyProviderId) {

        super(id, created, modified);
        this.company = company;
        this.description = description;
        this.name = name;
        this.sequence = sequence;
        this.status = status;
        this.thirdPartyProviderId = thirdPartyProviderId;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

    }


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

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


    public String getThirdPartyProviderId() {

        return thirdPartyProviderId;

    }


    public void setThirdPartyProviderId(String thirdPartyProviderId) {

        this.thirdPartyProviderId = thirdPartyProviderId;

    }


}
