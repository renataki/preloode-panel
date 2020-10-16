package com.preloode.panel.model.setting;

import org.springframework.data.mongodb.core.index.Indexed;


public class SettingActivationReference {


    @Indexed
    private boolean email;

    @Indexed
    private boolean sms;


    public SettingActivationReference() {


    }


    public SettingActivationReference(boolean email, boolean sms) {

        this.email = email;
        this.sms = sms;

    }


    public boolean getEmail() {

        return email;

    }


    public void setEmail(boolean email) {

        this.email = email;

    }


    public boolean getSms() {

        return sms;

    }


    public void setSms(boolean sms) {

        this.sms = sms;

    }


}
