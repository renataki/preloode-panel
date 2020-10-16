package com.preloode.panel.configuration.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FileConfiguration {


    @Value("${file.image.height.max}")
    private int imageHeightMax;

    @Value("${file.image.size.max}")
    private int imageSizeMax;

    @Value("${file.image.width.max}")
    private int imageWidthMax;

    @Value("${file.thumbnail.height.max}")
    private int thumbnailHeightMax;

    @Value("${file.thumbnail.size.max}")
    private int thumbnailSizeMax;

    @Value("${file.thumbnail.width.max}")
    private int thumbnailWidthMax;

    @Value("${file.upload.audio}")
    private String uploadAudio;

    @Value("${file.upload.image}")
    private String uploadImage;

    @Value("${file.upload.video}")
    private String uploadVideo;


    public int getImageHeightMax() {

        return imageHeightMax;

    }


    public void setImageHeightMax(int imageHeightMax) {

        this.imageHeightMax = imageHeightMax;

    }


    public int getImageSizeMax() {

        return imageSizeMax;

    }


    public void setImageSizeMax(int imageSizeMax) {

        this.imageSizeMax = imageSizeMax;

    }


    public int getImageWidthMax() {

        return imageWidthMax;

    }


    public void setImageWidthMax(int imageWidthMax) {

        this.imageWidthMax = imageWidthMax;

    }


    public int getThumbnailHeightMax() {

        return thumbnailHeightMax;

    }


    public void setThumbnailHeightMax(int thumbnailHeightMax) {

        this.thumbnailHeightMax = thumbnailHeightMax;

    }


    public int getThumbnailSizeMax() {

        return thumbnailSizeMax;

    }


    public void setThumbnailSizeMax(int thumbnailSizeMax) {

        this.thumbnailSizeMax = thumbnailSizeMax;

    }


    public int getThumbnailWidthMax() {

        return thumbnailWidthMax;

    }


    public void setThumbnailWidthMax(int thumbnailWidthMax) {

        this.thumbnailWidthMax = thumbnailWidthMax;

    }


    public String getUploadAudio() {

        return uploadAudio;

    }


    public void setUploadAudio(String uploadAudio) {

        this.uploadAudio = uploadAudio;

    }


    public String getUploadImage() {

        return uploadImage;

    }


    public void setUploadImage(String uploadImage) {

        this.uploadImage = uploadImage;

    }


    public String getUploadVideo() {

        return uploadVideo;

    }


    public void setUploadVideo(String uploadVideo) {

        this.uploadVideo = uploadVideo;

    }


}
