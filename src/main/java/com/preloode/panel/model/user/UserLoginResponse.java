package com.preloode.panel.model.user;

import com.preloode.panel.model.global.BaseResponse;


public class UserLoginResponse extends BaseResponse {


    private String avatar;

    private String name;

    private String username;


    public UserLoginResponse() {


    }


    public UserLoginResponse(String response, boolean result, String avatar, String name, String username) {

        super(response, result);
        this.avatar = avatar;
        this.name = name;
        this.username = username;

    }


    public String getAvatar() {

        return avatar;

    }


    public void setAvatar(String avatar) {

        this.avatar = avatar;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public String getUsername() {

        return username;

    }


    public void setUsername(String username) {

        this.username = username;

    }


}
