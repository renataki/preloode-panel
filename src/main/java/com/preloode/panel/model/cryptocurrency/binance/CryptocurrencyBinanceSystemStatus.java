package com.preloode.panel.model.cryptocurrency.binance;

public class CryptocurrencyBinanceSystemStatus {


    private int status;

    private String msg;


    public CryptocurrencyBinanceSystemStatus() {


    }


    public CryptocurrencyBinanceSystemStatus(int status, String msg) {

        this.status = status;
        this.msg = msg;

    }


    public int getStatus() {

        return status;

    }


    public void setStatus(int status) {

        this.status = status;

    }


    public String getMsg() {

        return msg;

    }


    public void setMsg(String msg) {

        this.msg = msg;

    }


}
