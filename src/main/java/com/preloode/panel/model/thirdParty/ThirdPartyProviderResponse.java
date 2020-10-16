package com.preloode.panel.model.thirdParty;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class ThirdPartyProviderResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private int size;

    private List<Status> statusList;

    private ThirdPartyProvider thirdPartyProvider;

    private List<ThirdPartyProvider> thirdPartyProviderList;


    public ThirdPartyProviderResponse() {


    }


    public ThirdPartyProviderResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, Map<String, Object> filter, String link, int page, int size, List<Status> statusList, ThirdPartyProvider thirdPartyProvider, List<ThirdPartyProvider> thirdPartyProviderList) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.size = size;
        this.statusList = statusList;
        this.thirdPartyProvider = thirdPartyProvider;
        this.thirdPartyProviderList = thirdPartyProviderList;

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


    public List<Status> getStatusList() {

        return statusList;

    }


    public void setStatusList(List<Status> statusList) {

        this.statusList = statusList;

    }


    public ThirdPartyProvider getThirdPartyProvider() {

        return thirdPartyProvider;

    }


    public void setThirdPartyProvider(ThirdPartyProvider thirdPartyProvider) {

        this.thirdPartyProvider = thirdPartyProvider;

    }


    public List<ThirdPartyProvider> getThirdPartyProviderList() {

        return thirdPartyProviderList;

    }


    public void setThirdPartyProviderList(List<ThirdPartyProvider> thirdPartyProviderList) {

        this.thirdPartyProviderList = thirdPartyProviderList;

    }


}
