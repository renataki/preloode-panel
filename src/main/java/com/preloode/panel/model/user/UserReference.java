package com.preloode.panel.model.user;

import org.springframework.data.mongodb.core.index.Indexed;


public class UserReference {


    @Indexed
    private String id;

    @Indexed
    private String username;


    public UserReference() {


    }


    public UserReference(String id, String username) {

        this.id = id;
        this.username = username;

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
