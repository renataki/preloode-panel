package com.preloode.panel.model.transaction;

public class TransactionUserThirdPartyReference {


    private TransactionUserThirdPartyAccountReference account;

    private TransactionUserThirdPartyProviderReference provider;


    public TransactionUserThirdPartyReference() {


    }


    public TransactionUserThirdPartyReference(TransactionUserThirdPartyAccountReference account, TransactionUserThirdPartyProviderReference provider) {

        this.account = account;
        this.provider = provider;

    }


    public TransactionUserThirdPartyAccountReference getAccount() {

        return account;

    }


    public void setAccount(TransactionUserThirdPartyAccountReference account) {

        this.account = account;

    }


    public TransactionUserThirdPartyProviderReference getProvider() {

        return provider;

    }


    public void setProvider(TransactionUserThirdPartyProviderReference provider) {

        this.provider = provider;

    }


}
