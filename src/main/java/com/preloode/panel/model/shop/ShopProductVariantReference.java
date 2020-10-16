package com.preloode.panel.model.shop;

import java.math.BigInteger;
import java.util.List;


public class ShopProductVariantReference {


    private List<String> nameList;

    private ShopProductVariantPriceReference price;

    private List<String> skuList;

    private List<BigInteger> stockList;


    public ShopProductVariantReference() {


    }


    public ShopProductVariantReference(List<String> nameList, ShopProductVariantPriceReference price, List<String> skuList, List<BigInteger> stockList) {

        this.nameList = nameList;
        this.price = price;
        this.skuList = skuList;
        this.stockList = stockList;

    }


    public List<String> getNameList() {

        return nameList;

    }


    public void setNameList(List<String> nameList) {

        this.nameList = nameList;

    }


    public ShopProductVariantPriceReference getPrice() {

        return price;

    }


    public void setPrice(ShopProductVariantPriceReference price) {

        this.price = price;

    }


    public List<String> getSkuList() {

        return skuList;

    }


    public void setSkuList(List<String> skuList) {

        this.skuList = skuList;

    }


    public List<BigInteger> getStockList() {

        return stockList;

    }


    public void setStockList(List<BigInteger> stockList) {

        this.stockList = stockList;

    }


}
