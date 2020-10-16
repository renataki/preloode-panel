package com.preloode.panel.model.shop;

import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;


public class ShopProductPriceOneTimeReference {


    @Indexed
    private BigDecimal discount;

    @Indexed
    private BigDecimal normal;


    public ShopProductPriceOneTimeReference() {


    }


    public ShopProductPriceOneTimeReference(BigDecimal discount, BigDecimal normal) {

        this.discount = discount;
        this.normal = normal;

    }


    public BigDecimal getDiscount() {

        return discount;

    }


    public void setDiscount(BigDecimal discount) {

        this.discount = discount;

    }


    public BigDecimal getNormal() {

        return normal;

    }


    public void setNormal(BigDecimal normal) {

        this.normal = normal;

    }


}
