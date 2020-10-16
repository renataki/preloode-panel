package com.preloode.panel.model.transaction;

import com.preloode.panel.model.global.CreditReference;
import org.springframework.data.mongodb.core.index.Indexed;


public class TransactionUserReference {


    private TransactionUserAccountReference account;

    private CreditReference credit;

    @Indexed
    private String id;

    @Indexed
    private String username;


    public TransactionUserReference() {


    }


    public TransactionUserReference(TransactionUserAccountReference account, CreditReference credit, String id, String username) {

        this.account = account;
        this.credit = credit;
        this.id = id;
        this.username = username;

    }


    public TransactionUserAccountReference getAccount() {

        return account;

    }


    public void setAccount(TransactionUserAccountReference account) {

        this.account = account;

    }


    public CreditReference getCredit() {

        return credit;

    }


    public void setCredit(CreditReference credit) {

        this.credit = credit;

    }


    public String getId() {

        return id;

    }


    public void setId(String id) {

        this.id = id;

    }


    public String getUsername() {

        return username;

    }


    public void setUsername(String username) {

        this.username = username;

    }


}
