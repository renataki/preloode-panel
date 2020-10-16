package com.preloode.panel.model.transaction;

import com.preloode.panel.enumeration.shop.PriceRecurring;

import java.util.List;


public class TransactionShopProductReference {


    private List<PriceRecurring> cycleList;

    private List<String> idList;

    private List<String> nameList;

    private List<String> referenceList;

    private TransactionShopProductVariantReference variantList;


    public TransactionShopProductReference() {


    }


    public TransactionShopProductReference(List<PriceRecurring> cycleList, List<String> idList, List<String> nameList, List<String> referenceList, TransactionShopProductVariantReference variantList) {

        this.cycleList = cycleList;
        this.idList = idList;
        this.nameList = nameList;
        this.referenceList = referenceList;
        this.variantList = variantList;

    }


    public List<PriceRecurring> getCycleList() {

        return cycleList;

    }


    public void setCycleList(List<PriceRecurring> cycleList) {

        this.cycleList = cycleList;

    }


    public List<String> getIdList() {

        return idList;

    }


    public void setIdList(List<String> idList) {

        this.idList = idList;

    }


    public List<String> getNameList() {

        return nameList;

    }


    public void setNameList(List<String> nameList) {

        this.nameList = nameList;

    }


    public List<String> getReferenceList() {

        return referenceList;

    }


    public void setReferenceList(List<String> referenceList) {

        this.referenceList = referenceList;

    }


    public TransactionShopProductVariantReference getVariantList() {

        return variantList;

    }


    public void setVariantList(TransactionShopProductVariantReference variantList) {

        this.variantList = variantList;

    }


}
