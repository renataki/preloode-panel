package com.preloode.panel.model.transaction;

import com.preloode.panel.enumeration.transaction.TransactionStatus;
import com.preloode.panel.enumeration.transaction.TransactionType;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.CreditReference;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;


public class Transaction extends Base {


    private TransactionActionReference action;

    private TransactionAdditionalCostReference additionalCost;

    private CreditReference amount;

    @Indexed
    private boolean archive;

    private TransactionCompanyReference company;

    private TransactionSourceReference from;

    private List<String> imageList;

    private TransactionShopProductReference product;

    @Indexed
    private boolean recurring;

    private String reference;

    @Indexed
    private TransactionStatus status;

    @Indexed
    private String ticketNumber;

    private TransactionSourceReference to;

    @Indexed
    private TransactionType type;


    public Transaction() {


    }


    public Transaction(String id, TimestampReference created, TimestampReference modified, TransactionActionReference action, TransactionAdditionalCostReference additionalCost, CreditReference amount, boolean archive, TransactionCompanyReference company, TransactionSourceReference from, List<String> imageList, TransactionShopProductReference product, boolean recurring, String reference, TransactionStatus status, String ticketNumber, TransactionSourceReference to, TransactionType type) {

        super(id, created, modified);
        this.action = action;
        this.additionalCost = additionalCost;
        this.amount = amount;
        this.archive = archive;
        this.company = company;
        this.from = from;
        this.imageList = imageList;
        this.product = product;
        this.recurring = recurring;
        this.reference = reference;
        this.status = status;
        this.ticketNumber = ticketNumber;
        this.to = to;
        this.type = type;

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


    public boolean isArchive() {

        return archive;

    }


    public void setArchive(boolean archive) {

        this.archive = archive;

    }


    public TransactionCompanyReference getCompany() {

        return company;

    }


    public void setCompany(TransactionCompanyReference company) {

        this.company = company;

    }


    public TransactionSourceReference getFrom() {

        return from;

    }


    public void setFrom(TransactionSourceReference from) {

        this.from = from;

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


    public boolean isRecurring() {

        return recurring;

    }


    public void setRecurring(boolean recurring) {

        this.recurring = recurring;

    }


    public String getReference() {

        return reference;

    }


    public void setReference(String reference) {

        this.reference = reference;

    }


    public TransactionStatus getStatus() {

        return status;

    }


    public void setStatus(TransactionStatus status) {

        this.status = status;

    }


    public String getTicketNumber() {

        return ticketNumber;

    }


    public void setTicketNumber(String ticketNumber) {

        this.ticketNumber = ticketNumber;

    }


    public TransactionSourceReference getTo() {

        return to;

    }


    public void setTo(TransactionSourceReference to) {

        this.to = to;

    }


    public TransactionType getType() {

        return type;

    }


    public void setType(TransactionType type) {

        this.type = type;

    }


}
