package com.preloode.panel.model.transaction;

import java.math.BigInteger;
import java.util.List;


public class TransactionShopProductVariantReference {


    private List<List<String>> nameList;

    private TransactionShopProductVariantPriceReference price;

    private List<List<String>> skuList;

    private List<List<BigInteger>> stockList;


    public TransactionShopProductVariantReference() {


    }


    public TransactionShopProductVariantReference(List<List<String>> nameList, TransactionShopProductVariantPriceReference price, List<List<String>> skuList, List<List<BigInteger>> stockList) {

        this.nameList = nameList;
        this.price = price;
        this.skuList = skuList;
        this.stockList = stockList;

    }


    public List<List<String>> getNameList() {

        return nameList;

    }


    public void setNameList(List<List<String>> nameList) {

        this.nameList = nameList;

    }


    public TransactionShopProductVariantPriceReference getPrice() {

        return price;

    }


    public void setPrice(TransactionShopProductVariantPriceReference price) {

        this.price = price;

    }


    public List<List<String>> getSkuList() {

        return skuList;

    }


    public void setSkuList(List<List<String>> skuList) {

        this.skuList = skuList;

    }


    public List<List<BigInteger>> getStockList() {

        return stockList;

    }


    public void setStockList(List<List<BigInteger>> stockList) {

        this.stockList = stockList;

    }


}
