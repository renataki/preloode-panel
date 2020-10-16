package com.preloode.panel.model.thirdParty;

import org.springframework.data.mongodb.core.index.Indexed;


public class ThirdPartyProviderReference {


    @Indexed
    private String id;

    @Indexed
    private String name;


    public ThirdPartyProviderReference() {


    }


    public ThirdPartyProviderReference(String id, String name) {

        this.id = id;
        this.name = name;

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
