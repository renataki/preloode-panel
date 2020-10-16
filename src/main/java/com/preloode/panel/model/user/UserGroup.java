package com.preloode.panel.model.user;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;


public class UserGroup extends Base {


    private CompanyListReference company;

    private UserGroupTransactionRangeReference deposit;

    private String description;

    @Indexed(unique = true)
    private String name;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private Status status;

    private UserGroupTransactionRangeReference transaction;

    @Indexed
    private UserType type;

    private UserGroupTransactionRangeReference withdrawal;


    public UserGroup() {


    }


    public UserGroup(String id, TimestampReference created, TimestampReference modified, CompanyListReference company, UserGroupTransactionRangeReference deposit, String description, String name, BigInteger sequence, Status status, UserGroupTransactionRangeReference transaction, UserType type, UserGroupTransactionRangeReference withdrawal) {

        super(id, created, modified);
        this.company = company;
        this.deposit = deposit;
        this.description = description;
        this.name = name;
        this.sequence = sequence;
        this.status = status;
        this.transaction = transaction;
        this.type = type;
        this.withdrawal = withdrawal;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

    }


    public UserGroupTransactionRangeReference getDeposit() {

        return deposit;

    }


    public void setDeposit(UserGroupTransactionRangeReference deposit) {

        this.deposit = deposit;

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


    public UserGroupTransactionRangeReference getTransaction() {

        return transaction;

    }


    public void setTransaction(UserGroupTransactionRangeReference transaction) {

        this.transaction = transaction;

    }


    public UserType getType() {

        return type;

    }


    public void setType(UserType type) {

        this.type = type;

    }


    public UserGroupTransactionRangeReference getWithdrawal() {

        return withdrawal;

    }


    public void setWithdrawal(UserGroupTransactionRangeReference withdrawal) {

        this.withdrawal = withdrawal;

    }


}
