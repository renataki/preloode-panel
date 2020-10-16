package com.preloode.panel.model.transaction;

import com.preloode.panel.model.user.UserListReference;

import java.util.Date;
import java.util.List;


public class TransactionActionReference {


    private UserListReference user;

    private List<String> name;

    private List<Date> timestamp;


    public TransactionActionReference() {


    }


    public TransactionActionReference(UserListReference user, List<String> name, List<Date> timestamp) {

        this.user = user;
        this.name = name;
        this.timestamp = timestamp;

    }


    public UserListReference getUser() {

        return user;

    }


    public void setUser(UserListReference user) {

        this.user = user;

    }


    public List<String> getName() {

        return name;

    }


    public void setName(List<String> name) {

        this.name = name;

    }


    public List<Date> getTimestamp() {

        return timestamp;

    }


    public void setTimestamp(List<Date> timestamp) {

        this.timestamp = timestamp;

    }


}
