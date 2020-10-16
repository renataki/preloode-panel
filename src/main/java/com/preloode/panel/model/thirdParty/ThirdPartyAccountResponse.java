package com.preloode.panel.model.thirdParty;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class ThirdPartyAccountResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private List<ThirdPartyProvider> thirdPartyProviderList;

    private ThirdPartyAccount thirdPartyAccount;

    private List<ThirdPartyAccount> thirdPartyAccountList;

    private int size;

    private List<Status> statusList;


    public ThirdPartyAccountResponse() {


    }


    public ThirdPartyAccountResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, Map<String, Object> filter, String link, int page, List<ThirdPartyProvider> thirdPartyProviderList, ThirdPartyAccount thirdPartyAccount, List<ThirdPartyAccount> thirdPartyAccountList, int size, List<Status> statusList) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.thirdPartyProviderList = thirdPartyProviderList;
        this.thirdPartyAccount = thirdPartyAccount;
        this.thirdPartyAccountList = thirdPartyAccountList;
        this.size = size;
        this.statusList = statusList;

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


    public List<ThirdPartyProvider> getThirdPartyProviderList() {

        return thirdPartyProviderList;

    }


    public void setThirdPartyProviderList(List<ThirdPartyProvider> thirdPartyProviderList) {

        this.thirdPartyProviderList = thirdPartyProviderList;

    }


    public ThirdPartyAccount getThirdPartyAccount() {

        return thirdPartyAccount;

    }


    public void setThirdPartyAccount(ThirdPartyAccount thirdPartyAccount) {

        this.thirdPartyAccount = thirdPartyAccount;

    }


    public List<ThirdPartyAccount> getThirdPartyAccountList() {

        return thirdPartyAccountList;

    }


    public void setThirdPartyAccountList(List<ThirdPartyAccount> thirdPartyAccountList) {

        this.thirdPartyAccountList = thirdPartyAccountList;

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


}
