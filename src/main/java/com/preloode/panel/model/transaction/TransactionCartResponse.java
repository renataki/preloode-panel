package com.preloode.panel.model.transaction;

import com.preloode.panel.enumeration.shop.ProductType;
import com.preloode.panel.enumeration.transaction.AdjustmentType;
import com.preloode.panel.enumeration.transaction.TransactionStatus;
import com.preloode.panel.enumeration.transaction.TransactionType;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.payment.PaymentAccount;
import com.preloode.panel.model.payment.PaymentMethod;
import com.preloode.panel.model.user.User;

import java.util.List;
import java.util.Map;


public class TransactionCartResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private List<ProductType> productTypeList;

    private int size;

    private TransactionCart transactionCart;

    private List<TransactionCart> transactionCartList;

    private List<TransactionStatus> transactionStatusList;

    private User user;

    private List<User> userList;


    public TransactionCartResponse() {



    }


    public TransactionCartResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, Map<String, Object> filter, String link, int page, List<ProductType> productTypeList, int size, TransactionCart transactionCart, List<TransactionCart> transactionCartList, List<TransactionStatus> transactionStatusList, User user, List<User> userList) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.productTypeList = productTypeList;
        this.size = size;
        this.transactionCart = transactionCart;
        this.transactionCartList = transactionCartList;
        this.transactionStatusList = transactionStatusList;
        this.user = user;
        this.userList = userList;

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


    public List<ProductType> getProductTypeList() {

        return productTypeList;

    }


    public void setProductTypeList(List<ProductType> productTypeList) {

        this.productTypeList = productTypeList;

    }


    public int getSize() {

        return size;

    }


    public void setSize(int size) {

        this.size = size;

    }


    public TransactionCart getTransactionCart() {

        return transactionCart;

    }


    public void setTransactionCart(TransactionCart transactionCart) {

        this.transactionCart = transactionCart;

    }


    public List<TransactionCart> getTransactionCartList() {

        return transactionCartList;

    }


    public void setTransactionCartList(List<TransactionCart> transactionCartList) {

        this.transactionCartList = transactionCartList;

    }


    public List<TransactionStatus> getTransactionStatusList() {

        return transactionStatusList;

    }


    public void setTransactionStatusList(List<TransactionStatus> transactionStatusList) {

        this.transactionStatusList = transactionStatusList;

    }


    public User getUser() {

        return user;

    }


    public void setUser(User user) {

        this.user = user;

    }


    public List<User> getUserList() {

        return userList;

    }


    public void setUserList(List<User> userList) {

        this.userList = userList;

    }


}
