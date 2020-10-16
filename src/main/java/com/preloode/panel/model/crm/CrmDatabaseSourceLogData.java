package com.preloode.panel.model.crm;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;


public class CrmDatabaseSourceLogData extends Base {


    private CompanyListReference company;

    @Indexed
    private String crmDatabaseSourceId;

    private String description;

    @Indexed
    private String name;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private Status status;


    public CrmDatabaseSourceLogData() {



    }


    public CrmDatabaseSourceLogData(String id, TimestampReference created, TimestampReference modified, CompanyListReference company, String crmDatabaseSourceId, String description, String name, BigInteger sequence, Status status) {

        super(id, created, modified);
        this.company = company;
        this.crmDatabaseSourceId = crmDatabaseSourceId;
        this.description = description;
        this.name = name;
        this.sequence = sequence;
        this.status = status;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

    }


    public String getCrmDatabaseSourceId() {

        return crmDatabaseSourceId;

    }


    public void setCrmDatabaseSourceId(String crmDatabaseSourceId) {

        this.crmDatabaseSourceId = crmDatabaseSourceId;

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


}
