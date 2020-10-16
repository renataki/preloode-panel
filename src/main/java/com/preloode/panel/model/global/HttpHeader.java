package com.preloode.panel.model.global;

import java.util.List;


public class HttpHeader {


    private List<String> name;

    private List<Object> value;


    public HttpHeader() {


    }


    public HttpHeader(List<String> name, List<Object> value) {

        this.name = name;
        this.value = value;

    }


    public List<String> getName() {

        return name;

    }


    public void setName(List<String> name) {

        this.name = name;

    }


    public List<Object> getValue() {

        return value;

    }


    public void setValue(List<Object> value) {

        this.value = value;

    }


}
