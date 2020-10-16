package com.preloode.panel.model.global;

import org.springframework.data.mongodb.core.index.Indexed;


public class NameReference {


    @Indexed
    private String first;

    @Indexed
    private String last;

    @Indexed
    private String middle;


    public NameReference() {


    }


    public NameReference(String first, String last, String middle) {

        this.first = first;
        this.last = last;
        this.middle = middle;

    }


    public String getFirst() {

        return first;

    }


    public void setFirst(String first) {

        this.first = first;

    }


    public String getLast() {

        return last;

    }


    public void setLast(String last) {

        this.last = last;

    }


    public String getMiddle() {

        return middle;

    }


    public void setMiddle(String middle) {

        this.middle = middle;

    }


}
