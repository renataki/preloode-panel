package com.preloode.panel.model.user;

import com.preloode.panel.enumeration.user.UserType;
import org.springframework.data.mongodb.core.index.Indexed;


public class UserGroupReference {


    @Indexed
    private String id;

    @Indexed
    private String name;

    @Indexed
    private UserType type;


    public UserGroupReference() {


    }


    public UserGroupReference(String id, String name, UserType type) {

        this.id = id;
        this.name = name;
        this.type = type;

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


    public UserType getType() {

        return type;

    }


    public void setType(UserType type) {

        this.type = type;

    }


}
