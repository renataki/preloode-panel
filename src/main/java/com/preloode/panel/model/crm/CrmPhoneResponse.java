package com.preloode.panel.model.crm;

import com.preloode.panel.enumeration.crm.CrmDatabaseStatus;
import com.preloode.panel.enumeration.crm.CrmDatabaseType;
import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.global.Gender;
import com.preloode.panel.enumeration.global.Language;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class CrmPhoneResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private CrmDatabase crmDatabase;

    private List<CrmDatabase> crmDatabaseList;

    private List<CrmDatabaseStatus> crmDatabaseStatusList;

    private List<CrmDatabaseSource> crmDatabaseSourceList;

    private List<CrmDatabaseType> crmDatabaseTypeList;

    private List<CrmGroup> crmGroupList;

    private List<String> countryCodeList;

    private List<Country> countryList;

    private Map<String, Object> filter;

    private List<Gender> genderList;

    private List<Language> languageList;

    private String link;

    private int page;

    private int size;


    public CrmPhoneResponse() {



    }


    public CrmPhoneResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, CrmDatabase crmDatabase, List<CrmDatabase> crmDatabaseList, List<CrmDatabaseStatus> crmDatabaseStatusList, List<CrmDatabaseSource> crmDatabaseSourceList, List<CrmDatabaseType> crmDatabaseTypeList, List<CrmGroup> crmGroupList, List<String> countryCodeList, List<Country> countryList, Map<String, Object> filter, List<Gender> genderList, List<Language> languageList, String link, int page, int size) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.crmDatabase = crmDatabase;
        this.crmDatabaseList = crmDatabaseList;
        this.crmDatabaseStatusList = crmDatabaseStatusList;
        this.crmDatabaseSourceList = crmDatabaseSourceList;
        this.crmDatabaseTypeList = crmDatabaseTypeList;
        this.crmGroupList = crmGroupList;
        this.countryCodeList = countryCodeList;
        this.countryList = countryList;
        this.filter = filter;
        this.genderList = genderList;
        this.languageList = languageList;
        this.link = link;
        this.page = page;
        this.size = size;

    }


    public List<CompanyBranch> getCompanyBranchList() {

        return companyBranchList;

    }


    public void setCompanyBranchList(List<CompanyBranch> companyBranchList) {

        this.companyBranchList = companyBranchList;

    }


    public List<Company> getCompanyList() {

        return companyList;

    }


    public void setCompanyList(List<Company> companyList) {

        this.companyList = companyList;

    }


    public CrmDatabase getCrmDatabase() {

        return crmDatabase;

    }


    public void setCrmDatabase(CrmDatabase crmDatabase) {

        this.crmDatabase = crmDatabase;

    }


    public List<CrmDatabase> getCrmDatabaseList() {

        return crmDatabaseList;

    }


    public void setCrmDatabaseList(List<CrmDatabase> crmDatabaseList) {

        this.crmDatabaseList = crmDatabaseList;

    }


    public List<CrmDatabaseStatus> getCrmDatabaseStatusList() {

        return crmDatabaseStatusList;

    }


    public void setCrmDatabaseStatusList(List<CrmDatabaseStatus> crmDatabaseStatusList) {

        this.crmDatabaseStatusList = crmDatabaseStatusList;

    }


    public List<CrmDatabaseSource> getCrmDatabaseSourceList() {

        return crmDatabaseSourceList;

    }


    public void setCrmDatabaseSourceList(List<CrmDatabaseSource> crmDatabaseSourceList) {

        this.crmDatabaseSourceList = crmDatabaseSourceList;

    }


    public List<CrmDatabaseType> getCrmDatabaseTypeList() {

        return crmDatabaseTypeList;

    }


    public void setCrmDatabaseTypeList(List<CrmDatabaseType> crmDatabaseTypeList) {

        this.crmDatabaseTypeList = crmDatabaseTypeList;

    }


    public List<CrmGroup> getCrmGroupList() {

        return crmGroupList;

    }


    public void setCrmGroupList(List<CrmGroup> crmGroupList) {

        this.crmGroupList = crmGroupList;

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


    public List<Gender> getGenderList() {

        return genderList;

    }


    public void setGenderList(List<Gender> genderList) {

        this.genderList = genderList;

    }


    public List<Language> getLanguageList() {

        return languageList;

    }


    public void setLanguageList(List<Language> languageList) {

        this.languageList = languageList;

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
