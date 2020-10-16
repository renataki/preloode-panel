package com.preloode.panel.model.company;

import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.setting.ApplicationStatus;
import com.preloode.panel.model.global.*;
import com.preloode.panel.model.setting.SettingActivationReference;
import com.preloode.panel.model.setting.SettingMaintenanceReference;
import com.preloode.panel.model.setting.SettingTransactionReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;


public class Company extends Base {


    private SettingActivationReference activation;

    private SettingTransactionReference addToCart;

    private SettingTransactionReference adjustment;

    private SettingTransactionReference checkout;

    @Indexed
    private String city;

    private ContactReference contact;

    @Indexed
    private Country country;

    private SettingTransactionReference deposit;

    private String description;

    private SettingTransactionReference expense;

    private String favicon;

    private SettingTransactionReference fee;

    private SettingTransactionReference internal;

    private SwitchReference like;

    private String logo;

    private SettingMaintenanceReference maintenance;

    private MetaReference meta;

    @Indexed(unique = true)
    private String name;

    private OgReference og;

    private SwitchReference rating;

    @Indexed
    private String registrationNumber;

    private SwitchReference review;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private String state;

    @Indexed
    private ApplicationStatus status;

    private String streetAddress;

    private SettingTransactionReference transfer;

    private SettingTransactionReference withdrawal;

    @Indexed
    private String zipCode;


    public Company() {


    }


    public Company(String id, TimestampReference created, TimestampReference modified, SettingActivationReference activation, SettingTransactionReference addToCart, SettingTransactionReference adjustment, SettingTransactionReference checkout, String city, ContactReference contact, Country country, SettingTransactionReference deposit, String description, SettingTransactionReference expense, String favicon, SettingTransactionReference fee, SettingTransactionReference internal, SwitchReference like, String logo, SettingMaintenanceReference maintenance, MetaReference meta, String name, OgReference og, SwitchReference rating, String registrationNumber, SwitchReference review, BigInteger sequence, String state, ApplicationStatus status, String streetAddress, SettingTransactionReference transfer, SettingTransactionReference withdrawal, String zipCode) {

        super(id, created, modified);
        this.activation = activation;
        this.addToCart = addToCart;
        this.adjustment = adjustment;
        this.checkout = checkout;
        this.city = city;
        this.contact = contact;
        this.country = country;
        this.deposit = deposit;
        this.description = description;
        this.expense = expense;
        this.favicon = favicon;
        this.fee = fee;
        this.internal = internal;
        this.like = like;
        this.logo = logo;
        this.maintenance = maintenance;
        this.meta = meta;
        this.name = name;
        this.og = og;
        this.rating = rating;
        this.registrationNumber = registrationNumber;
        this.review = review;
        this.sequence = sequence;
        this.state = state;
        this.status = status;
        this.streetAddress = streetAddress;
        this.transfer = transfer;
        this.withdrawal = withdrawal;
        this.zipCode = zipCode;

    }


    public SettingActivationReference getActivation() {

        return activation;

    }


    public void setActivation(SettingActivationReference activation) {

        this.activation = activation;

    }


    public SettingTransactionReference getAddToCart() {

        return addToCart;

    }


    public void setAddToCart(SettingTransactionReference addToCart) {

        this.addToCart = addToCart;

    }


    public SettingTransactionReference getAdjustment() {

        return adjustment;

    }


    public void setAdjustment(SettingTransactionReference adjustment) {

        this.adjustment = adjustment;

    }


    public SettingTransactionReference getCheckout() {

        return checkout;

    }


    public void setCheckout(SettingTransactionReference checkout) {

        this.checkout = checkout;

    }


    public String getCity() {

        return city;

    }


    public void setCity(String city) {

        this.city = city;

    }


    public ContactReference getContact() {

        return contact;

    }


    public void setContact(ContactReference contact) {

        this.contact = contact;

    }


    public Country getCountry() {

        return country;

    }


    public void setCountry(Country country) {

        this.country = country;

    }


    public SettingTransactionReference getDeposit() {

        return deposit;

    }


    public void setDeposit(SettingTransactionReference deposit) {

        this.deposit = deposit;

    }


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public SettingTransactionReference getExpense() {

        return expense;

    }


    public void setExpense(SettingTransactionReference expense) {

        this.expense = expense;

    }


    public String getFavicon() {

        return favicon;

    }


    public void setFavicon(String favicon) {

        this.favicon = favicon;

    }


    public SettingTransactionReference getFee() {

        return fee;

    }


    public void setFee(SettingTransactionReference fee) {

        this.fee = fee;

    }


    public SettingTransactionReference getInternal() {

        return internal;

    }


    public void setInternal(SettingTransactionReference internal) {

        this.internal = internal;

    }


    public SwitchReference getLike() {

        return like;

    }


    public void setLike(SwitchReference like) {

        this.like = like;

    }


    public String getLogo() {

        return logo;

    }


    public void setLogo(String logo) {

        this.logo = logo;

    }


    public SettingMaintenanceReference getMaintenance() {

        return maintenance;

    }


    public void setMaintenance(SettingMaintenanceReference maintenance) {

        this.maintenance = maintenance;

    }


    public MetaReference getMeta() {

        return meta;

    }


    public void setMeta(MetaReference meta) {

        this.meta = meta;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public OgReference getOg() {

        return og;

    }


    public void setOg(OgReference og) {

        this.og = og;

    }


    public SwitchReference getRating() {

        return rating;

    }


    public void setRating(SwitchReference rating) {

        this.rating = rating;

    }


    public String getRegistrationNumber() {

        return registrationNumber;

    }


    public void setRegistrationNumber(String registrationNumber) {

        this.registrationNumber = registrationNumber;

    }


    public SwitchReference getReview() {

        return review;

    }


    public void setReview(SwitchReference review) {

        this.review = review;

    }


    public BigInteger getSequence() {

        return sequence;

    }


    public void setSequence(BigInteger sequence) {

        this.sequence = sequence;

    }


    public String getState() {

        return state;

    }


    public void setState(String state) {

        this.state = state;

    }


    public ApplicationStatus getStatus() {

        return status;

    }


    public void setStatus(ApplicationStatus status) {

        this.status = status;

    }


    public String getStreetAddress() {

        return streetAddress;

    }


    public void setStreetAddress(String streetAddress) {

        this.streetAddress = streetAddress;

    }


    public SettingTransactionReference getTransfer() {

        return transfer;

    }


    public void setTransfer(SettingTransactionReference transfer) {

        this.transfer = transfer;

    }


    public SettingTransactionReference getWithdrawal() {

        return withdrawal;

    }


    public void setWithdrawal(SettingTransactionReference withdrawal) {

        this.withdrawal = withdrawal;

    }


    public String getZipCode() {

        return zipCode;

    }


    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;

    }


}
