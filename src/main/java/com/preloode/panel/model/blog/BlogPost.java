package com.preloode.panel.model.blog;

import com.preloode.panel.enumeration.blog.BlogType;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


public class BlogPost extends Base {


    private CategoryMultipleReference category;

    private CompanyListReference company;

    private String content;

    private String description;

    @Indexed
    private BigInteger dislike;

    private boolean featured;

    private List<String> imageList;

    @Indexed
    private BigInteger like;

    private MetaReference meta;

    private OgReference og;

    private List<String> pathList;

    @Indexed
    private BigDecimal rate;

    @Indexed
    private BigInteger sequence;

    private BlogStarReference star;

    @Indexed
    private Status status;

    private List<String> tagList;

    private List<String> thumbnailList;

    @Indexed
    private String title;

    @Indexed
    private BlogType type;

    @Indexed
    private String url;

    private List<String> videoList;

    @Indexed
    private BigInteger view;


    public BlogPost() {


    }


    public BlogPost(String id, TimestampReference created, TimestampReference modified, CategoryMultipleReference category, CompanyListReference company, String content, String description, BigInteger dislike, boolean featured, List<String> imageList, BigInteger like, MetaReference meta, OgReference og, List<String> pathList, BigDecimal rate, BigInteger sequence, BlogStarReference star, Status status, List<String> tagList, List<String> thumbnailList, String title, BlogType type, String url, List<String> videoList, BigInteger view) {

        super(id, created, modified);
        this.category = category;
        this.company = company;
        this.content = content;
        this.description = description;
        this.dislike = dislike;
        this.featured = featured;
        this.imageList = imageList;
        this.like = like;
        this.meta = meta;
        this.og = og;
        this.pathList = pathList;
        this.rate = rate;
        this.sequence = sequence;
        this.star = star;
        this.status = status;
        this.tagList = tagList;
        this.thumbnailList = thumbnailList;
        this.title = title;
        this.type = type;
        this.url = url;
        this.videoList = videoList;
        this.view = view;

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


    public boolean isFeatured() {

        return featured;

    }


    public void setFeatured(boolean featured) {

        this.featured = featured;

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


    public OgReference getOg() {

        return og;

    }


    public void setOg(OgReference og) {

        this.og = og;

    }


    public List<String> getPathList() {

        return pathList;

    }


    public void setPathList(List<String> pathList) {

        this.pathList = pathList;

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


    public BlogStarReference getStar() {

        return star;

    }


    public void setStar(BlogStarReference star) {

        this.star = star;

    }


    public Status getStatus() {

        return status;

    }


    public void setStatus(Status status) {

        this.status = status;

    }


    public List<String> getTagList() {

        return tagList;

    }


    public void setTagList(List<String> tagList) {

        this.tagList = tagList;

    }


    public List<String> getThumbnailList() {

        return thumbnailList;

    }


    public void setThumbnailList(List<String> thumbnailList) {

        this.thumbnailList = thumbnailList;

    }


    public String getTitle() {

        return title;

    }


    public void setTitle(String title) {

        this.title = title;

    }


    public BlogType getType() {

        return type;

    }


    public void setType(BlogType type) {

        this.type = type;

    }


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


    public List<String> getVideoList() {

        return videoList;

    }


    public void setVideoList(List<String> videoList) {

        this.videoList = videoList;

    }


    public BigInteger getView() {

        return view;

    }


    public void setView(BigInteger view) {

        this.view = view;

    }


}
