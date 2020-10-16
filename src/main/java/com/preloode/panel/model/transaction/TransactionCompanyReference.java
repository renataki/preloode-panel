package com.preloode.panel.model.transaction;

import org.springframework.data.mongodb.core.index.Indexed;


public class TransactionCompanyReference {


    private TransactionCompanyBranchReference branch;

    @Indexed
    private String id;

    @Indexed
    private String name;


    public TransactionCompanyReference() {


    }


    public TransactionCompanyReference(TransactionCompanyBranchReference branch, String id, String name) {

        this.branch = branch;
        this.id = id;
        this.name = name;

    }


    public TransactionCompanyBranchReference getBranch() {

        return branch;

    }


    public void setBranch(TransactionCompanyBranchReference branch) {

        this.branch = branch;

    }


    public String getId() {

        return id;

    }


    public void setId(String id) {

        this.id = id;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


}
