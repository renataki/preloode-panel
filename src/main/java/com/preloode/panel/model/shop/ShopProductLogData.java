package com.preloode.panel.model.shop;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.shop.ProductType;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.global.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;


public class ShopProductLogData extends Base {


    private ShopBrandReference brand;

    private CategoryMultipleReference category;

    private CompanyListReference company;

    private String description;

    @Indexed
    private BigInteger dislike;

    private boolean featured;

    private String guide;

    private List<String> imageList;

    private String information;

    @Indexed
    private BigInteger like;

    private MetaReference meta;

    @Indexed
    private String name;

    private OgReference og;

    private List<String> pathList;

    private ShopProductPriceReference price;

    @Indexed
    private BigDecimal rate;

    @Indexed
    private boolean recurring;

    @Indexed
    private BigInteger sequence;

    @Indexed
    private String shopProductId;

    @Indexed
    private String sku;

    @Indexed
    private Status status;

    @Indexed
    private BigInteger stock;

    @Indexed
    private boolean storage;

    private List<String> tagList;

    private List<String> thumbnailList;

    @Indexed
    private ProductType type;

    @Indexed
    private String url;

    private ShopProductVariantReference variant;

    @Indexed
    private BigInteger view;


    public ShopProductLogData() {


    }


    public ShopProductLogData(String id, TimestampReference created, TimestampReference modified, ShopBrandReference brand, CategoryMultipleReference category, CompanyListReference company, String description, BigInteger dislike, boolean featured, String guide, List<String> imageList, String information, BigInteger like, MetaReference meta, String name, OgReference og, List<String> pathList, ShopProductPriceReference price, BigDecimal rate, boolean recurring, BigInteger sequence, String shopProductId, String sku, Status status, BigInteger stock, boolean storage, List<String> tagList, List<String> thumbnailList, ProductType type, String url, ShopProductVariantReference variant, BigInteger view) {

        super(id, created, modified);
        this.brand = brand;
        this.category = category;
        this.company = company;
        this.description = description;
        this.dislike = dislike;
        this.featured = featured;
        this.guide = guide;
        this.imageList = imageList;
        this.information = information;
        this.like = like;
        this.meta = meta;
        this.name = name;
        this.og = og;
        this.pathList = pathList;
        this.price = price;
        this.rate = rate;
        this.recurring = recurring;
        this.sequence = sequence;
        this.shopProductId = shopProductId;
        this.sku = sku;
        this.status = status;
        this.stock = stock;
        this.storage = storage;
        this.tagList = tagList;
        this.thumbnailList = thumbnailList;
        this.type = type;
        this.url = url;
        this.variant = variant;
        this.view = view;

    }


    public ShopBrandReference getBrand() {

        return brand;

    }


    public void setBrand(ShopBrandReference brand) {

        this.brand = brand;

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


    public String getGuide() {

        return guide;

    }


    public void setGuide(String guide) {

        this.guide = guide;

    }


    public List<String> getImageList() {

        return imageList;

    }


    public void setImageList(List<String> imageList) {

        this.imageList = imageList;

    }


    public String getInformation() {

        return information;

    }


    public void setInformation(String information) {

        this.information = information;

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


    public List<String> getPathList() {

        return pathList;

    }


    public void setPathList(List<String> pathList) {

        this.pathList = pathList;

    }


    public ShopProductPriceReference getPrice() {

        return price;

    }


    public void setPrice(ShopProductPriceReference price) {

        this.price = price;

    }


    public BigDecimal getRate() {

        return rate;

    }


    public void setRate(BigDecimal rate) {

        this.rate = rate;

    }


    public boolean isRecurring() {

        return recurring;

    }


    public void setRecurring(boolean recurring) {

        this.recurring = recurring;

    }


    public BigInteger getSequence() {

        return sequence;

    }


    public void setSequence(BigInteger sequence) {

        this.sequence = sequence;

    }


    public String getShopProductId() {

        return shopProductId;

    }


    public void setShopProductId(String shopProductId) {

        this.shopProductId = shopProductId;

    }


    public String getSku() {

        return sku;

    }


    public void setSku(String sku) {

        this.sku = sku;

    }


    public Status getStatus() {

        return status;

    }


    public void setStatus(Status status) {

        this.status = status;

    }


    public BigInteger getStock() {

        return stock;

    }


    public void setStock(BigInteger stock) {

        this.stock = stock;

    }


    public boolean isStorage() {

        return storage;

    }


    public void setStorage(boolean storage) {

        this.storage = storage;

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


    public ProductType getType() {

        return type;

    }


    public void setType(ProductType type) {

        this.type = type;

    }


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


    public ShopProductVariantReference getVariant() {

        return variant;

    }


    public void setVariant(ShopProductVariantReference variant) {

        this.variant = variant;

    }


    public BigInteger getView() {

        return view;

    }


    public void setView(BigInteger view) {

        this.view = view;

    }


}
