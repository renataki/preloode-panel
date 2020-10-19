package com.preloode.panel.model.transaction;

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


public class TransactionResponse extends BaseResponse {


    private List<AdjustmentType> adjustmentTypeList;

    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private List<PaymentAccount> paymentAccountList;

    private List<PaymentMethod> paymentMethodList;

    private int size;

    private Transaction transaction;

    private List<Transaction> transactionList;

    private List<TransactionStatus> transactionStatusList;

    private TransactionSummaryReference transactionSummary;

    private List<TransactionType> transactionTypeList;

    private User user;

    private List<User> userList;


    public TransactionResponse() {


    }


    public TransactionResponse(String response, boolean result, List<AdjustmentType> adjustmentTypeList, List<CompanyBranch> companyBranchList, List<Company> companyList, Map<String, Object> filter, String link, int page, List<PaymentAccount> paymentAccountList, List<PaymentMethod> paymentMethodList, int size, Transaction transaction, List<Transaction> transactionList, List<TransactionStatus> transactionStatusList, TransactionSummaryReference transactionSummary, List<TransactionType> transactionTypeList, User user, List<User> userList) {

        super(response, result);
        this.adjustmentTypeList = adjustmentTypeList;
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.paymentAccountList = paymentAccountList;
        this.paymentMethodList = paymentMethodList;
        this.size = size;
        this.transaction = transaction;
        this.transactionList = transactionList;
        this.transactionStatusList = transactionStatusList;
        this.transactionSummary = transactionSummary;
        this.transactionTypeList = transactionTypeList;
        this.user = user;
        this.userList = userList;

    }


    public List<AdjustmentType> getAdjustmentTypeList() {

        return adjustmentTypeList;

    }


    public void setAdjustmentTypeList(List<AdjustmentType> adjustmentTypeList) {

        this.adjustmentTypeList = adjustmentTypeList;

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


    public List<PaymentAccount> getPaymentAccountList() {

        return paymentAccountList;

    }


    public void setPaymentAccountList(List<PaymentAccount> paymentAccountList) {

        this.paymentAccountList = paymentAccountList;

    }


    public List<PaymentMethod> getPaymentMethodList() {

        return paymentMethodList;

    }


    public void setPaymentMethodList(List<PaymentMethod> paymentMethodList) {

        this.paymentMethodList = paymentMethodList;

    }


    public int getSize() {

        return size;

    }


    public void setSize(int size) {

        this.size = size;

    }


    public Transaction getTransaction() {

        return transaction;

    }


    public void setTransaction(Transaction transaction) {

        this.transaction = transaction;

    }


    public List<Transaction> getTransactionList() {

        return transactionList;

    }


    public void setTransactionList(List<Transaction> transactionList) {

        this.transactionList = transactionList;

    }


    public List<TransactionStatus> getTransactionStatusList() {

        return transactionStatusList;

    }


    public void setTransactionStatusList(List<TransactionStatus> transactionStatusList) {

        this.transactionStatusList = transactionStatusList;

    }


    public TransactionSummaryReference getTransactionSummary() {

        return transactionSummary;

    }


    public void setTransactionSummary(TransactionSummaryReference transactionSummary) {

        this.transactionSummary = transactionSummary;

    }


    public List<TransactionType> getTransactionTypeList() {

        return transactionTypeList;

    }


    public void setTransactionTypeList(List<TransactionType> transactionTypeList) {

        this.transactionTypeList = transactionTypeList;

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
