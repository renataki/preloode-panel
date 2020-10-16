package com.preloode.panel.model.user;

import java.util.List;


public class UserPaymentReference {


    private List<String> branchList;

    private UserPaymentMethodReference method;

    private List<String> nameList;

    private List<String> numberList;


    public UserPaymentReference() {


    }


    public UserPaymentReference(List<String> branchList, UserPaymentMethodReference method, List<String> nameList, List<String> numberList) {

        this.branchList = branchList;
        this.method = method;
        this.nameList = nameList;
        this.numberList = numberList;

    }


    public List<String> getBranchList() {

        return branchList;

    }


    public void setBranchList(List<String> branchList) {

        this.branchList = branchList;

    }


    public UserPaymentMethodReference getMethod() {

        return method;

    }


    public void setMethod(UserPaymentMethodReference method) {

        this.method = method;

    }


    public List<String> getNameList() {

        return nameList;

    }


    public void setNameList(List<String> nameList) {

        this.nameList = nameList;

    }


    public List<String> getNumberList() {

        return numberList;

    }


    public void setNumberList(List<String> numberList) {

        this.numberList = numberList;

    }


}
