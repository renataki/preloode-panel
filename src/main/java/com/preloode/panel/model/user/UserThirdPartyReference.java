package com.preloode.panel.model.user;


public class UserThirdPartyReference {


    private UserThirdPartyAccountReference account;

    private UserThirdPartyProviderReference provider;


    public UserThirdPartyReference() {


    }


    public UserThirdPartyReference(UserThirdPartyAccountReference account, UserThirdPartyProviderReference provider) {

        this.account = account;
        this.provider = provider;

    }


    public UserThirdPartyAccountReference getAccount() {

        return account;

    }


    public void setAccount(UserThirdPartyAccountReference account) {

        this.account = account;

    }


    public UserThirdPartyProviderReference getProvider() {

        return provider;

    }


    public void setProvider(UserThirdPartyProviderReference provider) {

        this.provider = provider;

    }


}
