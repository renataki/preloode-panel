package com.preloode.panel.service.shop;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.global.TypeIncrement;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.shop.ShopBrand;
import com.preloode.panel.model.shop.ShopBrandLogData;
import com.preloode.panel.model.shop.ShopBrandResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.shop.ShopBrandLogDataRepository;
import com.preloode.panel.repository.shop.ShopBrandRepository;
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
public class ShopBrandService {


    private static final Logger logger = LoggerFactory.getLogger(ShopBrandService.class);

    private static final String cookieFilter = "fltshpbrn";

    private static final String cookiePagination = "pgnshpbrn";

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
    private ShopBrandLogDataRepository shopBrandLogDataRepository;

    @Autowired
    private ShopBrandRepository shopBrandRepository;

    @Autowired
    private ShopCategoryRepository shopCategoryRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete shop brand",
                false
        );

        ShopBrand shopBrandById = shopBrandRepository.findById(id).orElse(null);

        if(shopBrandById != null) {

            shopBrandLogDataRepository.save(data.initializeLogData("shopBrandId", shopBrandById, account, ShopBrandLogData.class));

            shopBrandRepository.delete(shopBrandById);

            for(String string : shopBrandById.getImageList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/shop/brand", string);

            }

            for(String string : shopBrandById.getThumbnailList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/shop/brand/thumbnail", string);

            }

            userLogService.insert(request, "", "Delete shop brand id " + shopBrandById.getId(), UserLogType.DeleteShopBrand, shopBrandById.getId(), shopBrandById.getName(), account);

            result.setResponse("Shop brand deleted");
            result.setResult(true);

        } else {

            result.setResponse("Shop brand doesn't exist");

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
        List<ShopBrand> shopBrandByStatusSort = shopBrandRepository.findByBranchIdListCompanyIdListStatusSort(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), Status.Active, Sort.by(sort));

        if(!shopBrandByStatusSort.isEmpty()) {

            for(ShopBrand shopBrand : shopBrandByStatusSort) {

                String url = global.getSetting().getUrl().getBase() + "/brand/";

                if(!shopBrand.getPath().isEmpty()) {

                    url += shopBrand.getPath() + "/";

                }

                url += shopBrand.getUrl() + "/";

                result += "<li>" +
                        "<a href=\"" + url + "\">" +
                        "<p class=\"title\">" + shopBrand.getName() + "</p>" +
                        "</a>" +
                        "</li>";

            }

        }

        result += "</ul>";

        logger.info("Shop brand menu initialized");

        return result;

    }


    public ShopBrandResponse initializeData(User account, String id) {

        ShopBrandResponse result = new ShopBrandResponse() {
            {
                setResponse("Failed to initialize shop brand data");
                setResult(false);
            }
        };

        result.setShopBrand(shopBrandRepository.findById(id).orElse(new ShopBrand()));

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

        result.setResponse("Shop brand data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private ShopBrand initializeInput(ShopBrand input) {

        ShopBrand result = input;

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

        logger.info("Shop brand input initialized");

        return result;

    }


    public ShopBrandResponse initializePagination(HttpServletRequest request, User account, int page) {

        ShopBrandResponse result = new ShopBrandResponse() {
            {
                setResponse("Failed to initialize shop brand pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setShopCategoryList(shopCategoryRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Shop brand pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("shopBrandName", data.validateShopBrandName(id, name));
            }
        };

        logger.info("Shop brand input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, ShopBrand insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert shop brand",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getName());

        boolean valid = true;

        for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

            if(!map.getValue().isResult()) {

                valid = false;

                result.setResponse(map.getValue().getResponse());

                break;

            }

        }

        if(valid) {

            insertData.setUrl(data.incrementUrl(TypeIncrement.ShopBrand, "", new ArrayList<>(), "", insertData.getUrl()));

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            shopBrandRepository.save(insertData);

            userLogService.insert(request, "", "Insert shop brand id " + insertData.getId(), UserLogType.InsertShopBrand, insertData.getId(), insertData.getName(), account);

            result.setResponse("Shop brand inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public ShopBrand loadEntry(String id) {

        return shopBrandRepository.findById(id).orElse(new ShopBrand());

    }


    public ShopBrandResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        ShopBrandResponse result = new ShopBrandResponse() {
            {
                setResponse("Failed to load shop brand pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setShopBrandList(globalRepository.findPagination(query, ShopBrand.class));
        result.setLink(data.initializePaginationLink(request, "/shop/brand", page, size, result.getShopBrandList().size()));

        result.setResponse("Shop brand pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, ShopBrand updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update shop brand",
                false
        );

        updateData = initializeInput(updateData);

        ShopBrand shopBrandById = shopBrandRepository.findById(updateData.getId()).orElse(null);

        if(shopBrandById != null) {

            Map<String, BaseResponse> validation = inputValidation(shopBrandById.getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                updateData.setUrl(data.incrementUrl(TypeIncrement.ShopBrand, shopBrandById.getId(), new ArrayList<String>(), "", updateData.getUrl()));

                for(String string : shopBrandById.getImageList()) {

                    if(!updateData.getImageList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/shop/brand", string);

                    }

                }

                for(String string : shopBrandById.getThumbnailList()) {

                    if(!updateData.getThumbnailList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/shop/brand/thumbnail", string);

                    }

                }

                shopBrandLogDataRepository.save(data.initializeLogData("shopBrandId", shopBrandById, account, ShopBrandLogData.class));

                updateData.setCreated(shopBrandById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                shopBrandRepository.save(updateData);

                userLogService.insert(request, "", "Update shop brand id " + updateData.getId(), UserLogType.UpdateShopBrand, shopBrandById.getId(), shopBrandById.getName(), account);

                result.setResponse("Shop brand updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Shop brand doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
