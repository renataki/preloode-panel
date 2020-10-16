package com.preloode.panel.model.user;

public class UserBrowserReference {


    private String id;

    private String name;

    private String manufacturer;

    private String renderingEngine;

    private String type;

    private String version;


    public UserBrowserReference() {


    }


    public UserBrowserReference(String id, String name, String manufacturer, String renderingEngine, String type, String version) {

        this.id = id;
        this.name = name;
        this.manufacturer = manufacturer;
        this.renderingEngine = renderingEngine;
        this.type = type;
        this.version = version;

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


    public String getManufacturer() {

        return manufacturer;

    }


    public void setManufacturer(String manufacturer) {

        this.manufacturer = manufacturer;

    }


    public String getRenderingEngine() {

        return renderingEngine;

    }


    public void setRenderingEngine(String renderingEngine) {

        this.renderingEngine = renderingEngine;

    }


    public String getType() {

        return type;

    }


    public void setType(String type) {

        this.type = type;

    }


    public String getVersion() {

        return version;

    }


    public void setVersion(String version) {

        this.version = version;

    }


}
