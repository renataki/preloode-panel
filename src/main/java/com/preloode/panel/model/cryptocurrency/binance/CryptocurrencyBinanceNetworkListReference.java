package com.preloode.panel.model.cryptocurrency.binance;

import java.math.BigDecimal;
import java.math.BigInteger;


public class CryptocurrencyBinanceNetworkListReference {


    private String addressRegex;

    private String coin;

    private String depositDesc;

    private boolean depositEnable;

    private boolean isDefault;

    private String memoRegex;

    private BigInteger minConfirm;

    private String name;

    private String network;

    private boolean resetAddressStatus;

    private String specialTips;

    private BigInteger unLockConfirm;

    private String withdrawDesc;

    private boolean withdrawEnable;

    private BigDecimal withdrawFee;

    private BigDecimal withdrawIntegerMultiple;

    private BigDecimal withdrawMax;

    private BigDecimal withdrawMin;


    public CryptocurrencyBinanceNetworkListReference() {


    }


    public CryptocurrencyBinanceNetworkListReference(String addressRegex, String coin, String depositDesc, boolean depositEnable, boolean isDefault, String memoRegex, BigInteger minConfirm, String name, String network, boolean resetAddressStatus, String specialTips, BigInteger unLockConfirm, String withdrawDesc, boolean withdrawEnable, BigDecimal withdrawFee, BigDecimal withdrawIntegerMultiple, BigDecimal withdrawMax, BigDecimal withdrawMin) {

        this.addressRegex = addressRegex;
        this.coin = coin;
        this.depositDesc = depositDesc;
        this.depositEnable = depositEnable;
        this.isDefault = isDefault;
        this.memoRegex = memoRegex;
        this.minConfirm = minConfirm;
        this.name = name;
        this.network = network;
        this.resetAddressStatus = resetAddressStatus;
        this.specialTips = specialTips;
        this.unLockConfirm = unLockConfirm;
        this.withdrawDesc = withdrawDesc;
        this.withdrawEnable = withdrawEnable;
        this.withdrawFee = withdrawFee;
        this.withdrawIntegerMultiple = withdrawIntegerMultiple;
        this.withdrawMax = withdrawMax;
        this.withdrawMin = withdrawMin;

    }


    public String getAddressRegex() {

        return addressRegex;

    }


    public void setAddressRegex(String addressRegex) {

        this.addressRegex = addressRegex;

    }


    public String getCoin() {

        return coin;

    }


    public void setCoin(String coin) {

        this.coin = coin;

    }


    public String getDepositDesc() {

        return depositDesc;

    }


    public void setDepositDesc(String depositDesc) {

        this.depositDesc = depositDesc;

    }


    public boolean isDepositEnable() {

        return depositEnable;

    }


    public void setDepositEnable(boolean depositEnable) {

        this.depositEnable = depositEnable;

    }


    public boolean isIsDefault() {

        return isDefault;

    }


    public void setIsDefault(boolean isDefault) {

        this.isDefault = isDefault;

    }


    public String getMemoRegex() {

        return memoRegex;

    }


    public void setMemoRegex(String memoRegex) {

        this.memoRegex = memoRegex;

    }


    public BigInteger getMinConfirm() {

        return minConfirm;

    }


    public void setMinConfirm(BigInteger minConfirm) {

        this.minConfirm = minConfirm;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public String getNetwork() {

        return network;

    }


    public void setNetwork(String network) {

        this.network = network;

    }


    public boolean isResetAddressStatus() {

        return resetAddressStatus;

    }


    public void setResetAddressStatus(boolean resetAddressStatus) {

        this.resetAddressStatus = resetAddressStatus;

    }


    public String getSpecialTips() {

        return specialTips;

    }


    public void setSpecialTips(String specialTips) {

        this.specialTips = specialTips;

    }


    public BigInteger getUnLockConfirm() {

        return unLockConfirm;

    }


    public void setUnLockConfirm(BigInteger unLockConfirm) {

        this.unLockConfirm = unLockConfirm;

    }


    public String getWithdrawDesc() {

        return withdrawDesc;

    }


    public void setWithdrawDesc(String withdrawDesc) {

        this.withdrawDesc = withdrawDesc;

    }


    public boolean isWithdrawEnable() {

        return withdrawEnable;

    }


    public void setWithdrawEnable(boolean withdrawEnable) {

        this.withdrawEnable = withdrawEnable;

    }


    public BigDecimal getWithdrawFee() {

        return withdrawFee;

    }


    public void setWithdrawFee(BigDecimal withdrawFee) {

        this.withdrawFee = withdrawFee;

    }


    public BigDecimal getWithdrawIntegerMultiple() {

        return withdrawIntegerMultiple;

    }


    public void setWithdrawIntegerMultiple(BigDecimal withdrawIntegerMultiple) {

        this.withdrawIntegerMultiple = withdrawIntegerMultiple;

    }


    public BigDecimal getWithdrawMax() {

        return withdrawMax;

    }


    public void setWithdrawMax(BigDecimal withdrawMax) {

        this.withdrawMax = withdrawMax;

    }


    public BigDecimal getWithdrawMin() {

        return withdrawMin;

    }


    public void setWithdrawMin(BigDecimal withdrawMin) {

        this.withdrawMin = withdrawMin;

    }


}
