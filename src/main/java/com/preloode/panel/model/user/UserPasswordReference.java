package com.preloode.panel.model.user;

public class UserPasswordReference {


    private String main;

    private String recovery;


    public UserPasswordReference() {


    }


    public UserPasswordReference(String main, String recovery) {

        this.main = main;
        this.recovery = recovery;

    }


    public String getMain() {

        return main;

    }


    public void setMain(String main) {

        this.main = main;

    }


    public String getRecovery() {

        return recovery;

    }


    public void setRecovery(String recovery) {

        this.recovery = recovery;

    }


}
