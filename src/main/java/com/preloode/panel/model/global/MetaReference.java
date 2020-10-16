package com.preloode.panel.model.global;

public class MetaReference {


    private String description;

    private String keyword;

    private String title;


    public MetaReference() {


    }


    public MetaReference(String description, String keyword, String title) {

        this.description = description;
        this.keyword = keyword;
        this.title = title;

    }


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public String getKeyword() {

        return keyword;

    }


    public void setKeyword(String keyword) {

        this.keyword = keyword;

    }


    public String getTitle() {

        return title;

    }


    public void setTitle(String title) {

        this.title = title;

    }


}
