package com.preloode.panel.model.user;

import org.springframework.data.mongodb.core.index.Indexed;


public class UserPrivilegeReference {


    @Indexed
    private String blog;

    @Indexed
    private String blogCategory;

    @Indexed
    private String blogPost;

    @Indexed
    private String blogStar;

    @Indexed
    private String company;

    @Indexed
    private String companyBranch;

    @Indexed
    private String crm;

    @Indexed
    private String crmDatabase;

    @Indexed
    private String crmDatabaseSource;

    @Indexed
    private String crmEmail;

    @Indexed
    private String crmEmailBlast;

    @Indexed
    private String crmGroup;

    @Indexed
    private String crmPhone;

    @Indexed
    private String crmPhoneCall;

    @Indexed
    private String crmWhatsapp;

    @Indexed
    private String crmWhatsappBlast;

    @Indexed
    private String cryptocurrency;

    @Indexed
    private String cryptocurrencyArbitrage;

    @Indexed
    private String cryptocurrencyTriangularArbitrage;

    @Indexed
    private String payment;

    @Indexed
    private String paymentAccount;

    @Indexed
    private String paymentMethod;

    @Indexed
    private String setting;

    @Indexed
    private String settingSlider;

    @Indexed
    private String shop;

    @Indexed
    private String shopBrand;

    @Indexed
    private String shopCategory;

    @Indexed
    private String shopProduct;

    @Indexed
    private String thirdParty;

    @Indexed
    private String thirdPartyAccount;

    @Indexed
    private String thirdPartyProvider;

    @Indexed
    private String transaction;

    @Indexed
    private String transactionCart;

    @Indexed
    private String user;

    @Indexed
    private String userGroup;

    @Indexed
    private String userRole;


    public UserPrivilegeReference() {


    }


    public UserPrivilegeReference(String blog, String blogCategory, String blogPost, String blogStar, String company, String companyBranch, String crm, String crmDatabase, String crmDatabaseSource, String crmEmail, String crmEmailBlast, String crmGroup, String crmPhone, String crmPhoneCall, String crmWhatsapp, String crmWhatsappBlast, String cryptocurrency, String cryptocurrencyArbitrage, String cryptocurrencyTriangularArbitrage, String payment, String paymentAccount, String paymentMethod, String setting, String settingSlider, String shop, String shopBrand, String shopCategory, String shopProduct, String thirdParty, String thirdPartyAccount, String thirdPartyProvider, String transaction, String transactionCart, String user, String userGroup, String userRole) {

        this.blog = blog;
        this.blogCategory = blogCategory;
        this.blogPost = blogPost;
        this.blogStar = blogStar;
        this.company = company;
        this.companyBranch = companyBranch;
        this.crm = crm;
        this.crmDatabase = crmDatabase;
        this.crmDatabaseSource = crmDatabaseSource;
        this.crmEmail = crmEmail;
        this.crmEmailBlast = crmEmailBlast;
        this.crmGroup = crmGroup;
        this.crmPhone = crmPhone;
        this.crmPhoneCall = crmPhoneCall;
        this.crmWhatsapp = crmWhatsapp;
        this.crmWhatsappBlast = crmWhatsappBlast;
        this.cryptocurrency = cryptocurrency;
        this.cryptocurrencyArbitrage = cryptocurrencyArbitrage;
        this.cryptocurrencyTriangularArbitrage = cryptocurrencyTriangularArbitrage;
        this.payment = payment;
        this.paymentAccount = paymentAccount;
        this.paymentMethod = paymentMethod;
        this.setting = setting;
        this.settingSlider = settingSlider;
        this.shop = shop;
        this.shopBrand = shopBrand;
        this.shopCategory = shopCategory;
        this.shopProduct = shopProduct;
        this.thirdParty = thirdParty;
        this.thirdPartyAccount = thirdPartyAccount;
        this.thirdPartyProvider = thirdPartyProvider;
        this.transaction = transaction;
        this.transactionCart = transactionCart;
        this.user = user;
        this.userGroup = userGroup;
        this.userRole = userRole;

    }


    public String getBlog() {

        return blog;

    }


    public void setBlog(String blog) {

        this.blog = blog;

    }


    public String getBlogCategory() {

        return blogCategory;

    }


    public void setBlogCategory(String blogCategory) {

        this.blogCategory = blogCategory;

    }


    public String getBlogPost() {

        return blogPost;

    }


    public void setBlogPost(String blogPost) {

        this.blogPost = blogPost;

    }


    public String getBlogStar() {

        return blogStar;

    }


    public void setBlogStar(String blogStar) {

        this.blogStar = blogStar;

    }


    public String getCompany() {

        return company;

    }


    public void setCompany(String company) {

        this.company = company;

    }


    public String getCompanyBranch() {

        return companyBranch;

    }


    public void setCompanyBranch(String companyBranch) {

        this.companyBranch = companyBranch;

    }


    public String getCrm() {

        return crm;

    }


    public void setCrm(String crm) {

        this.crm = crm;

    }


    public String getCrmDatabase() {

        return crmDatabase;

    }


