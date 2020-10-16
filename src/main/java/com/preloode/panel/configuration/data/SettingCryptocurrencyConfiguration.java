package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SettingCryptocurrencyConfiguration {


    @Autowired
    private SettingCryptocurrencyAssetConfiguration asset;

    @Autowired
    private SettingCryptocurrencyCurrencyConfiguration currency;

    @Autowired
    private SettingCryptocurrencyLotConfiguration lot;

    @Autowired
    private SettingCryptocurrencySymbolConfiguration symbol;


    public SettingCryptocurrencyAssetConfiguration getAsset() {

        return asset;

    }


    public void setAsset(SettingCryptocurrencyAssetConfiguration asset) {

        this.asset = asset;

    }


    public SettingCryptocurrencyCurrencyConfiguration getCurrency() {

        return currency;

    }


    public void setCurrency(SettingCryptocurrencyCurrencyConfiguration currency) {

        this.currency = currency;

    }


    public SettingCryptocurrencyLotConfiguration getLot() {

        return lot;

    }


    public void setLot(SettingCryptocurrencyLotConfiguration lot) {

        this.lot = lot;

    }


    public SettingCryptocurrencySymbolConfiguration getSymbol() {

        return symbol;

    }


    public void setSymbol(SettingCryptocurrencySymbolConfiguration symbol) {

        this.symbol = symbol;

    }


}
