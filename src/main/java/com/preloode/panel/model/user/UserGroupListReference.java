package com.preloode.panel.model.user;

import com.preloode.panel.enumeration.user.UserType;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;


public class UserGroupListReference {


    private List<String> idList;

    private List<String> nameList;

    private List<UserType> typeList;


    public UserGroupListReference() {


    }


    public UserGroupListReference(List<String> idList, List<String> nameList, List<UserType> typeList) {

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


    public List<UserType> getTypeList() {

        return typeList;

    }


    public void setTypeList(List<UserType> typeList) {

        this.typeList = typeList;

    }


}
