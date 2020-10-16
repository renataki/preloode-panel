package com.preloode.panel.model.setting;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.Base;
import com.preloode.panel.model.global.TimestampReference;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigInteger;
import java.util.List;


public class SettingSlider extends Base {


    private String alternative;

    private CompanyListReference company;

    private String description;

    private List<String> imageList;

    @Indexed(unique = true)
    private String name;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private Status status;

    private String title;

    private String url;


    public SettingSlider() {


    }


    public SettingSlider(String id, TimestampReference created, TimestampReference modified, String alternative, CompanyListReference company, String description, List<String> imageList, String name, BigInteger sequence, Status status, String title, String url) {

        super(id, created, modified);
        this.alternative = alternative;
        this.company = company;
        this.description = description;
        this.imageList = imageList;
        this.name = name;
        this.sequence = sequence;
        this.status = status;
        this.title = title;
        this.url = url;

    }


    public String getAlternative() {

        return alternative;

    }


    public void setAlternative(String alternative) {

        this.alternative = alternative;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

    }


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public List<String> getImageList() {

        return imageList;

    }


    public void setImageList(List<String> imageList) {

        this.imageList = imageList;

    }


    public String getName() {

        return name;

    }


    public void setName(String name) {

        this.name = name;

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


    public String getTitle() {

        return title;

    }


    public void setTitle(String title) {

        this.title = title;

    }


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


}
