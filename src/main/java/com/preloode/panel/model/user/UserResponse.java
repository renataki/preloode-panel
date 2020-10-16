package com.preloode.panel.model.user;

import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.global.Gender;
import com.preloode.panel.enumeration.global.Language;
import com.preloode.panel.enumeration.user.UserStatus;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.payment.PaymentMethod;
import com.preloode.panel.model.thirdParty.ThirdPartyAccount;
import com.preloode.panel.model.thirdParty.ThirdPartyProvider;

import java.util.List;
import java.util.Map;


public class UserResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private List<String> countryCodeList;

    private List<Country> countryList;

    private Map<String, Object> filter;

    private List<Gender> genderList;

    private List<Language> languageList;

    private String link;

    private int page;

    private List<PaymentMethod> paymentMethodList;

    private int size;

    private List<ThirdPartyAccount> thirdPartyAccountList;

    private List<ThirdPartyProvider> thirdPartyProviderList;

    private User user;

    private List<UserGroup> userGroupList;

    private List<User> userList;

    private UserRole userRole;

    private List<UserRole> userRoleList;

    private List<UserStatus> userStatusList;

    private List<UserType> userTypeList;


    public UserResponse() {


    }


    public UserResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, List<String> countryCodeList, List<Country> countryList, Map<String, Object> filter, List<Gender> genderList, List<Language> languageList, String link, int page, List<PaymentMethod> paymentMethodList, UserRole userRole, List<UserRole> userRoleList, int size, List<ThirdPartyAccount> thirdPartyAccountList, List<ThirdPartyProvider> thirdPartyProviderList, User user, List<UserGroup> userGroupList, List<User> userList, List<UserStatus> userStatusList, List<UserType> userTypeList) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.countryCodeList = countryCodeList;
        this.countryList = countryList;
        this.filter = filter;
        this.genderList = genderList;
        this.languageList = languageList;
        this.link = link;
        this.page = page;
        this.paymentMethodList = paymentMethodList;
        this.userRole = userRole;
        this.userRoleList = userRoleList;
        this.size = size;
        this.thirdPartyAccountList = thirdPartyAccountList;
        this.thirdPartyProviderList = thirdPartyProviderList;
        this.user = user;
        this.userGroupList = userGroupList;
        this.userList = userList;
        this.userStatusList = userStatusList;
        this.userTypeList = userTypeList;

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


    public String getLink() {

        return link;

    }


    public void setLink(String link) {

        this.link = link;

    }


    public List<Language> getLanguageList() {

        return languageList;

    }


    public void setLanguageList(List<Language> languageList) {

        this.languageList = languageList;

    }


    public int getPage() {

        return page;

    }


    public void setPage(int page) {

        this.page = page;

    }


    public List<PaymentMethod> getPaymentMethodList() {

        return paymentMethodList;

    }


    public void setPaymentMethodList(List<PaymentMethod> paymentMethodList) {

        this.paymentMethodList = paymentMethodList;

    }


    public UserRole getUserRole() {

        return userRole;

    }


    public void setUserRole(UserRole userRole) {

        this.userRole = userRole;

    }


    public int getSize() {

        return size;

    }


    public void setSize(int size) {

        this.size = size;

    }


    public List<ThirdPartyAccount> getThirdPartyAccountList() {

        return thirdPartyAccountList;

    }


    public void setThirdPartyAccountList(List<ThirdPartyAccount> thirdPartyAccountList) {

        this.thirdPartyAccountList = thirdPartyAccountList;

    }


    public List<ThirdPartyProvider> getThirdPartyProviderList() {

        return thirdPartyProviderList;

    }


    public void setThirdPartyProviderList(List<ThirdPartyProvider> thirdPartyProviderList) {

        this.thirdPartyProviderList = thirdPartyProviderList;

    }


    public User getUser() {

        return user;

    }


    public void setUser(User user) {

        this.user = user;

    }


    public List<UserGroup> getUserGroupList() {

        return userGroupList;

    }


    public void setUserGroupList(List<UserGroup> userGroupList) {

        this.userGroupList = userGroupList;

    }


    public List<User> getUserList() {

        return userList;

    }


    public void setUserList(List<User> userList) {

        this.userList = userList;

    }


    public List<UserRole> getUserRoleList() {

        return userRoleList;

    }


    public void setUserRoleList(List<UserRole> userRoleList) {

        this.userRoleList = userRoleList;

    }


    public List<UserStatus> getUserStatusList() {

        return userStatusList;

    }


    public void setUserStatusList(List<UserStatus> userStatusList) {

        this.userStatusList = userStatusList;

    }


    public List<UserType> getUserTypeList() {

        return userTypeList;

    }


    public void setUserTypeList(List<UserType> userTypeList) {

        this.userTypeList = userTypeList;

    }


}
