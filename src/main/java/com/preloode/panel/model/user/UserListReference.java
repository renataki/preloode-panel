package com.preloode.panel.model.user;

import java.util.List;


public class UserListReference {


    private List<String> id;

    private List<String> username;


    public UserListReference() {


    }


    public UserListReference(List<String> id, List<String> username) {

        this.id = id;
        this.username = username;

    }


    public List<String> getId() {

        return id;

    }


    public void setId(List<String> id) {

        this.id = id;

    }


    public List<String> getUsername() {

        return username;

    }


    public void setUsername(List<String> username) {

        this.username = username;

    }


}
