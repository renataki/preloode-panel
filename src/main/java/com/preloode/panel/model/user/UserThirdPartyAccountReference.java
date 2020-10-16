package com.preloode.panel.model.user;

import java.util.List;


public class UserThirdPartyAccountReference {


    private List<String> idList;

    private List<String> usernameList;


    public UserThirdPartyAccountReference() {


    }


    public UserThirdPartyAccountReference(List<String> idList, List<String> usernameList) {

        this.idList = idList;
        this.usernameList = usernameList;

    }


    public List<String> getIdList() {

        return idList;

    }


    public void setIdList(List<String> idList) {

        this.idList = idList;

    }


    public List<String> getUsernameList() {

        return usernameList;

    }


    public void setUsernameList(List<String> usernameList) {

        this.usernameList = usernameList;

    }


}
