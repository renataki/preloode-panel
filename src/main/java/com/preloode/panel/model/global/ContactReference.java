package com.preloode.panel.model.global;

import org.springframework.data.mongodb.core.index.Indexed;


public class ContactReference {


    @Indexed
    private String emailAddress;

    @Indexed
    private String faxNumber;

    @Indexed
    private String lineId;

    @Indexed
    private String phoneNumber;

    @Indexed
    private String wechatId;

    @Indexed
    private String whatsappNumber;


    public ContactReference() {


    }


    public ContactReference(String emailAddress, String faxNumber, String lineId, String phoneNumber, String wechatId, String whatsappNumber) {

        this.emailAddress = emailAddress;
        this.faxNumber = faxNumber;
        this.lineId = lineId;
        this.phoneNumber = phoneNumber;
        this.wechatId = wechatId;
        this.whatsappNumber = whatsappNumber;

    }


    public String getEmailAddress() {

        return emailAddress;

    }


    public void setEmailAddress(String emailAddress) {

        this.emailAddress = emailAddress;

    }


    public String getFaxNumber() {

        return faxNumber;

    }


    public void setFaxNumber(String faxNumber) {

        this.faxNumber = faxNumber;

    }


    public String getLineId() {

        return lineId;

    }


    public void setLineId(String lineId) {

        this.lineId = lineId;

    }


    public String getPhoneNumber() {

        return phoneNumber;

    }


    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;

    }


    public String getWechatId() {

        return wechatId;

    }


    public void setWechatId(String wechatId) {

        this.wechatId = wechatId;

    }


    public String getWhatsappNumber() {

        return whatsappNumber;

    }


    public void setWhatsappNumber(String whatsappNumber) {

        this.whatsappNumber = whatsappNumber;

    }


}
