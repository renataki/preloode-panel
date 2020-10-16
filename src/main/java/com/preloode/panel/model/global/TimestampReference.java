package com.preloode.panel.model.global;

import com.preloode.panel.model.user.UserReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;


public class TimestampReference {


    @Indexed(unique = true)
    private Date timestamp;

    private UserReference user;


    public TimestampReference() {


    }


    public TimestampReference(Date timestamp, UserReference user) {

        this.timestamp = timestamp;
        this.user = user;

    }


    public Date getTimestamp() {

        return timestamp;

    }


    public void setTimestamp(Date timestamp) {

        this.timestamp = timestamp;

    }


    public UserReference getUser() {

        return user;

    }


    public void setUser(UserReference user) {

        this.user = user;

    }


}
