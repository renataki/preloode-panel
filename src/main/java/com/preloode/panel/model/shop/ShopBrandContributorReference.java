package com.preloode.panel.model.shop;

import java.math.BigInteger;
import java.util.List;


public class ShopBrandContributorReference {


    private List<BigInteger> amountList;

    private List<String> idList;


    public ShopBrandContributorReference() {


    }


    public ShopBrandContributorReference(List<BigInteger> amountList, List<String> idList) {

        this.amountList = amountList;
        this.idList = idList;

    }


    public List<BigInteger> getAmountList() {

        return amountList;

    }


    public void setAmountList(List<BigInteger> amountList) {

        this.amountList = amountList;

    }


    public List<String> getIdList() {

        return idList;

    }


    public void setIdList(List<String> idList) {

        this.idList = idList;

    }


}
