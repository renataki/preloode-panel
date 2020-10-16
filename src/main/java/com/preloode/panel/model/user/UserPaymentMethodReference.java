package com.preloode.panel.model.user;

import com.preloode.panel.enumeration.payment.PaymentType;

import java.util.List;


public class UserPaymentMethodReference {


    private List<String> idList;

    private List<String> nameList;

    private List<PaymentType> typeList;


    public UserPaymentMethodReference() {


    }


    public UserPaymentMethodReference(List<String> idList, List<String> nameList, List<PaymentType> typeList) {

        this.idList = idList;
        this.nameList = nameList;
        this.typeList = typeList;

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


    public List<PaymentType> getTypeList() {

        return typeList;

    }


    public void setTypeList(List<PaymentType> typeList) {

        this.typeList = typeList;

    }


}
