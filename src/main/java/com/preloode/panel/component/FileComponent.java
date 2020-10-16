package com.preloode.panel.component;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.FileSize;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.FileResponse;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.List;
import java.util.*;


@Component
public class FileComponent {


    private static final Logger logger = LoggerFactory.getLogger(DataComponent.class);

    @Autowired
    private GlobalConfiguration global;


    public BaseResponse cropImageSquare(String path, String fileName) {

        BaseResponse result = new BaseResponse(
                "Failed to crop square image",
                false
        );

        int x = 0;
        int y = 0;

        try {

            BufferedImage originalImage = ImageIO.read(new File(global.getSetting().getPath().getBase() + global.getSetting().getPath().getImage() + path + "/" + fileName));
            String extension = FilenameUtils.getExtension(global.getSetting().getPath().getBase() + global.getSetting().getPath().getImage() + path + "/" + fileName);
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            if(width != height) {

                if(width > height) {

                    x = (width - height) / 2;
                    width = height;

                } else if(height > width) {

                    y = (height - width) / 2;
                    height = width;

                }

            }

            BufferedImage bufferedImage = originalImage.getSubimage(x, y, width, height);
            ImageIO.write(bufferedImage, extension, new File(global.getSetting().getPath().getBase() + global.getSetting().getPath().getImage() + path + "/" + fileName));

            result.setResponse("Image cropped square");
            result.setResult(true);

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse deleteImage(String path, String fileName) {

        BaseResponse result = new BaseResponse(
                "Failed to delete file",
                false
        );

        try {

            File file = new File(global.getSetting().getPath().getBase() + global.getSetting().getPath().getImage() + path + "/" + fileName);
            boolean fileDelete = Files.deleteIfExists(file.toPath());

            if(fileDelete) {

                result.setResponse("File deleted");
                result.setResult(true);

            } else {

                result.setResponse("File doesn't exist");

            }

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse deleteImageTinyMCE(String content, String path) {

        BaseResponse result = new BaseResponse(
                "Failed to delete image tinyMCE",
                false
        );

        String[] contentList = content.split("src=\"");

        for(String string : contentList) {

            if(string.contains(global.getSetting().getPath().getImage())) {

                String[] filePath = string.split("\"");
                String[] fileName = filePath[0].split(global.getSetting().getPath().getImage() + path);

                if(fileName.length == 2) {

                    deleteImage(global.getSetting().getPath().getImage() + path, fileName[1]);

                }

            }

        }

        result.setResponse("Image tinyMCE deleted");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse resizeImage(String path, String fileName, double maxWidth, double maxHeight) {

        BaseResponse result = new BaseResponse(
                "Failed to resize image",
                false
        );

        try {

            BufferedImage originalImage = ImageIO.read(new File(global.getSetting().getPath().getBase() + path + "/" + fileName));
            String extension = FilenameUtils.getExtension(global.getSetting().getPath().getBase() + path + "/" + fileName);
            double originalWidth = originalImage.getWidth();
            double originalHeight = originalImage.getHeight();
            double width = originalWidth;
            double height = originalHeight;

            if(originalWidth > maxWidth) {

                width = maxWidth;
                height = (width / originalWidth) * originalHeight;

            } else if(originalHeight > maxHeight) {

                width = (height / originalHeight) * originalWidth;
                height = maxHeight;

            }

            int newWidth = (int) width;
            int newHeight = (int) height;

            BufferedImage bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

            if(extension.equals("png")) {

                bufferedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

            }

            Graphics2D graphic = bufferedImage.createGraphics();
            graphic.drawImage(originalImage, 0, 0, newWidth, newHeight, new Color(255, 255, 255, 0), null);
            graphic.dispose();

            ImageIO.write(bufferedImage, extension, new File(global.getSetting().getPath().getBase() + path + "/" + fileName));

            result.setResponse("Image resized");
            result.setResult(true);

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        logger.info(result.getResponse());

        return result;

    }


    public FileResponse uploadImage(MultipartHttpServletRequest multipartRequest, String path, int maxSize) {

        FileResponse result = new FileResponse() {
            {
                setResponse("Failed to upload image");
                setResult(false);
            }
        };

        Iterator<String> files = multipartRequest.getFileNames();
        List<String> fileResult = new ArrayList<>();

        while(files.hasNext()) {

            List<MultipartFile> multipartFiles = multipartRequest.getFiles(files.next());

            for(MultipartFile multipartFile : multipartFiles) {

                Map<String, Boolean> validation = new HashMap<>() {
                    {
                        put("extension", false);
                        put("size", false);
                    }
                };

                String fileName = multipartFile.getOriginalFilename().replaceAll("[^A-Za-z0-9\\s.-]+", "").replace(" ", "-").toLowerCase();
                String extension = FilenameUtils.getExtension(fileName);
                String validExtension = global.getFile().getUploadImage().replace("|", ", ");

                if(validExtension.contains(extension)) {

                    validation.put("extension", true);

                } else {

                    result.setResponse("Only " + validExtension + " file allowed");

                }

                int fileSize = (int) Math.ceil(multipartFile.getSize() / 1024);

                if(fileSize <= maxSize) {

                    validation.put("size", true);

                } else {

                    result.setResponse("Maximum " + Math.ceil(maxSize / 1024) + " MB file allowed");

                }

                boolean valid = true;

                for(Map.Entry<String, Boolean> map : validation.entrySet()) {

                    if(!map.getValue()) {

                        valid = false;

                        break;

                    }

                }

                if(valid) {

                    try {

                        File checkDir = new File(global.getSetting().getPath().getBase() + path + "/" + fileName);
                        String fileNameResult = fileName;
                        int i = 1;

                        while(checkDir.exists()) {

                            fileNameResult = FilenameUtils.removeExtension(fileName) + "_" + i + "." + extension;
                            checkDir = new File(global.getSetting().getPath().getBase() + path + "/" + fileNameResult);

                            i++;

                        }

                        FileCopyUtils.copy(multipartFile.getBytes(), new FileOutputStream(global.getSetting().getPath().getBase() + path + "/" + fileNameResult));
                        fileResult.add(fileNameResult);

                        result.setResponse("Image uploaded");
                        result.setResult(true);

                    } catch(Exception exception) {

                        logger.error(exception.getMessage());

                    }

                }

            }

        }

        result.setImageList(fileResult);

        logger.info(result.getResponse());

        return result;

    }


    public FileResponse uploadResizeImage(MultipartHttpServletRequest multipartRequest, FileSize size, String path) {

        int maxHeight = global.getFile().getImageHeightMax();
        int maxSize = global.getFile().getImageSizeMax();
        int maxWidth = global.getFile().getImageWidthMax();

        if(size == FileSize.Medium) {

            maxHeight = global.getFile().getThumbnailHeightMax();
            maxSize = global.getFile().getThumbnailSizeMax();
            maxWidth = global.getFile().getThumbnailWidthMax();

        }

        FileResponse result = uploadImage(multipartRequest, global.getSetting().getPath().getImage() + path, maxSize);

        if(result.isResult()) {

            for(String string : result.getImageList()) {

                resizeImage(global.getSetting().getPath().getImage() + path, string, maxWidth, maxHeight);

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public FileResponse uploadResizeTinymceImage(MultipartHttpServletRequest multipartRequest, String path) {

        FileResponse result = uploadImage(multipartRequest, global.getSetting().getPath().getImage() + path, global.getFile().getImageSizeMax());

        if(result.isResult()) {

            List<String> files = result.getImageList();

            for(String string : files) {

                resizeImage(global.getSetting().getPath().getImage() + path, string, global.getFile().getImageWidthMax(), global.getFile().getImageHeightMax());

                result.setLocation(global.getSetting().getUrl().getImage() + path + "/" + string);

            }

            result.setResponse("Tinymce image uploaded resized");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse writeFile(String content, String path, String fileName) {

        BaseResponse result = new BaseResponse(
                "Failed to write file",
                false
        );

        try {

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(global.getSetting().getPath().getBase() + path + "/" + fileName));
            bufferedWriter.write(content);
            bufferedWriter.close();

            result.setResponse("File wrote");
            result.setResult(true);

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        logger.info(result.getResponse());

        return result;

    }


}
