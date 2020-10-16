package com.preloode.panel.model.crm;

import com.preloode.panel.enumeration.crm.CrmDatabaseStatus;
import com.preloode.panel.enumeration.crm.CrmDatabaseType;
import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.global.Gender;
import com.preloode.panel.enumeration.global.Language;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.ContactReference;
import com.preloode.panel.model.global.NameReference;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;
import java.util.List;


public class CrmDatabaseLogData extends Base {


    private List<BigInteger> attemptList;

    @Indexed
    private String city;

    private CompanyListReference company;

    private ContactReference contact;

    @Indexed
    private Country country;

    @Indexed
    private String crmDatabaseId;

    @Indexed
    private Gender gender;

    @Indexed
    private CrmGroupReference group;

    private Language language;

    private NameReference name;

    private String reference;

    private CrmDatabaseSourceReference source;

    @Indexed
    private String state;

    @Indexed
    private CrmDatabaseStatus status;

    private String streetAddress;

    @Indexed
    private CrmDatabaseType type;

    @Indexed
    private String zipCode;


    public CrmDatabaseLogData() {


    }


    public CrmDatabaseLogData(String id, TimestampReference created, TimestampReference modified, List<BigInteger> attemptList, String city, CompanyListReference company, ContactReference contact, Country country, String crmDatabaseId, Gender gender, CrmGroupReference group, Language language, NameReference name, String reference, CrmDatabaseSourceReference source, String state, CrmDatabaseStatus status, String streetAddress, CrmDatabaseType type, String zipCode) {

        super(id, created, modified);
        this.attemptList = attemptList;
        this.city = city;
        this.company = company;
        this.contact = contact;
        this.country = country;
        this.crmDatabaseId = crmDatabaseId;
        this.gender = gender;
        this.group = group;
        this.language = language;
        this.name = name;
        this.reference = reference;
        this.source = source;
        this.state = state;
        this.status = status;
        this.streetAddress = streetAddress;
        this.type = type;
        this.zipCode = zipCode;

    }


    public List<BigInteger> getAttemptList() {

        return attemptList;

    }


    public void setAttemptList(List<BigInteger> attemptList) {

        this.attemptList = attemptList;

    }


    public String getCity() {

        return city;

    }


    public void setCity(String city) {

        this.city = city;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

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


    public String getCrmDatabaseId() {

        return crmDatabaseId;

    }


    public void setCrmDatabaseId(String crmDatabaseId) {

        this.crmDatabaseId = crmDatabaseId;

    }


    public Gender getGender() {

        return gender;

    }


    public void setGender(Gender gender) {

        this.gender = gender;

    }


    public CrmGroupReference getGroup() {

        return group;

    }


    public void setGroup(CrmGroupReference group) {

        this.group = group;

    }


    public Language getLanguage() {

        return language;

    }


    public void setLanguage(Language language) {

        this.language = language;

    }


    public NameReference getName() {

        return name;

    }


    public void setName(NameReference name) {

        this.name = name;

    }


    public String getReference() {

        return reference;

    }


    public void setReference(String reference) {

        this.reference = reference;

    }


    public CrmDatabaseSourceReference getSource() {

        return source;

    }


    public void setSource(CrmDatabaseSourceReference source) {

        this.source = source;

    }


    public String getState() {

        return state;

    }


    public void setState(String state) {

        this.state = state;

    }


    public CrmDatabaseStatus getStatus() {

        return status;

    }


    public void setStatus(CrmDatabaseStatus status) {

        this.status = status;

    }


    public String getStreetAddress() {

        return streetAddress;

    }


    public void setStreetAddress(String streetAddress) {

        this.streetAddress = streetAddress;

    }


    public CrmDatabaseType getType() {

        return type;

    }


    public void setType(CrmDatabaseType type) {

        this.type = type;

    }


    public String getZipCode() {

        return zipCode;

    }


    public void setZipCode(String zipCode) {

        this.zipCode = zipCode;

    }


}
