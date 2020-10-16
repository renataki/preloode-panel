package com.preloode.panel.model.user;

import java.math.BigDecimal;
import java.util.List;


public class UserAccountReference {


    private List<BigDecimal> creditList;

    private UserThirdPartyReference thirdParty;

    private List<String> usernameList;


    public UserAccountReference() {


    }


    public UserAccountReference(List<BigDecimal> creditList, UserThirdPartyReference thirdParty, List<String> usernameList) {

        this.creditList = creditList;
        this.thirdParty = thirdParty;
        this.usernameList = usernameList;

    }


    public List<BigDecimal> getCreditList() {

        return creditList;

    }


    public void setCreditList(List<BigDecimal> creditList) {

        this.creditList = creditList;

    }


    public UserThirdPartyReference getThirdParty() {

        return thirdParty;

    }


    public void setThirdParty(UserThirdPartyReference thirdParty) {

        this.thirdParty = thirdParty;

    }


    public List<String> getUsernameList() {

        return usernameList;

    }


    public void setUsernameList(List<String> usernameList) {

        this.usernameList = usernameList;

    }


}
