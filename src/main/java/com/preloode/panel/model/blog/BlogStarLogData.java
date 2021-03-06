package com.preloode.panel.model.blog;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


public class BlogStarLogData extends Base {


    @Indexed
    private String blogStarId;

    private CategoryMultipleReference category;

    private CompanyListReference company;

    private String content;

    private String description;

    @Indexed
    private BigInteger dislike;

    private List<String> imageList;

    @Indexed
    private BigInteger like;

    private MetaReference meta;

    @Indexed(unique = true)
    private String name;

    private OgReference og;

    @Indexed
    private String path;

    @Indexed
    private BigDecimal rate;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private Status status;

    private List<String> thumbnailList;

    @Indexed
    private String url;

    @Indexed
    private BigInteger view;


    public BlogStarLogData() {


    }


    public BlogStarLogData(String id, TimestampReference created, TimestampReference modified, String blogStarId, CategoryMultipleReference category, CompanyListReference company, String content, String description, BigInteger dislike, List<String> imageList, BigInteger like, MetaReference meta, String name, OgReference og, String path, BigDecimal rate, BigInteger sequence, Status status, List<String> thumbnailList, String url, BigInteger view) {

        super(id, created, modified);
        this.blogStarId = blogStarId;
        this.category = category;
        this.company = company;
        this.content = content;
        this.description = description;
        this.dislike = dislike;
        this.imageList = imageList;
        this.like = like;
        this.meta = meta;
        this.name = name;
        this.og = og;
        this.path = path;
        this.rate = rate;
        this.sequence = sequence;
        this.status = status;
        this.thumbnailList = thumbnailList;
        this.url = url;
        this.view = view;

    }


    public String getBlogStarId() {

        return blogStarId;

    }


    public void setBlogStarId(String blogStarId) {

        this.blogStarId = blogStarId;

    }


    public CategoryMultipleReference getCategory() {

        return category;

    }


    public void setCategory(CategoryMultipleReference category) {

        this.category = category;

    }


    public CompanyListReference getCompany() {

        return company;

    }


    public void setCompany(CompanyListReference company) {

        this.company = company;

    }


    public String getContent() {

        return content;

    }


    public void setContent(String content) {

        this.content = content;

    }


    public String getDescription() {

        return description;

    }


    public void setDescription(String description) {

        this.description = description;

    }


    public BigInteger getDislike() {

        return dislike;

    }


    public void setDislike(BigInteger dislike) {

        this.dislike = dislike;

    }


    public List<String> getImageList() {

        return imageList;

    }


    public void setImageList(List<String> imageList) {

        this.imageList = imageList;

    }


    public BigInteger getLike() {

        return like;

    }


    public void setLike(BigInteger like) {

        this.like = like;

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


    public String getPath() {

        return path;

    }


    public void setPath(String path) {

        this.path = path;

    }


    public BigDecimal getRate() {

        return rate;

    }


    public void setRate(BigDecimal rate) {

        this.rate = rate;

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


    public List<String> getThumbnailList() {

        return thumbnailList;

    }


    public void setThumbnailList(List<String> thumbnailList) {

        this.thumbnailList = thumbnailList;

    }


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


    public BigInteger getView() {

        return view;

    }


    public void setView(BigInteger view) {

        this.view = view;

    }


}
