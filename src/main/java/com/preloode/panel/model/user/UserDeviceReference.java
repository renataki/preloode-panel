package com.preloode.panel.model.user;

public class UserDeviceReference {


    private String id;

    private String manufacturer;

    private String operatingSystem;

    private String type;


    public UserDeviceReference() {


    }


    public UserDeviceReference(String id, String manufacturer, String operatingSystem, String type) {

        this.id = id;
        this.manufacturer = manufacturer;
        this.operatingSystem = operatingSystem;
        this.type = type;

    }


    public String getId() {

        return id;

    }


    public void setId(String id) {

        this.id = id;

    }


    public String getManufacturer() {

        return manufacturer;

    }


    public void setManufacturer(String manufacturer) {

        this.manufacturer = manufacturer;

    }


    public String getOperatingSystem() {

        return operatingSystem;

    }


    public void setOperatingSystem(String operatingSystem) {

        this.operatingSystem = operatingSystem;

    }


    public String getType() {

        return type;

    }


    public void setType(String type) {

        this.type = type;

    }


}
