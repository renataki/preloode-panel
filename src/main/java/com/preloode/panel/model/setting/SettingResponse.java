package com.preloode.panel.model.setting;

import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.setting.ApplicationStatus;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;


public class SettingResponse extends BaseResponse {


    private List<ApplicationStatus> applicationStatusList;

    private List<String> countryCodeList;

    private List<Country> countryList;

    private Setting setting;


    public SettingResponse() {


    }


    public SettingResponse(String response, boolean result, List<ApplicationStatus> applicationStatusList, List<String> countryCodeList, List<Country> countryList, Setting setting) {

        super(response, result);
        this.applicationStatusList = applicationStatusList;
        this.countryCodeList = countryCodeList;
        this.countryList = countryList;
        this.setting = setting;

    }


    public List<ApplicationStatus> getApplicationStatusList() {

        return applicationStatusList;

    }


    public void setApplicationStatusList(List<ApplicationStatus> applicationStatusList) {

        this.applicationStatusList = applicationStatusList;

    }


    public List<String> getCountryCodeList() {

        return countryCodeList;

    }


    public void setCountryCodeList(List<String> countryCodeList) {

        this.countryCodeList = countryCodeList;

    }


    public List<Country> getCountryList() {

        return countryList;

    }


    public void setCountryList(List<Country> countryList) {

        this.countryList = countryList;

    }


    public Setting getSetting() {

        return setting;

    }


    public void setSetting(Setting setting) {

        this.setting = setting;

    }


}
