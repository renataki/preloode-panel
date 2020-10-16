package com.preloode.panel.configuration.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SettingMetaGeoConfiguration {


    @Value("${setting.meta.geo.placename}")
    private String placename;

    @Value("${setting.meta.geo.region}")
    private String region;


    public String getPlacename() {

        return placename;

    }


    public void setPlacename(String placename) {

        this.placename = placename;

    }


    public String getRegion() {

        return region;

    }


    public void setRegion(String region) {

        this.region = region;

    }


}
