package com.preloode.panel.model.crm;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.UserGroup;

import java.util.List;
import java.util.Map;


public class CrmDatabaseSourceResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private CrmDatabaseSource crmDatabaseSource;

    private List<CrmDatabaseSource> crmDatabaseSourceList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private int size;

    private List<Status> statusList;


    public CrmDatabaseSourceResponse() {



    }


    public CrmDatabaseSourceResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, CrmDatabaseSource crmDatabaseSource, List<CrmDatabaseSource> crmDatabaseSourceList, Map<String, Object> filter, String link, int page, int size, List<Status> statusList) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.crmDatabaseSource = crmDatabaseSource;
        this.crmDatabaseSourceList = crmDatabaseSourceList;
        this.filter = filter;
        this.link = link;
        this.page = page;
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


    public CrmDatabaseSource getCrmDatabaseSource() {

        return crmDatabaseSource;

    }


    public void setCrmDatabaseSource(CrmDatabaseSource crmDatabaseSource) {

        this.crmDatabaseSource = crmDatabaseSource;

    }


    public List<CrmDatabaseSource> getCrmDatabaseSourceList() {

        return crmDatabaseSourceList;

    }


    public void setCrmDatabaseSourceList(List<CrmDatabaseSource> crmDatabaseSourceList) {

        this.crmDatabaseSourceList = crmDatabaseSourceList;

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


}
