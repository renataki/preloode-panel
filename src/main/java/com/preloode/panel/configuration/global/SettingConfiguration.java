package com.preloode.panel.configuration.global;

import com.preloode.panel.configuration.data.SettingCryptocurrencyConfiguration;
import com.preloode.panel.configuration.data.SettingMetaConfiguration;
import com.preloode.panel.configuration.layout.SettingLayoutConfiguration;
import com.preloode.panel.model.global.ContactReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SettingConfiguration {


    @Value("${setting.author}")
    private String author;

    @Value("${setting.canonical}")
    private String canonical;

    @Value("${setting.company}")
    private String company;

    @Value("${setting.company-branch}")
    private String companyBranch;

    private ContactReference contact;

    private String content;

    @Autowired
    private SettingCryptocurrencyConfiguration cryptocurrency;

    private List<String> css;

    private String favicon;

    private List<String> javascript;

    @Autowired
    private SettingLayoutConfiguration layout;

    private String logo;

    @Autowired
    private SettingMetaConfiguration meta;

    @Value("${setting.name}")
    private String name;

    @Autowired
    private SettingPathConfiguration path;

    @Value("${setting.publisher}")
    private String publisher;

    @Autowired
    private SettingUrlConfiguration url;


    public String getAuthor() {

        return author;

    }


    public void setAuthor(String author) {

        this.author = author;

    }


    public String getCanonical() {

        return canonical;

    }


    public void setCanonical(String canonical) {

        this.canonical = canonical;

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


    public ContactReference getContact() {

        return contact;

    }


    public void setContact(ContactReference contact) {

        this.contact = contact;

    }


    public String getContent() {

        return content;

    }


    public void setContent(String content) {

        this.content = content;

    }


    public SettingCryptocurrencyConfiguration getCryptocurrency() {

        return cryptocurrency;

    }


    public void setCryptocurrency(SettingCryptocurrencyConfiguration cryptocurrency) {

        this.cryptocurrency = cryptocurrency;

    }


    public List<String> getCss() {

        return css;

    }


    public void setCss(List<String> css) {

        this.css = css;

    }


    public String getFavicon() {

        return favicon;

    }


    public void setFavicon(String favicon) {

        this.favicon = favicon;

    }


    public List<String> getJavascript() {

        return javascript;

    }


    public void setJavascript(List<String> javascript) {

        this.javascript = javascript;

    }


    public SettingLayoutConfiguration getLayout() {

        return layout;

    }


    public void setLayout(SettingLayoutConfiguration layout) {

        this.layout = layout;

    }


    public String getLogo() {

        return logo;

    }


    public void setLogo(String logo) {

        this.logo = logo;

    }


    public SettingMetaConfiguration getMeta() {

        return meta;

    }


    public void setMeta(SettingMetaConfiguration meta) {

        this.meta = meta;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

    }


    public SettingPathConfiguration getPath() {

        return path;

    }


    public void setPath(SettingPathConfiguration path) {

        this.path = path;

    }


    public String getPublisher() {

        return publisher;

    }


    public void setPublisher(String publisher) {

        this.publisher = publisher;

    }


    public SettingUrlConfiguration getUrl() {

        return url;

    }


    public void setUrl(SettingUrlConfiguration url) {

        this.url = url;

    }


}
