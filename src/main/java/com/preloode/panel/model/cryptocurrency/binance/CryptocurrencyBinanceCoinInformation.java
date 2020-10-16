package com.preloode.panel.model.cryptocurrency.binance;

import java.math.BigDecimal;
import java.util.List;


public class CryptocurrencyBinanceCoinInformation {


    private String coin;

    private boolean depositAllEnable;

    private BigDecimal free;

    private BigDecimal freeze;

    private BigDecimal ipoable;

    private BigDecimal ipoing;

    private boolean isLegalMoney;

    private BigDecimal locked;

    private String name;

    private List<CryptocurrencyBinanceNetworkListReference> networkList;

    private BigDecimal storage;

    private boolean trading;

    private boolean withdrawAllEnable;

    private BigDecimal withdrawing;


    public CryptocurrencyBinanceCoinInformation() {


    }


    public CryptocurrencyBinanceCoinInformation(String coin, boolean depositAllEnable, BigDecimal free, BigDecimal freeze, BigDecimal ipoable, BigDecimal ipoing, boolean isLegalMoney, BigDecimal locked, String name, List<CryptocurrencyBinanceNetworkListReference> networkList, BigDecimal storage, boolean trading, boolean withdrawAllEnable, BigDecimal withdrawing) {

        this.coin = coin;
        this.depositAllEnable = depositAllEnable;
        this.free = free;
        this.freeze = freeze;
        this.ipoable = ipoable;
        this.ipoing = ipoing;
        this.isLegalMoney = isLegalMoney;
        this.locked = locked;
        this.name = name;
        this.networkList = networkList;
        this.storage = storage;
        this.trading = trading;
        this.withdrawAllEnable = withdrawAllEnable;
        this.withdrawing = withdrawing;

    }


    public String getCoin() {

        return coin;

    }


    public void setCoin(String coin) {

        this.coin = coin;

    }


    public boolean isDepositAllEnable() {

        return depositAllEnable;

    }


    public void setDepositAllEnable(boolean depositAllEnable) {

        this.depositAllEnable = depositAllEnable;

    }


    public BigDecimal getFree() {

        return free;

    }


    public void setFree(BigDecimal free) {

        this.free = free;

    }


    public BigDecimal getFreeze() {

        return freeze;

    }


    public void setFreeze(BigDecimal freeze) {

        this.freeze = freeze;

    }


    public BigDecimal getIpoable() {

        return ipoable;

    }


    public void setIpoable(BigDecimal ipoable) {

        this.ipoable = ipoable;

    }


    public BigDecimal getIpoing() {

        return ipoing;

    }


    public void setIpoing(BigDecimal ipoing) {

        this.ipoing = ipoing;

    }


    public boolean isIsLegalMoney() {

        return isLegalMoney;

    }


    public void setIsLegalMoney(boolean isLegalMoney) {

        this.isLegalMoney = isLegalMoney;

    }


    public BigDecimal getLocked() {

        return locked;

    }


    public void setLocked(BigDecimal locked) {

        this.locked = locked;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public List<CryptocurrencyBinanceNetworkListReference> getNetworkList() {

        return networkList;

    }


    public void setNetworkList(List<CryptocurrencyBinanceNetworkListReference> networkList) {

        this.networkList = networkList;

    }


    public BigDecimal getStorage() {

        return storage;

    }


    public void setStorage(BigDecimal storage) {

        this.storage = storage;

    }


    public boolean isTrading() {

        return trading;

    }


    public void setTrading(boolean trading) {

        this.trading = trading;

    }


    public boolean isWithdrawAllEnable() {

        return withdrawAllEnable;

    }


    public void setWithdrawAllEnable(boolean withdrawAllEnable) {

        this.withdrawAllEnable = withdrawAllEnable;

    }


    public BigDecimal getWithdrawing() {

        return withdrawing;

    }


    public void setWithdrawing(BigDecimal withdrawing) {

        this.withdrawing = withdrawing;

    }


}
