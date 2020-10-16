package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SettingCryptocurrencyAssetConfiguration {


    @Value("${setting.cryptocurrency.asset.low-value}")
    private String lowValue;

    @Value("${setting.cryptocurrency.asset.primary}")
    private String primary;


    public String getLowValue() {

        return lowValue;

    }


    public void setLowValue(String lowValue) {

        this.lowValue = lowValue;

    }


    public String getPrimary() {

        return primary;

    }


    public void setPrimary(String primary) {

        this.primary = primary;

    }


}
