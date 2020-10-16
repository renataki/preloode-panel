package com.preloode.panel.model.shop;

import java.math.BigDecimal;
import java.util.List;


public class ShopProductPriceRecurringReference {


    private List<BigDecimal> discountList;

    private List<BigDecimal> normalList;


    public ShopProductPriceRecurringReference() {


    }


    public ShopProductPriceRecurringReference(List<BigDecimal> discountList, List<BigDecimal> normalList) {

        this.discountList = discountList;
        this.normalList = normalList;

    }


    public List<BigDecimal> getDiscountList() {

        return discountList;

    }


    public void setDiscountList(List<BigDecimal> discountList) {

        this.discountList = discountList;

    }


    public List<BigDecimal> getNormalList() {

        return normalList;

    }


    public void setNormalList(List<BigDecimal> normalList) {

        this.normalList = normalList;

    }


}
