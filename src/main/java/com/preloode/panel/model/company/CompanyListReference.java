package com.preloode.panel.model.company;

import java.util.List;


public class CompanyListReference {


    private CompanyBranchListReference branch;

    private List<String> idList;

    private List<String> nameList;


    public CompanyListReference() {


    }


    public CompanyListReference(CompanyBranchListReference branch, List<String> idList, List<String> nameList) {

        this.branch = branch;
        this.idList = idList;
        this.nameList = nameList;

    }


    public CompanyBranchListReference getBranch() {

        return branch;

    }


    public void setBranch(CompanyBranchListReference branch) {

        this.branch = branch;

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


}
