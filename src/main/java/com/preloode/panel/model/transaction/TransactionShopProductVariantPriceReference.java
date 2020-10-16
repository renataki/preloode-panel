package com.preloode.panel.model.transaction;

public class TransactionShopProductVariantPriceReference {


    private TransactionShopProductVariantPriceOneTimeReference oneTime;

    private TransactionShopProductVariantPriceRecurringReference recurring;


    public TransactionShopProductVariantPriceReference() {


    }


    public TransactionShopProductVariantPriceReference(TransactionShopProductVariantPriceOneTimeReference oneTime, TransactionShopProductVariantPriceRecurringReference recurring) {

        this.oneTime = oneTime;
        this.recurring = recurring;

    }


    public TransactionShopProductVariantPriceOneTimeReference getOneTime() {

        return oneTime;

    }


    public void setOneTime(TransactionShopProductVariantPriceOneTimeReference oneTime) {

        this.oneTime = oneTime;

    }


    public TransactionShopProductVariantPriceRecurringReference getRecurring() {

        return recurring;

    }


    public void setRecurring(TransactionShopProductVariantPriceRecurringReference recurring) {

        this.recurring = recurring;

    }


}
