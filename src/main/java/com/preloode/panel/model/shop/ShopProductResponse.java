package com.preloode.panel.model.shop;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.shop.PriceRecurring;
import com.preloode.panel.enumeration.shop.ProductType;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class ShopProductResponse extends BaseResponse {


    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private List<PriceRecurring> priceRecurringList;

    private List<ProductType> productTypeList;

    private List<ShopBrand> shopBrandList;

    private List<ShopCategory> shopCategoryList;

    private ShopProduct shopProduct;

    private List<ShopProduct> shopProductList;

    private int size;

    private List<Status> statusList;


    public ShopProductResponse() {


    }


    public ShopProductResponse(String response, boolean result, List<CompanyBranch> companyBranchList, List<Company> companyList, Map<String, Object> filter, String link, int page, List<PriceRecurring> priceRecurringList, List<ProductType> productTypeList, List<ShopBrand> shopBrandList, List<ShopCategory> shopCategoryList, ShopProduct shopProduct, List<ShopProduct> shopProductList, int size, List<Status> statusList) {

        super(response, result);
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.priceRecurringList = priceRecurringList;
        this.productTypeList = productTypeList;
        this.shopBrandList = shopBrandList;
        this.shopCategoryList = shopCategoryList;
        this.shopProduct = shopProduct;
        this.shopProductList = shopProductList;
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


    public List<PriceRecurring> getPriceRecurringList() {

        return priceRecurringList;

    }


    public void setPriceRecurringList(List<PriceRecurring> priceRecurringList) {

        this.priceRecurringList = priceRecurringList;

    }


    public List<ProductType> getProductTypeList() {

        return productTypeList;

    }


    public void setProductTypeList(List<ProductType> productTypeList) {

        this.productTypeList = productTypeList;

    }


    public List<ShopBrand> getShopBrandList() {

        return shopBrandList;

    }


    public void setShopBrandList(List<ShopBrand> shopBrandList) {

        this.shopBrandList = shopBrandList;

    }


    public List<ShopCategory> getShopCategoryList() {

        return shopCategoryList;

    }


    public void setShopCategoryList(List<ShopCategory> shopCategoryList) {

        this.shopCategoryList = shopCategoryList;

    }


    public ShopProduct getShopProduct() {

        return shopProduct;

    }


    public void setShopProduct(ShopProduct shopProduct) {

        this.shopProduct = shopProduct;

    }


    public List<ShopProduct> getShopProductList() {

        return shopProductList;

    }


    public void setShopProductList(List<ShopProduct> shopProductList) {

        this.shopProductList = shopProductList;

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
