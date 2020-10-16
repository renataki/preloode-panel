package com.preloode.panel.model.global;

import java.util.List;


public class FileResponse extends BaseResponse {


    private List<String> imageList;

    private String location;


    public FileResponse() {


    }


    public FileResponse(String response, boolean result, List<String> imageList, String location) {

        super(response, result);
        this.imageList = imageList;
        this.location = location;

    }


    public List<String> getImageList() {

        return imageList;

    }


    public void setImageList(List<String> imageList) {

        this.imageList = imageList;

    }


    public String getLocation() {

        return location;

    }


    public void setLocation(String location) {

        this.location = location;

    }


}
