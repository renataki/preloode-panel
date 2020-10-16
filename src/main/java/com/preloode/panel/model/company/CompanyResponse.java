package com.preloode.panel.model.company;

import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.setting.ApplicationStatus;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class CompanyResponse extends BaseResponse {


    private List<ApplicationStatus> applicationStatusList;

    private Company company;

    private List<Company> companyList;

    private List<String> countryCodeList;

    private List<Country> countryList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private int size;


    public CompanyResponse() {


    }


    public CompanyResponse(String response, boolean result, List<ApplicationStatus> applicationStatusList, Company company, List<Company> companyList, List<String> countryCodeList, List<Country> countryList, Map<String, Object> filter, String link, int page, int size) {

        super(response, result);
        this.applicationStatusList = applicationStatusList;
        this.company = company;
        this.companyList = companyList;
        this.countryCodeList = countryCodeList;
        this.countryList = countryList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.size = size;

    }


    public List<ApplicationStatus> getApplicationStatusList() {

        return applicationStatusList;

    }


    public void setApplicationStatusList(List<ApplicationStatus> applicationStatusList) {

        this.applicationStatusList = applicationStatusList;

    }


    public Company getCompany() {

        return company;

    }


    public void setCompany(Company company) {

        this.company = company;

    }


    public List<Company> getCompanyList() {

        return companyList;

    }


    public void setCompanyList(List<Company> companyList) {

        this.companyList = companyList;

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


    public Map<String, Object> getFilter() {

        return filter;

    }


    public void setFilter(Map<String, Object> filter) {

        this.filter = filter;

    }


    public String getLink() {

        return link;

    }


    public void setLink(String link) {

        this.link = link;

    }


    public int getPage() {

        return page;

    }


    public void setPage(int page) {

        this.page = page;

    }


    public int getSize() {

        return size;

    }


    public void setSize(int size) {

        this.size = size;

    }


}
