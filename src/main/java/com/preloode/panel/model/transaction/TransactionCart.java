package com.preloode.panel.model.transaction;

import com.preloode.panel.enumeration.shop.ProductType;
import com.preloode.panel.enumeration.transaction.TransactionStatus;
import com.preloode.panel.model.company.CompanyBranchListReference;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.CreditReference;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.util.List;


public class TransactionCart extends Base {


    private TransactionActionReference action;

    private TransactionAdditionalCostReference additionalCost;

    private CreditReference amount;

    private CompanyBranchListReference branch;

    private CompanyListReference company;

    private List<String> imageList;

    private TransactionShopProductReference product;

    private String reference;

    @Indexed
    private TransactionStatus status;

    @Indexed
    private ProductType type;


    public TransactionCart() {


    }


    public TransactionCart(String id, TimestampReference created, TimestampReference modified, TransactionActionReference action, TransactionAdditionalCostReference additionalCost, CreditReference amount, CompanyBranchListReference branch, CompanyListReference company, List<String> imageList, TransactionShopProductReference product, String reference) {

        super(id, created, modified);
        this.action = action;
        this.additionalCost = additionalCost;
        this.amount = amount;
        this.branch = branch;
        this.company = company;
        this.imageList = imageList;
        this.product = product;
        this.reference = reference;

    }


    public TransactionActionReference getAction() {

        return action;

    }


    public void setAction(TransactionActionReference action) {

        this.action = action;

    }


    public TransactionAdditionalCostReference getAdditionalCost() {

        return additionalCost;

    }


    public void setAdditionalCost(TransactionAdditionalCostReference additionalCost) {

        this.additionalCost = additionalCost;

    }


    public CreditReference getAmount() {

        return amount;

    }


    public void setAmount(CreditReference amount) {

        this.amount = amount;

    }


    public CompanyBranchListReference getBranch() {

        return branch;

    }


    public void setBranch(CompanyBranchListReference branch) {

        this.branch = branch;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

    }


    public List<String> getImageList() {

        return imageList;

    }


    public void setImageList(List<String> imageList) {

        this.imageList = imageList;

    }


    public TransactionShopProductReference getProduct() {

        return product;

    }


    public void setProduct(TransactionShopProductReference product) {

        this.product = product;

    }


    public String getReference() {

        return reference;

    }


    public void setReference(String reference) {

        this.reference = reference;

    }


}
