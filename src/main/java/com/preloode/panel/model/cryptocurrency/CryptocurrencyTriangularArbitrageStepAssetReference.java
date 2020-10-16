package com.preloode.panel.model.cryptocurrency;

import org.springframework.data.mongodb.core.index.Indexed;


public class CryptocurrencyTriangularArbitrageStepAssetReference {


    @Indexed
    private String base;

    @Indexed
    private String quote;


    public CryptocurrencyTriangularArbitrageStepAssetReference() {


    }


    public CryptocurrencyTriangularArbitrageStepAssetReference(String base, String quote) {

        this.base = base;
        this.quote = quote;

    }


    public String getBase() {

        return base;

    }


    public void setBase(String base) {

        this.base = base;

    }


    public String getQuote() {

        return quote;

    }


    public void setQuote(String quote) {

        this.quote = quote;

    }


}
