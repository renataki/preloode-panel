package com.preloode.panel.model.user;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;


public class UserRoleLogData extends Base {


    private CompanyListReference company;

    private String description;

    @Indexed
    private String name;

    private UserPrivilegeReference privilege;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private Status status;

    @Indexed
    private String userRoleId;


    public UserRoleLogData() {


    }


    public UserRoleLogData(String id, TimestampReference created, TimestampReference modified, CompanyListReference company, String description, String name, UserPrivilegeReference privilege, BigInteger sequence, Status status, String userRoleId) {

        super(id, created, modified);
        this.company = company;
        this.description = description;
        this.name = name;
        this.privilege = privilege;
        this.sequence = sequence;
        this.status = status;
        this.userRoleId = userRoleId;

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


    public UserPrivilegeReference getPrivilege() {

        return privilege;

    }


    public void setPrivilege(UserPrivilegeReference privilege) {

        this.privilege = privilege;

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


    public String getUserRoleId() {

        return userRoleId;

    }


    public void setUserRoleId(String userRoleId) {

        this.userRoleId = userRoleId;

    }


}
