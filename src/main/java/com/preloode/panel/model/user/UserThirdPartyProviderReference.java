package com.preloode.panel.model.user;

import java.util.List;


public class UserThirdPartyProviderReference {


    private List<String> idList;

    private List<String> nameList;


    public UserThirdPartyProviderReference() {


    }


    public UserThirdPartyProviderReference(List<String> idList, List<String> nameList) {

        this.idList = idList;
        this.nameList = nameList;

    }


    public List<String> getIdList() {

        return idList;

    }


    public void setIdList(List<String> idList) {

        this.idList = idList;

    }


    public List<String> getNameList() {

        return nameList;

    }


    public void setNameList(List<String> nameList) {

        this.nameList = nameList;

    }


}
