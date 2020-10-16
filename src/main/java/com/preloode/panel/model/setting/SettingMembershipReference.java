package com.preloode.panel.model.setting;

import org.springframework.data.mongodb.core.index.Indexed;


public class SettingMembershipReference {


    @Indexed
    private boolean password;


    public SettingMembershipReference() {



    }


    public SettingMembershipReference(boolean password) {

        this.password = password;

    }


    public boolean isPassword() {

        return password;

    }


    public void setPassword(boolean password) {

        this.password = password;

    }


}
