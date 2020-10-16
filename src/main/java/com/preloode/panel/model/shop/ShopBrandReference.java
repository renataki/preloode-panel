package com.preloode.panel.model.shop;

import org.springframework.data.mongodb.core.index.Indexed;


public class ShopBrandReference {


    @Indexed
    private String id;

    @Indexed
    private String name;

    @Indexed
    private String path;

    @Indexed
    private String url;


    public ShopBrandReference() {


    }


    public ShopBrandReference(String id, String name, String path, String url) {

        this.id = id;
        this.name = name;
        this.path = path;
        this.url = url;

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


    public String getPath() {

        return path;

    }


    public void setPath(String path) {

        this.path = path;

    }


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


}
