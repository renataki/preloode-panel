package com.preloode.panel.model.cryptocurrency.binance;

import java.util.List;


public class CryptocurrencyBinanceAccountStatus {


    private String msg;

    private boolean success;

    private List<String> objs;


    public CryptocurrencyBinanceAccountStatus() {


    }


    public CryptocurrencyBinanceAccountStatus(String msg, boolean success, List<String> objs) {

        this.msg = msg;
        this.success = success;
        this.objs = objs;

    }


    public String getMsg() {

        return msg;

    }


    public void setMsg(String msg) {

        this.msg = msg;

    }


    public boolean isSuccess() {

        return success;

    }


    public void setSuccess(boolean success) {

        this.success = success;

    }


    public List<String> getObjs() {

        return objs;

    }


    public void setObjs(List<String> objs) {

        this.objs = objs;

    }


}
