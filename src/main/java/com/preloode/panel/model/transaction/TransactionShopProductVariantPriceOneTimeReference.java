package com.preloode.panel.model.transaction;

import java.math.BigDecimal;
import java.util.List;


public class TransactionShopProductVariantPriceOneTimeReference {


    private List<List<BigDecimal>> discountList;

    private List<List<BigDecimal>> normalList;


    public TransactionShopProductVariantPriceOneTimeReference() {


    }


    public TransactionShopProductVariantPriceOneTimeReference(List<List<BigDecimal>> discountList, List<List<BigDecimal>> normalList) {

        this.discountList = discountList;
        this.normalList = normalList;

    }


    public List<List<BigDecimal>> getDiscountList() {

        return discountList;

    }


    public void setDiscountList(List<List<BigDecimal>> discountList) {

        this.discountList = discountList;

    }


    public List<List<BigDecimal>> getNormalList() {

        return normalList;

    }


    public void setNormalList(List<List<BigDecimal>> normalList) {

        this.normalList = normalList;

    }


}
