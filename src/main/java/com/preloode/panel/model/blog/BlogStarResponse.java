package com.preloode.panel.model.blog;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.global.BaseResponse;

import java.util.List;
import java.util.Map;


public class BlogStarResponse extends BaseResponse {


    private List<BlogCategory> blogCategoryList;

    private BlogStar blogStar;

    private List<BlogStar> blogStarList;

    private List<CompanyBranch> companyBranchList;

    private List<Company> companyList;

    private Map<String, Object> filter;

    private String link;

    private int page;

    private int size;

    private List<Status> statusList;


    public BlogStarResponse() {


    }


    public BlogStarResponse(String response, boolean result, List<BlogCategory> blogCategoryList, BlogStar blogStar, List<BlogStar> blogStarList, List<CompanyBranch> companyBranchList, List<Company> companyList, Map<String, Object> filter, String link, int page, int size, List<Status> statusList) {

        super(response, result);
        this.blogCategoryList = blogCategoryList;
        this.blogStar = blogStar;
        this.blogStarList = blogStarList;
        this.companyBranchList = companyBranchList;
        this.companyList = companyList;
        this.filter = filter;
        this.link = link;
        this.page = page;
        this.size = size;
        this.statusList = statusList;

    }


    public List<BlogCategory> getBlogCategoryList() {

        return blogCategoryList;

    }


    public void setBlogCategoryList(List<BlogCategory> blogCategoryList) {

        this.blogCategoryList = blogCategoryList;

    }


    public BlogStar getBlogStar() {

        return blogStar;

    }


    public void setBlogStar(BlogStar blogStar) {

        this.blogStar = blogStar;

    }


    public List<BlogStar> getBlogStarList() {

        return blogStarList;

    }


    public void setBlogStarList(List<BlogStar> blogStarList) {

        this.blogStarList = blogStarList;

    }


    public List<CompanyBranch> getCompanyBranchList() {

        return companyBranchList;

    }


    public void setCompanyBranchList(List<CompanyBranch> companyBranchList) {

        this.companyBranchList = companyBranchList;

    }


    public List<Company> getCompanyList() {

        return companyList;

    }


    public void setCompanyList(List<Company> companyList) {

        this.companyList = companyList;

    }


    public Map<String, Object> getFilter() {

        return filter;

    }


    public void setFilter(Map<String, Object> filter) {

        this.filter = filter;

    }


    public String getLink() {

        return link;

    }


    public void setLink(String link) {

        this.link = link;

    }


    public int getPage() {

        return page;

    }


    public void setPage(int page) {

        this.page = page;

    }


    public int getSize() {

        return size;

    }


    public void setSize(int size) {

        this.size = size;

    }


    public List<Status> getStatusList() {

        return statusList;

    }


    public void setStatusList(List<Status> statusList) {

        this.statusList = statusList;

    }


}
