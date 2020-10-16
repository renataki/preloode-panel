package com.preloode.panel.service.shop;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.global.TypeIncrement;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.UrlResponse;
import com.preloode.panel.model.shop.ShopCategory;
import com.preloode.panel.model.shop.ShopCategoryLogData;
import com.preloode.panel.model.shop.ShopCategoryResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.shop.ShopCategoryLogDataRepository;
import com.preloode.panel.repository.shop.ShopCategoryRepository;
import com.preloode.panel.service.user.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ShopCategoryService {


    private static final Logger logger = LoggerFactory.getLogger(ShopCategoryService.class);

    private static final String cookieFilter = "fltshpctg";

    private static final String cookiePagination = "pgnshpctg";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private FileComponent file;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private ShopCategoryLogDataRepository shopCategoryLogDataRepository;

    @Autowired
    private ShopCategoryRepository shopCategoryRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete shop category",
                false
        );

        ShopCategory shopCategoryById = shopCategoryRepository.findById(id).orElse(null);

        if(shopCategoryById != null) {

            shopCategoryLogDataRepository.save(data.initializeLogData("shopCategoryId", shopCategoryById, account, ShopCategoryLogData.class));

            shopCategoryRepository.delete(shopCategoryById);

            for(String string : shopCategoryById.getImageList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/shop/category", string);

            }

            for(String string : shopCategoryById.getThumbnailList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/shop/category/thumbnail", string);

            }

            userLogService.insert(request, "", "Delete shop category id " + shopCategoryById.getId(), UserLogType.DeleteShopCategory, shopCategoryById.getId(), shopCategoryById.getName(), account);

            result.setResponse("Shop category deleted");
            result.setResult(true);

        } else {

            result.setResponse("Shop category doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public String initializeMenu() {

        String result = "<ul>";

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result += initializeMenuTree(shopCategoryRepository.findByBranchIdListCompanyIdListStatusSort(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), Status.Active, Sort.by(sort)), "0", 0);

        result += "</ul>";

        logger.info("Shop category menu initialized");

        return result;

    }


    public String initializeSidebar(UrlResponse pathUrl) {

        String result = "<ul class=\"category-sidebar\">";

        pathUrl.setPath(pathUrl.getPath() + "/" + pathUrl.getUrl());

        if(pathUrl.getPath().startsWith("/")) {

            pathUrl.setPath(pathUrl.getPath().replaceFirst("/", ""));

        }

        List<ShopCategory> shopCategoryLikePathByStatus = shopCategoryRepository.findLikePathByBranchIdListCompanyIdListStatus("(?i)" + pathUrl.getPath() + "(?-i)", global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), Status.Active);

        String parentId = "0";

        if(!pathUrl.getPath().isEmpty()) {

            List<Sort.Order> sort = new ArrayList<>() {
                {
                    add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                    add(new Sort.Order(Sort.Direction.ASC, "name"));
                }
            };
            List<ShopCategory> shopCategoryByStatusUrl = shopCategoryRepository.findByStatusUrlSort(Status.Active, pathUrl.getUrl(), Sort.by(sort));

            if(!shopCategoryByStatusUrl.isEmpty()) {

                parentId = shopCategoryByStatusUrl.get(0).getId();

            }

        }

        result += initializeSidebarTree(shopCategoryLikePathByStatus, parentId, 0);

        result += "</ul>";

        logger.info("Shop category sidebar initialized");

        return result;

    }


    private String initializeSidebarTree(List<ShopCategory> shopCategoryList, String parentId, int level) {

        String result = "";

        for(ShopCategory shopCategory : shopCategoryList) {

            if(shopCategory.getParent().getId().equals(parentId)) {

                String url = global.getSetting().getUrl().getBase() + "/category/";

                if(!shopCategory.getPath().isEmpty()) {

                    url += shopCategory.getPath() + "/";

                }

                url += shopCategory.getUrl() + "/";

                result += "<li>" +
                        "<a href=\"" + url + "\">" +
                        "<p class=\"title\">" + shopCategory.getName() + "</p>" +
                        "</a>" +
                        "<p class=\"icon\"><i class=\"toggle-black square-10\"></i></p>";

                level++;

                if(level > 0) {

                    result += "<ul>";

                }

                result += initializeSidebarTree(shopCategoryList, shopCategory.getId(), level);

                if(level > 0) {

                    result += "</ul>";

                }

                result += "</li>";

                level--;

            }

        }

        logger.info("Shop category icon tree initialized");

        return result;

    }


    private String initializeMenuTree(List<ShopCategory> shopCategoryList, String parentId, int level) {

        String result = "";

        for(ShopCategory shopCategory : shopCategoryList) {

            if(shopCategory.getParent().getId().equals(parentId)) {

                String url = global.getSetting().getUrl().getBase() + "/category/";

                if(!shopCategory.getPath().isEmpty()) {

                    url += shopCategory.getPath() + "/";

                }

                url += shopCategory.getUrl() + "/";

                result += "<li>" +
                        "<a href=\"" + url + "\">" +
                        "<p class=\"title\">" + shopCategory.getName() + "</p>" +
                        "</a>";

                level++;

                if(level > 0) {

                    result += "<ul>";

                }

                result += initializeMenuTree(shopCategoryList, shopCategory.getId(), level);

                if(level > 0) {

                    result += "</ul>";

                }

                result += "</li>";

                level--;

            }

        }

        logger.info("Shop category tree initialized");

        return result;

    }


    public ShopCategoryResponse initializeData(User account, String id) {

        ShopCategoryResponse result = new ShopCategoryResponse() {
            {
                setResponse("Failed to initialize shop category data");
                setResult(false);
            }
        };

        result.setShopCategory(shopCategoryRepository.findById(id).orElse(new ShopCategory()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setShopCategoryList(shopCategoryRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Shop category data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private ShopCategory initializeInput(ShopCategory input) {

        ShopCategory result = input;

        if(result.getDislike() == null) {

            result.setDislike(BigInteger.ZERO);

        }

        if(result.getLike() == null) {

            result.setLike(BigInteger.ZERO);

        }

        if(result.getRate() == null) {

            result.setRate(BigDecimal.ZERO);

        }

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        if(result.getUrl().isEmpty()) {

            result.setUrl(result.getName().replaceAll("[^A-Za-z0-9- ]", "").replaceAll(" ", "-").toLowerCase());

        }

        if(!result.getUrl().substring(result.getUrl().length() - 1).equals("/")) {

            result.setUrl(result.getUrl() + "/");

        }

        if(result.getView() == null) {

            result.setView(BigInteger.ZERO);

        }

        logger.info("Shop category input initialized");

        return result;

    }


    public ShopCategoryResponse initializePagination(HttpServletRequest request, User account, int page) {

        ShopCategoryResponse result = new ShopCategoryResponse() {
            {
                setResponse("Failed to initialize shop category pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("Shop category pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name, String parentId) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("shopCategoryName", data.validateShopCategoryName(id, name, parentId));
                put("shopCategoryParent", data.validateShopCategoryParentId(parentId));
            }
        };

        logger.info("Shop category input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, ShopCategory insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert shop category",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getName(), insertData.getParent().getId());

        boolean valid = true;

        for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

            if(!map.getValue().isResult()) {

                valid = false;

                result.setResponse(map.getValue().getResponse());

                break;

            }

        }

        if(valid) {

            insertData.setUrl(data.incrementUrl(TypeIncrement.ShopCategory, "", new ArrayList<>(), insertData.getParent().getId(), insertData.getUrl()));
            insertData.setPath(insertData.getParent().getPath() + "/" + insertData.getParent().getUrl());

            if(insertData.getPath().startsWith("/")) {

                insertData.setPath(insertData.getPath().replaceFirst("/", ""));

            }

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            shopCategoryRepository.save(insertData);

            userLogService.insert(request, "", "Insert shop category id " + insertData.getId(), UserLogType.InsertShopCategory, insertData.getId(), insertData.getName(), account);

            result.setResponse("Shop category inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public ShopCategory loadEntry(String id) {

        return shopCategoryRepository.findById(id).orElse(new ShopCategory());

    }


    public ShopCategoryResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        ShopCategoryResponse result = new ShopCategoryResponse() {
            {
                setResponse("Failed to load shop category pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setShopCategoryList(globalRepository.findPagination(query, ShopCategory.class));
        result.setLink(data.initializePaginationLink(request, "/shop/category", page, size, result.getShopCategoryList().size()));

        result.setResponse("Shop category pagination loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse removeFilterPagination(HttpServletRequest request, HttpServletResponse response, User account) {

        return data.removeFilter(request, response, account.getId(), global.getCookie().getPrefix() + cookieFilter);

    }


    public BaseResponse setPagination(HttpServletResponse response, User account, int pagination) {

        return data.setPagination(response, account.getId(), global.getCookie().getPrefix() + cookiePagination, pagination);

    }


    public BaseResponse update(HttpServletRequest request, User account, ShopCategory updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update shop category",
                false
        );

        updateData = initializeInput(updateData);

        ShopCategory shopCategoryById = shopCategoryRepository.findById(updateData.getId()).orElse(null);

        if(shopCategoryById != null) {

            Map<String, BaseResponse> validation = inputValidation(shopCategoryById.getId(), updateData.getName(), updateData.getParent().getId());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                updateData.setUrl(data.incrementUrl(TypeIncrement.ShopCategory, shopCategoryById.getId(), new ArrayList<>(), shopCategoryById.getParent().getId(), shopCategoryById.getUrl()));
                updateData.setPath(updateData.getParent().getPath() + "/" + updateData.getParent().getUrl());

                if(updateData.getPath().startsWith("/")) {

                    updateData.setPath(updateData.getPath().replaceFirst("/", ""));

                }

                for(String string : shopCategoryById.getImageList()) {

                    if(!updateData.getImageList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/shop/category", string);

                    }

                }

                for(String string : shopCategoryById.getThumbnailList()) {

                    if(!updateData.getThumbnailList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/shop/category/thumbnail", string);

                    }

                }

                shopCategoryLogDataRepository.save(data.initializeLogData("shopCategoryId", shopCategoryById, account, ShopCategoryLogData.class));

                updateData.setCreated(shopCategoryById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                shopCategoryRepository.save(updateData);

                userLogService.insert(request, "", "Update shop category id " + updateData.getId(), UserLogType.UpdateShopCategory, shopCategoryById.getId(), shopCategoryById.getName(), account);

                result.setResponse("Shop category updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Shop category doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
