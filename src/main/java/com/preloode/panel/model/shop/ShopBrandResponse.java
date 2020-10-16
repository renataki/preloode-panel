package com.preloode.panel.model.shop;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class ShopBrandResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<ShopCategory> shopCategoryList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private ShopBrand shopBrand;

    private List<ShopBrand> shopBrandList;

    private int size;

    private List<Status> statusList;


    public ShopBrandResponse() {


    }


    public ShopBrandResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<ShopCategory> shopCategoryList, List<Company> companyList, Map<String, Object> filter, String link, int page, ShopBrand shopBrand, List<ShopBrand> shopBrandList, int size, List<Status> statusList) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.shopCategoryList = shopCategoryList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.shopBrand = shopBrand;
        this.shopBrandList = shopBrandList;
        this.size = size;
        this.statusList = statusList;

    }


    public List<CompanyBranch> getCompanyBranchList() {

        return companyBranchList;

    }


    public void setCompanyBranchList(List<CompanyBranch> companyBranchList) {

        this.companyBranchList = companyBranchList;

    }


    public List<ShopCategory> getShopCategoryList() {

        return shopCategoryList;

    }


    public void setShopCategoryList(List<ShopCategory> shopCategoryList) {

        this.shopCategoryList = shopCategoryList;

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


    public ShopBrand getShopBrand() {

        return shopBrand;

    }


    public void setShopBrand(ShopBrand shopBrand) {

        this.shopBrand = shopBrand;

    }


    public List<ShopBrand> getShopBrandList() {

        return shopBrandList;

    }


    public void setShopBrandList(List<ShopBrand> shopBrandList) {

        this.shopBrandList = shopBrandList;

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
