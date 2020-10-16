package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SettingCryptocurrencyCurrencyConfiguration {


    @Value("${setting.cryptocurrency.currency.primary}")
    private String primary;


    public String getPrimary() {

        return primary;

    }


    public void setPrimary(String primary) {

        this.primary = primary;

    }


}
