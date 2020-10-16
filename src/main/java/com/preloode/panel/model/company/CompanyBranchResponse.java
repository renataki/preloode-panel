package com.preloode.panel.model.company;

import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.setting.ApplicationStatus;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class CompanyBranchResponse extends BaseResponse {


    private List<ApplicationStatus> applicationStatusList;

    private List<Company> companyList;

    private CompanyBranch companyBranch;

    private List<CompanyBranch> companyBranchList;

    private List<String> countryCodeList;

    private List<Country> countryList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private int size;


    public CompanyBranchResponse() {


    }


    public CompanyBranchResponse(String response, boolean result, List<ApplicationStatus> applicationStatusList, List<Company> companyList, CompanyBranch companyBranch, List<CompanyBranch> companyBranchList, List<String> countryCodeList, List<Country> countryList, Map<String, Object> filter, String link, int page, int size) {

        super(response, result);
        this.applicationStatusList = applicationStatusList;
        this.companyList = companyList;
        this.companyBranch = companyBranch;
        this.companyBranchList = companyBranchList;
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


    public List<Company> getCompanyList() {

        return companyList;

    }


    public void setCompanyList(List<Company> companyList) {

        this.companyList = companyList;

    }


    public CompanyBranch getCompanyBranch() {

        return companyBranch;

    }


    public void setCompanyBranch(CompanyBranch companyBranch) {

        this.companyBranch = companyBranch;

    }


    public List<CompanyBranch> getCompanyBranchList() {

        return companyBranchList;

    }


    public void setCompanyBranchList(List<CompanyBranch> companyBranchList) {

        this.companyBranchList = companyBranchList;

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
