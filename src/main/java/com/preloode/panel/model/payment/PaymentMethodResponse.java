package com.preloode.panel.model.payment;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.payment.PaymentType;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class PaymentMethodResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private int size;

    private PaymentMethod paymentMethod;

    private List<PaymentMethod> paymentMethodList;

    private List<Status> statusList;

    private List<PaymentType> paymentTypeList;


    public PaymentMethodResponse() {


    }


    public PaymentMethodResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, Map<String, Object> filter, String link, int page, int size, PaymentMethod paymentMethod, List<PaymentMethod> paymentMethodList, List<Status> statusList, List<PaymentType> paymentTypeList) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.size = size;
        this.paymentMethod = paymentMethod;
        this.paymentMethodList = paymentMethodList;
        this.statusList = statusList;
        this.paymentTypeList = paymentTypeList;

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


    public PaymentMethod getPaymentMethod() {

        return paymentMethod;

    }


    public void setPaymentMethod(PaymentMethod paymentMethod) {

        this.paymentMethod = paymentMethod;

    }


    public List<PaymentMethod> getPaymentMethodList() {

        return paymentMethodList;

    }


    public void setPaymentMethodList(List<PaymentMethod> paymentMethodList) {

        this.paymentMethodList = paymentMethodList;

    }


    public List<Status> getStatusList() {

        return statusList;

    }


    public void setStatusList(List<Status> statusList) {

        this.statusList = statusList;

    }


    public List<PaymentType> getPaymentTypeList() {

        return paymentTypeList;

    }


    public void setPaymentTypeList(List<PaymentType> paymentTypeList) {

        this.paymentTypeList = paymentTypeList;

    }


}
