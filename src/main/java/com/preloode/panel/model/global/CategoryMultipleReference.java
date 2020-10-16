package com.preloode.panel.model.global;

import java.util.List;


public class CategoryMultipleReference {


    private List<String> idList;

    private List<String> nameList;

    private List<String> pathList;

    private List<String> urlList;


    public CategoryMultipleReference() {


    }


    public CategoryMultipleReference(List<String> idList, List<String> nameList, List<String> pathList, List<String> urlList) {

        this.idList = idList;
        this.nameList = nameList;
        this.pathList = pathList;
        this.urlList = urlList;

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


    public List<String> getPathList() {

        return pathList;

    }


    public void setPathList(List<String> pathList) {

        this.pathList = pathList;

    }


    public List<String> getUrlList() {

        return urlList;

    }


    public void setUrlList(List<String> urlList) {

        this.urlList = urlList;

    }


}
