package com.preloode.panel.model.payment;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.transaction.Transaction;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;
import java.util.Map;


public class PaymentAccountResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private List<PaymentMethod> paymentMethodList;

    private int page;

    private PaymentAccount paymentAccount;

    private List<PaymentAccount> paymentAccountList;

    private int size;

    private List<Status> statusList;

    private List<Transaction> transactionList;

    private Workbook workbook;


    public PaymentAccountResponse() {


    }


    public PaymentAccountResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, Map<String, Object> filter, String link, List<PaymentMethod> paymentMethodList, int page, PaymentAccount paymentAccount, List<PaymentAccount> paymentAccountList, int size, List<Status> statusList, List<Transaction> transactionList, Workbook workbook) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.paymentMethodList = paymentMethodList;
        this.page = page;
        this.paymentAccount = paymentAccount;
        this.paymentAccountList = paymentAccountList;
        this.size = size;
        this.statusList = statusList;
        this.transactionList = transactionList;
        this.workbook = workbook;

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


    public List<PaymentMethod> getPaymentMethodList() {

        return paymentMethodList;

    }


    public void setPaymentMethodList(List<PaymentMethod> paymentMethodList) {

        this.paymentMethodList = paymentMethodList;

    }


    public int getPage() {

        return page;

    }


    public void setPage(int page) {

        this.page = page;

    }


    public PaymentAccount getPaymentAccount() {

        return paymentAccount;

    }


    public void setPaymentAccount(PaymentAccount paymentAccount) {

        this.paymentAccount = paymentAccount;

    }


    public List<PaymentAccount> getPaymentAccountList() {

        return paymentAccountList;

    }


    public void setPaymentAccountList(List<PaymentAccount> paymentAccountList) {

        this.paymentAccountList = paymentAccountList;

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


    public List<Transaction> getTransactionList() {

        return transactionList;

    }


    public void setTransactionList(List<Transaction> transactionList) {

        this.transactionList = transactionList;

    }


    public Workbook getWorkbook() {

        return workbook;

    }


    public void setWorkbook(Workbook workbook) {

        this.workbook = workbook;

    }


}
