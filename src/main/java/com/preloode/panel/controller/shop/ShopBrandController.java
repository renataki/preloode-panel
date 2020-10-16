package com.preloode.panel.controller.shop;

import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.FileSize;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.FileResponse;
import com.preloode.panel.model.shop.ShopBrand;
import com.preloode.panel.model.shop.ShopBrandResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.shop.ShopBrandService;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
@RequestMapping(value = "/shop/brand")
public class ShopBrandController {


    private static final Logger logger = LoggerFactory.getLogger(ShopBrandController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private FileComponent file;

    @Autowired
    private ShopBrandService shopBrandService;

    @Autowired
    private UserService userService;

    private User account;

    private int page;


    @ModelAttribute("account")
    public User account(HttpServletRequest request, HttpServletResponse response) {

        userService.checkAccess(response);

        account = userService.initializeAccount(request);

        return account;

    }


    @ModelAttribute("global")
    public GlobalConfiguration global(HttpServletRequest request) {

        global.initializeSetting(request, "Shop Brand");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                page = 1;

                ShopBrandResponse shopBrandPagination = shopBrandService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(shopBrandPagination.isResult()) {

                    model.addAttribute("link", shopBrandPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", shopBrandPagination.getShopBrandList());

                }

                logger.info("Shop brand page initialized");

                return "shop/brand";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/{page:page-[\\d]}", method = RequestMethod.GET)
    public String page(HttpServletRequest request, @PathVariable("page") String pagePath, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                ShopBrandResponse shopBrandPagination = shopBrandService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(shopBrandPagination.isResult()) {

                    model.addAttribute("link", shopBrandPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", shopBrandPagination.getShopBrandList());

                }

                logger.info("Shop brand page initialized");

                return "shop/brand";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/entry", method = RequestMethod.GET)
    public String add(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new ShopBrand());

                logger.info("Shop brand entry page initialized");

                return "shop/brand-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/entry/{id}", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, @PathVariable("id") String id, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", shopBrandService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("Shop brand entry page initialized");

                return "shop/brand-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public ShopBrandResponse initializePagination(HttpServletRequest request) {

        ShopBrandResponse result = new ShopBrandResponse() {
            {
                setResponse("Failed to initialize shop brand pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = shopBrandService.initializePagination(request, account, page);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/filter-pagination")
    @ResponseBody
    public BaseResponse filterPagination(HttpServletResponse response, @RequestBody Map<String, Object> filter) {

        BaseResponse result = new BaseResponse(
                "Failed to filter shop brand pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = shopBrandService.filterPagination(response, account, filter);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/set-pagination")
    @ResponseBody
    public BaseResponse setPagination(HttpServletResponse response, @RequestBody int pagination) {

        BaseResponse result = new BaseResponse(
                "Failed to set shop brand pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = shopBrandService.setPagination(response, account, pagination);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/remove-filter-pagination")
    @ResponseBody
    public BaseResponse removeFilterPagination(HttpServletRequest request, HttpServletResponse response) {

        BaseResponse result = new BaseResponse(
                "Failed to remove filter shop brand pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = shopBrandService.removeFilterPagination(request, response, account);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/initialize-data")
    @ResponseBody
    public ShopBrandResponse initializeData(@RequestBody String id) {

        ShopBrandResponse result = new ShopBrandResponse() {
            {
                setResponse("Failed to initialize shop brand data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = shopBrandService.initializeData(account, id);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/upload-image-list")
    @ResponseBody
    public FileResponse uploadImageList(MultipartHttpServletRequest multipartRequest) {

        FileResponse result = new FileResponse() {
            {
                setResponse("Failed to upload shop brand image list");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = file.uploadResizeImage(multipartRequest, FileSize.Large, "/shop/brand");

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/upload-thumbnail-list")
    @ResponseBody
    public FileResponse uploadThumbnailList(MultipartHttpServletRequest multipartRequest) {

        FileResponse result = new FileResponse() {
            {
                setResponse("Failed to upload shop brand thumbnail list");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = file.uploadResizeImage(multipartRequest, FileSize.Medium, "/shop/brand/thumbnail");

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/upload-tinymce")
    @ResponseBody
    public FileResponse uploadTinymce(MultipartHttpServletRequest multipartRequest) {

        FileResponse result = new FileResponse() {
            {
                setResponse("Failed to upload shop brand tinymce");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = file.uploadResizeTinymceImage(multipartRequest, "/shop/brand/tinymce");

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/insert")
    @ResponseBody
    public BaseResponse insert(HttpServletRequest request, @RequestBody ShopBrand shopBrand) {

        BaseResponse result = new BaseResponse(
                "Failed to insert shop brand",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = shopBrandService.insert(request, account, shopBrand);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/update")
    @ResponseBody
    public BaseResponse update(HttpServletRequest request, @RequestBody ShopBrand shopBrand) {

        BaseResponse result = new BaseResponse(
                "Failed to update shop brand",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = shopBrandService.update(request, account, shopBrand);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public BaseResponse delete(HttpServletRequest request, @RequestBody String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete shop brand",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getShopBrand());

            if(checkPrivilege.isResult()) {

                result = shopBrandService.delete(request, account, id);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


}
