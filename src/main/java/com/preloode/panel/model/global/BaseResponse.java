package com.preloode.panel.model.global;

public class BaseResponse {


    private String response;

    private boolean result;


    public BaseResponse() {


    }


    public BaseResponse(String response, boolean result) {

        this.response = response;
        this.result = result;

    }


    public String getResponse() {

        return response;

    }


    public void setResponse(String response) {

        this.response = response;

    }


    public boolean isResult() {

        return result;

    }


    public void setResult(boolean result) {

        this.result = result;

    }


}
