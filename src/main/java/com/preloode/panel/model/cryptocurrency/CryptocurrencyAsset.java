package com.preloode.panel.model.cryptocurrency;

import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;


public class CryptocurrencyAsset extends Base {


    @Indexed(unique = true)
    private String name;

    @Indexed
    private BigInteger precision;


    public CryptocurrencyAsset() {


    }


    public CryptocurrencyAsset(String id, TimestampReference created, TimestampReference modified, String name, BigInteger precision) {

        super(id, created, modified);
        this.name = name;
        this.precision = precision;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public BigInteger getPrecision() {

        return precision;

    }


    public void setPrecision(BigInteger precision) {

        this.precision = precision;

    }


}
