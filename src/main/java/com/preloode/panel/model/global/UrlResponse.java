package com.preloode.panel.model.global;

import java.util.List;


public class UrlResponse extends BaseResponse {


    private String path;

    private List<String> pathList;

    private String url;

    private List<String> urlList;


    public UrlResponse() {


    }


    public UrlResponse(String response, boolean result, String path, List<String> pathList, String url, List<String> urlList) {

        super(response, result);
        this.path = path;
        this.pathList = pathList;
        this.url = url;
        this.urlList = urlList;

    }


    public String getPath() {

        return path;

    }


    public void setPath(String path) {

        this.path = path;

    }


    public List<String> getPathList() {

        return pathList;

    }


    public void setPathList(List<String> pathList) {

        this.pathList = pathList;

    }


    public String getUrl() {

        return url;

    }


    public void setUrl(String url) {

        this.url = url;

    }


    public List<String> getUrlList() {

        return urlList;

    }


    public void setUrlList(List<String> urlList) {

        this.urlList = urlList;

    }


}