    public void setCrmDatabase(String crmDatabase) {

        this.crmDatabase = crmDatabase;

    }


    public String getCrmDatabaseSource() {

        return crmDatabaseSource;

    }


    public void setCrmDatabaseSource(String crmDatabaseSource) {

        this.crmDatabaseSource = crmDatabaseSource;

    }


    public String getCrmEmail() {

        return crmEmail;

    }


    public void setCrmEmail(String crmEmail) {

        this.crmEmail = crmEmail;

    }


    public String getCrmEmailBlast() {

        return crmEmailBlast;

    }


    public void setCrmEmailBlast(String crmEmailBlast) {

        this.crmEmailBlast = crmEmailBlast;

    }


    public String getCrmGroup() {

        return crmGroup;

    }


    public void setCrmGroup(String crmGroup) {

        this.crmGroup = crmGroup;

    }


    public String getCrmPhone() {

        return crmPhone;

    }


    public void setCrmPhone(String crmPhone) {

        this.crmPhone = crmPhone;

    }


    public String getCrmPhoneCall() {

        return crmPhoneCall;

    }


    public void setCrmPhoneCall(String crmPhoneCall) {

        this.crmPhoneCall = crmPhoneCall;

    }


    public String getCrmWhatsapp() {

        return crmWhatsapp;

    }


    public void setCrmWhatsapp(String crmWhatsapp) {

        this.crmWhatsapp = crmWhatsapp;

    }


    public String getCrmWhatsappBlast() {

        return crmWhatsappBlast;

    }


    public void setCrmWhatsappBlast(String crmWhatsappBlast) {

        this.crmWhatsappBlast = crmWhatsappBlast;

    }


    public String getCryptocurrency() {

        return cryptocurrency;

    }


    public void setCryptocurrency(String cryptocurrency) {

        this.cryptocurrency = cryptocurrency;

    }


    public String getCryptocurrencyArbitrage() {

        return cryptocurrencyArbitrage;

    }


    public void setCryptocurrencyArbitrage(String cryptocurrencyArbitrage) {

        this.cryptocurrencyArbitrage = cryptocurrencyArbitrage;

    }


    public String getCryptocurrencyTriangularArbitrage() {

        return cryptocurrencyTriangularArbitrage;

    }


    public void setCryptocurrencyTriangularArbitrage(String cryptocurrencyTriangularArbitrage) {

        this.cryptocurrencyTriangularArbitrage = cryptocurrencyTriangularArbitrage;

    }


    public String getPayment() {

        return payment;

    }


    public void setPayment(String payment) {

        this.payment = payment;

    }


    public String getPaymentAccount() {

        return paymentAccount;

    }


    public void setPaymentAccount(String paymentAccount) {

        this.paymentAccount = paymentAccount;

    }


    public String getPaymentMethod() {

        return paymentMethod;

    }


    public void setPaymentMethod(String paymentMethod) {

        this.paymentMethod = paymentMethod;

    }


    public String getSetting() {

        return setting;

    }


    public void setSetting(String setting) {

        this.setting = setting;

    }


    public String getSettingSlider() {

        return settingSlider;

    }


    public void setSettingSlider(String settingSlider) {

        this.settingSlider = settingSlider;

    }


    public String getShop() {

        return shop;

    }


    public void setShop(String shop) {

        this.shop = shop;

    }


    public String getShopBrand() {

        return shopBrand;

    }


    public void setShopBrand(String shopBrand) {

        this.shopBrand = shopBrand;

    }


    public String getShopCategory() {

        return shopCategory;

    }


    public void setShopCategory(String shopCategory) {

        this.shopCategory = shopCategory;

    }


    public String getShopProduct() {

        return shopProduct;

    }


    public void setShopProduct(String shopProduct) {

        this.shopProduct = shopProduct;

    }


    public String getThirdParty() {

        return thirdParty;

    }


    public void setThirdParty(String thirdParty) {

        this.thirdParty = thirdParty;

    }


    public String getThirdPartyAccount() {

        return thirdPartyAccount;

    }


    public void setThirdPartyAccount(String thirdPartyAccount) {

        this.thirdPartyAccount = thirdPartyAccount;

    }


    public String getThirdPartyProvider() {

        return thirdPartyProvider;

    }


    public void setThirdPartyProvider(String thirdPartyProvider) {

        this.thirdPartyProvider = thirdPartyProvider;

    }


    public String getTransaction() {

        return transaction;

    }


    public void setTransaction(String transaction) {

        this.transaction = transaction;

    }


    public String getTransactionCart() {

        return transactionCart;

    }


    public void setTransactionCart(String transactionCart) {

        this.transactionCart = transactionCart;

    }


    public String getUser() {

        return user;

    }


    public void setUser(String user) {

        this.user = user;

    }


    public String getUserGroup() {

        return userGroup;

    }


    public void setUserGroup(String userGroup) {

        this.userGroup = userGroup;

    }


    public String getUserRole() {

        return userRole;

    }


    public void setUserRole(String userRole) {

        this.userRole = userRole;

    }


}
