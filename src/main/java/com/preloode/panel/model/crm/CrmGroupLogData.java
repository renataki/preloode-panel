package com.preloode.panel.model.crm;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import com.preloode.panel.model.user.UserGroupListReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;


public class CrmGroupLogData extends Base {


    private CompanyListReference company;

    @Indexed
    private String crmGroupId;

    private String description;

    @Indexed(unique = true)
    private String name;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private Status status;

    private UserGroupListReference userGroup;


    public CrmGroupLogData() {



    }


    public CrmGroupLogData(String id, TimestampReference created, TimestampReference modified, CompanyListReference company, String crmGroupId, String description, String name, BigInteger sequence, Status status, UserGroupListReference userGroup) {

        super(id, created, modified);
        this.company = company;
        this.crmGroupId = crmGroupId;
        this.description = description;
        this.name = name;
        this.sequence = sequence;
        this.status = status;
        this.userGroup = userGroup;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

    }


    public String getCrmGroupId() {

        return crmGroupId;

    }


    public void setCrmGroupId(String crmGroupId) {

        this.crmGroupId = crmGroupId;

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


    public UserGroupListReference getUserGroup() {

        return userGroup;

    }


    public void setUserGroup(UserGroupListReference userGroup) {

        this.userGroup = userGroup;

    }


}
