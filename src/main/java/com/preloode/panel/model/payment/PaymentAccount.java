package com.preloode.panel.model.payment;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.math.BigInteger;


public class PaymentAccount extends Base {


    @Indexed
    private String branch;

    private CompanyListReference company;

    @Indexed
    private BigDecimal credit;

    private String description;

    private PaymentMethodReference method;

    @Indexed
    private String name;

    @Indexed
    private String number;

    private String password;

    private String pin;

    private String qrCode;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private Status status;

    @Indexed
    private String username;


    public PaymentAccount() {


    }


    public PaymentAccount(String id, TimestampReference created, TimestampReference modified, String branch, CompanyListReference company, BigDecimal credit, String description, PaymentMethodReference method, String name, String number, String password, String pin, String qrCode, BigInteger sequence, Status status, String username) {

        super(id, created, modified);
        this.branch = branch;
        this.company = company;
        this.credit = credit;
        this.description = description;
        this.method = method;
        this.name = name;
        this.number = number;
        this.password = password;
        this.pin = pin;
        this.qrCode = qrCode;
        this.sequence = sequence;
        this.status = status;
        this.username = username;

    }


    public String getBranch() {

        return branch;

    }


    public void setBranch(String branch) {

        this.branch = branch;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

    }


    public BigDecimal getCredit() {

        return credit;

    }


    public void setCredit(BigDecimal credit) {

        this.credit = credit;

    }


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public PaymentMethodReference getMethod() {

        return method;

    }


    public void setMethod(PaymentMethodReference method) {

        this.method = method;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public String getNumber() {

        return number;

    }


    public void setNumber(String number) {

        this.number = number;

    }


    public String getPassword() {

        return password;

    }


    public void setPassword(String password) {

        this.password = password;

    }


    public String getPin() {

        return pin;

    }


    public void setPin(String pin) {

        this.pin = pin;

    }


    public String getQrCode() {

        return qrCode;

    }


    public void setQrCode(String qrCode) {

        this.qrCode = qrCode;

    }


    public BigInteger getSequence() {

        return sequence;

    }


    public void setSequence(BigInteger sequence) {

        this.sequence = sequence;

    }


    public Status getStatus() {

        return status;

    }


    public void setStatus(Status status) {

        this.status = status;

    }


    public String getUsername() {

        return username;

    }


    public void setUsername(String username) {

        this.username = username;

    }


}
