package com.preloode.panel.model.shop;

public class ShopProductPriceReference {


    private ShopProductPriceExchangeReference exchange;

    private ShopProductPriceOneTimeReference oneTime;

    private ShopProductPriceRecurringReference recurring;


    public ShopProductPriceReference() {


    }


    public ShopProductPriceReference(ShopProductPriceExchangeReference exchange, ShopProductPriceOneTimeReference oneTime, ShopProductPriceRecurringReference recurring) {

        this.exchange = exchange;
        this.oneTime = oneTime;
        this.recurring = recurring;

    }


    public ShopProductPriceExchangeReference getExchange() {

        return exchange;

    }


    public void setExchange(ShopProductPriceExchangeReference exchange) {

        this.exchange = exchange;

    }


    public ShopProductPriceOneTimeReference getOneTime() {

        return oneTime;

    }


    public void setOneTime(ShopProductPriceOneTimeReference oneTime) {

        this.oneTime = oneTime;

    }


    public ShopProductPriceRecurringReference getRecurring() {

        return recurring;

    }


    public void setRecurring(ShopProductPriceRecurringReference recurring) {

        this.recurring = recurring;

    }


}
