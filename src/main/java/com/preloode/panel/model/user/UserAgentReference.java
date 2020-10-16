package com.preloode.panel.model.user;

import org.springframework.data.mongodb.core.index.Indexed;


public class UserAgentReference {


    @Indexed
    private String id;

    @Indexed
    private UserBrowserReference browser;

    @Indexed
    private UserDeviceReference device;

    @Indexed
    private String ip;

    @Indexed
    private String jSessionId;

    @Indexed
    private String mac;


    public UserAgentReference() {


    }


    public UserAgentReference(String id, UserBrowserReference browser, UserDeviceReference device, String ip, String jSessionId, String mac) {

        this.id = id;
        this.browser = browser;
        this.device = device;
        this.ip = ip;
        this.jSessionId = jSessionId;
        this.mac = mac;

    }


    public String getId() {

        return id;

    }


    public void setId(String id) {

        this.id = id;

    }


    public UserBrowserReference getBrowser() {

        return browser;

    }


    public void setBrowser(UserBrowserReference browser) {

        this.browser = browser;

    }


    public UserDeviceReference getDevice() {

        return device;

    }


    public void setDevice(UserDeviceReference device) {

        this.device = device;

    }


    public String getIp() {

        return ip;

    }


    public void setIp(String ip) {

        this.ip = ip;

    }


    public String getjSessionId() {

        return jSessionId;

    }


    public void setjSessionId(String jSessionId) {

        this.jSessionId = jSessionId;

    }


    public String getMac() {

        return mac;

    }


    public void setMac(String mac) {

        this.mac = mac;

    }


}
