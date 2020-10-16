package com.preloode.panel.model.transaction;

import java.math.BigDecimal;
import java.util.List;


public class TransactionShopProductVariantPriceRecurringReference {


    private List<List<List<BigDecimal>>> discountList;

    private List<List<List<BigDecimal>>> normalList;


    public TransactionShopProductVariantPriceRecurringReference() {


    }


    public TransactionShopProductVariantPriceRecurringReference(List<List<List<BigDecimal>>> discountList, List<List<List<BigDecimal>>> normalList) {

        this.discountList = discountList;
        this.normalList = normalList;

    }


    public List<List<List<BigDecimal>>> getDiscountList() {

        return discountList;

    }


    public void setDiscountList(List<List<List<BigDecimal>>> discountList) {

        this.discountList = discountList;

    }


    public List<List<List<BigDecimal>>> getNormalList() {

        return normalList;

    }


    public void setNormalList(List<List<List<BigDecimal>>> normalList) {

        this.normalList = normalList;

    }


}
