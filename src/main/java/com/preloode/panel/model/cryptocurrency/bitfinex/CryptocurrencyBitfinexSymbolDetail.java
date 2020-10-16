package com.preloode.panel.model.cryptocurrency.bitfinex;

import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;

import java.math.BigDecimal;
import java.math.BigInteger;


public class CryptocurrencyBitfinexSymbolDetail extends Base {


    private String pair;

    private BigInteger price_precision;

    private BigDecimal initial_margin;

    private BigDecimal minimum_margin;

    private BigDecimal maximum_order_size;

    private BigDecimal minimum_order_size;

    private String expiration;

    private boolean margin;


    public CryptocurrencyBitfinexSymbolDetail() {


    }


    public CryptocurrencyBitfinexSymbolDetail(String id, TimestampReference created, TimestampReference modified, String pair, BigInteger price_precision, BigDecimal initial_margin, BigDecimal minimum_margin, BigDecimal maximum_order_size, BigDecimal minimum_order_size, String expiration, boolean margin) {

        super(id, created, modified);
        this.pair = pair;
        this.price_precision = price_precision;
        this.initial_margin = initial_margin;
        this.minimum_margin = minimum_margin;
        this.maximum_order_size = maximum_order_size;
        this.minimum_order_size = minimum_order_size;
        this.expiration = expiration;
        this.margin = margin;

    }


    public String getPair() {

        return pair;

    }


    public void setPair(String pair) {

        this.pair = pair;

    }


    public BigInteger getPrice_precision() {

        return price_precision;

    }


    public void setPrice_precision(BigInteger price_precision) {

        this.price_precision = price_precision;

    }


    public BigDecimal getInitial_margin() {

        return initial_margin;

    }


    public void setInitial_margin(BigDecimal initial_margin) {

        this.initial_margin = initial_margin;

    }


    public BigDecimal getMinimum_margin() {

        return minimum_margin;

    }


    public void setMinimum_margin(BigDecimal minimum_margin) {

        this.minimum_margin = minimum_margin;

    }


    public BigDecimal getMaximum_order_size() {

        return maximum_order_size;

    }


    public void setMaximum_order_size(BigDecimal maximum_order_size) {

        this.maximum_order_size = maximum_order_size;

    }


    public BigDecimal getMinimum_order_size() {

        return minimum_order_size;

    }


    public void setMinimum_order_size(BigDecimal minimum_order_size) {

        this.minimum_order_size = minimum_order_size;

    }


    public String getExpiration() {

        return expiration;

    }


    public void setExpiration(String expiration) {

        this.expiration = expiration;

    }


    public boolean isMargin() {

        return margin;

    }


    public void setMargin(boolean margin) {

        this.margin = margin;

    }


}
