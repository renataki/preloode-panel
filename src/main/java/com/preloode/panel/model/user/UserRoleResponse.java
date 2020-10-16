package com.preloode.panel.model.user;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class UserRoleResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private int size;

    private List<Status> statusList;

    private UserRole userRole;

    private List<UserRole> userRoleList;


    public UserRoleResponse() {


    }


    public UserRoleResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, Map<String, Object> filter, String link, int page, int size, List<Status> statusList, UserRole userRole, List<UserRole> userRoleList) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.size = size;
        this.statusList = statusList;
        this.userRole = userRole;
        this.userRoleList = userRoleList;

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


    public UserRole getUserRole() {

        return userRole;

    }


    public void setUserRole(UserRole userRole) {

        this.userRole = userRole;

    }


    public List<UserRole> getUserRoleList() {

        return userRoleList;

    }


    public void setUserRoleList(List<UserRole> userRoleList) {

        this.userRoleList = userRoleList;

    }


}
