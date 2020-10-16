package com.preloode.panel.model.shop;

public class ShopProductVariantPriceReference {


    private ShopProductVariantPriceOneTimeReference oneTime;

    private ShopProductVariantPriceRecurringReference recurring;


    public ShopProductVariantPriceReference() {


    }


    public ShopProductVariantPriceReference(ShopProductVariantPriceOneTimeReference oneTime, ShopProductVariantPriceRecurringReference recurring) {

        this.oneTime = oneTime;
        this.recurring = recurring;

    }


    public ShopProductVariantPriceOneTimeReference getOneTime() {

        return oneTime;

    }


    public void setOneTime(ShopProductVariantPriceOneTimeReference oneTime) {

        this.oneTime = oneTime;

    }


    public ShopProductVariantPriceRecurringReference getRecurring() {

        return recurring;

    }


    public void setRecurring(ShopProductVariantPriceRecurringReference recurring) {

        this.recurring = recurring;

    }


}
