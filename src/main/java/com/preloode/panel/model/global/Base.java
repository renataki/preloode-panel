package com.preloode.panel.model.global;

import org.springframework.data.annotation.Id;


public class Base {


    @Id
    private String id;

    private TimestampReference created;

    private TimestampReference modified;


    public Base() {


    }


    public Base(String id, TimestampReference created, TimestampReference modified) {

        this.id = id;
        this.created = created;
        this.modified = modified;

    }


    public String getId() {

        return id;

    }


    public void setId(String id) {

        this.id = id;

    }


    public TimestampReference getCreated() {

        return created;

    }


    public void setCreated(TimestampReference created) {

        this.created = created;

    }


    public TimestampReference getModified() {

        return modified;

    }


    public void setModified(TimestampReference modified) {

        this.modified = modified;

    }


}
