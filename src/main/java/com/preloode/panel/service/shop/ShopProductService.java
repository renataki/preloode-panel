package com.preloode.panel.service.shop;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.global.TypeIncrement;
import com.preloode.panel.enumeration.shop.PriceRecurring;
import com.preloode.panel.enumeration.shop.ProductType;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.UrlResponse;
import com.preloode.panel.model.shop.ShopProduct;
import com.preloode.panel.model.shop.ShopProductLogData;
import com.preloode.panel.model.shop.ShopProductResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.shop.ShopBrandRepository;
import com.preloode.panel.repository.shop.ShopCategoryRepository;
import com.preloode.panel.repository.shop.ShopProductLogDataRepository;
import com.preloode.panel.repository.shop.ShopProductRepository;
import com.preloode.panel.service.user.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ShopProductService {


    private static final Logger logger = LoggerFactory.getLogger(ShopProductService.class);

    private static final String cookieFilter = "fltshppdc";

    private static final String cookiePagination = "pgnshppdc";

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
    private ShopBrandRepository shopBrandRepository;

    @Autowired
    private ShopCategoryRepository shopCategoryRepository;

    @Autowired
    private ShopProductLogDataRepository shopProductLogDataRepository;

    @Autowired
    private ShopProductRepository shopProductRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete shop product",
                false
        );

        ShopProduct shopProductById = shopProductRepository.findById(id).orElse(null);

        if(shopProductById != null) {

            shopProductLogDataRepository.save(data.initializeLogData("shopProductId", shopProductById, account, ShopProductLogData.class));

            shopProductRepository.delete(shopProductById);

            for(String string : shopProductById.getImageList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/shop/product", string);

            }

            for(String string : shopProductById.getThumbnailList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/shop/product/thumbnail", string);

            }

            userLogService.insert(request, "", "Delete shop product id " + shopProductById.getId(), UserLogType.DeleteShopProduct, shopProductById.getId(), shopProductById.getName(), account);

            result.setResponse("Shop product deleted");
            result.setResult(true);

        } else {

            result.setResponse("Shop product doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public ShopProductResponse initializeData(User account, String id) {

        ShopProductResponse result = new ShopProductResponse() {
            {
                setResponse("Failed to initialize shop product data");
                setResult(false);
            }
        };

        result.setShopProduct(shopProductRepository.findById(id).orElse(new ShopProduct()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setPriceRecurringList(Stream.of(PriceRecurring.values()).collect(Collectors.toList()));
        result.setProductTypeList(Stream.of(ProductType.values()).collect(Collectors.toList()));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setShopBrandList(shopBrandRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setShopCategoryList(shopCategoryRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Shop product data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private ShopProduct initializeInput(ShopProduct input) {

        ShopProduct result = input;

        if(result.getDislike() == null) {

            result.setDislike(BigInteger.ZERO);

        }

        if(result.getLike() == null) {

            result.setLike(BigInteger.ZERO);

        }

        if(result.getPrice().getExchange().getBuy() == null) {

            result.getPrice().getExchange().setBuy(BigDecimal.ZERO);

        }

        if(result.getPrice().getExchange().getSell() == null) {

            result.getPrice().getExchange().setSell(BigDecimal.ZERO);

        }

        if(result.getPrice().getOneTime().getDiscount() == null) {

            result.getPrice().getOneTime().setDiscount(BigDecimal.ZERO);

        }

        if(result.getPrice().getOneTime().getNormal() == null) {

            result.getPrice().getOneTime().setNormal(BigDecimal.ZERO);

        }

        for(int i = 0; i < PriceRecurring.values().length; i++) {

            if(i < result.getPrice().getRecurring().getDiscountList().size()) {

                if(result.getPrice().getRecurring().getDiscountList().get(i) == null) {

                    result.getPrice().getRecurring().getDiscountList().set(i, BigDecimal.ZERO);

                }

            } else {

                result.getPrice().getRecurring().getDiscountList().set(i, BigDecimal.ZERO);

            }

            if(i < result.getPrice().getRecurring().getNormalList().size()) {

                if(result.getPrice().getRecurring().getNormalList().get(i) == null) {

                    result.getPrice().getRecurring().getNormalList().set(i, BigDecimal.ZERO);

                }

            } else {

                result.getPrice().getRecurring().getNormalList().set(i, BigDecimal.ZERO);

            }

        }

        if(result.getRate() == null) {

            result.setRate(BigDecimal.ZERO);

        }

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        if(result.getStock() == null) {

            result.setStock(BigInteger.ZERO);

        }

        if(result.getUrl().isEmpty()) {

            result.setUrl(result.getName().replaceAll("[^A-Za-z0-9- ]", "").replaceAll(" ", "-").toLowerCase());

        }

        if(!result.getUrl().substring(result.getUrl().length() - 1).equals("/")) {

            result.setUrl(result.getUrl() + "/");

        }

        if(!result.getVariant().getNameList().isEmpty()) {

            for(int i = 0; i < result.getVariant().getNameList().size(); i++) {

                if(i < result.getVariant().getPrice().getOneTime().getDiscountList().size()) {

                    if(result.getVariant().getPrice().getOneTime().getDiscountList().get(i) == null) {

                        result.getVariant().getPrice().getOneTime().getDiscountList().set(i, BigDecimal.ZERO);

                    }

                } else {

                    result.getVariant().getPrice().getOneTime().getDiscountList().set(i, BigDecimal.ZERO);

                }

                if(i < result.getVariant().getPrice().getOneTime().getNormalList().size()) {

                    if(result.getVariant().getPrice().getOneTime().getNormalList().get(i) == null) {

                        result.getVariant().getPrice().getOneTime().getNormalList().set(i, BigDecimal.ZERO);

                    }

                } else {

                    result.getVariant().getPrice().getOneTime().getNormalList().set(i, BigDecimal.ZERO);

                }

                if(i >= result.getVariant().getPrice().getRecurring().getDiscountList().size()) {

                    result.getVariant().getPrice().getRecurring().getDiscountList().set(i, new ArrayList<>());

                }

                if(i >= result.getVariant().getPrice().getRecurring().getNormalList().size()) {

                    result.getVariant().getPrice().getRecurring().getNormalList().set(i, new ArrayList<>());

                }

                for(int j = 0; j < PriceRecurring.values().length; j++) {

                    if(j < result.getVariant().getPrice().getRecurring().getDiscountList().get(i).size()) {

                        if(result.getVariant().getPrice().getRecurring().getDiscountList().get(i).get(j) == null) {

                            result.getVariant().getPrice().getRecurring().getDiscountList().get(i).set(j, BigDecimal.ZERO);

                        }

                    } else {

                        result.getVariant().getPrice().getRecurring().getDiscountList().get(i).set(j, BigDecimal.ZERO);

                    }

                    if(j < result.getVariant().getPrice().getRecurring().getNormalList().get(i).size()) {

                        if(result.getVariant().getPrice().getRecurring().getNormalList().get(i).get(j) == null) {

                            result.getVariant().getPrice().getRecurring().getNormalList().get(i).set(j, BigDecimal.ZERO);

                        }

                    } else {

                        result.getVariant().getPrice().getRecurring().getNormalList().get(i).set(j, BigDecimal.ZERO);

                    }

                }

                if(i < result.getVariant().getStockList().size()) {

                    if(result.getVariant().getStockList().get(i) == null) {

                        result.getVariant().getStockList().set(i, BigInteger.ZERO);

                    }

                } else {

                    result.getVariant().getStockList().set(i, BigInteger.ZERO);

                }

            }

        }

        if(result.getView() == null) {

            result.setView(BigInteger.ZERO);

        }

        logger.info("Shop product input initialized");

        return result;

    }


    public ShopProductResponse initializePagination(HttpServletRequest request, User account, int page) {

        ShopProductResponse result = new ShopProductResponse() {
            {
                setResponse("Failed to initialize shop product pagination");
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
        result.setShopBrandList(shopBrandRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setShopCategoryList(shopCategoryRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Shop product pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name, List<String> categoryId) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("shopProductName", data.validateShopProductName(id, name, new HashSet(categoryId)));
            }
        };

        logger.info("Shop product input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, ShopProduct insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert shop product",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getName(), insertData.getCategory().getIdList());

        boolean valid = true;

        for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

            if(!map.getValue().isResult()) {

                valid = false;

                result.setResponse(map.getValue().getResponse());

                break;

            }

        }

        if(valid) {

            insertData.setUrl(data.incrementUrl(TypeIncrement.ShopProduct, "", insertData.getCategory().getIdList(), "", insertData.getUrl()));
            insertData.setPathList(data.initializeMultiplePath(insertData.getCategory().getPathList(), insertData.getCategory().getUrlList()));

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            shopProductRepository.save(insertData);

            userLogService.insert(request, "", "Insert shop product id " + insertData.getId(), UserLogType.InsertShopProduct, insertData.getId(), insertData.getName(), account);

            result.setResponse("Shop product inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public ShopProductResponse loadBranchIdListCompanyIdListFeatured() {

        ShopProductResponse result = new ShopProductResponse() {
            {
                setResponse("Failed to load shop product branch id list company id list featured");
                setResult(false);
            }
        };

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.DESC, "modified.timestamp"));
            }
        };
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(sort));
        result.setShopProductList(shopProductRepository.findByBranchIdListCompanyIdListFeaturedStatusPageable(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), true, Status.Active, pageRequest));

        result.setResponse("Shop product branch id list company id list featured loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public ShopProductResponse loadBranchIdListCompanyIdListLatest() {

        ShopProductResponse result = new ShopProductResponse() {
            {
                setResponse("Failed to load shop product branch id list company id list latest");
                setResult(false);
            }
        };

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.DESC, "modified.timestamp"));
            }
        };
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(sort));
        result.setShopProductList(shopProductRepository.findByBranchIdListCompanyIdListStatusPageable(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), Status.Active, pageRequest));

        result.setResponse("Shop product branch id list company id list latest loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public ShopProduct loadEntry(String id) {

        return shopProductRepository.findById(id).orElse(new ShopProduct());

    }


    public ShopProductResponse loadEntryByBranchIdListCompanyIdListPathListStatusUrl(UrlResponse url) {

        ShopProductResponse result = new ShopProductResponse() {
            {
                setResponse("Failed to load shop product entry by branch id list company id list path list status url");
                setResult(false);
                setShopProduct(new ShopProduct());
            }
        };

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "created.timestamp"));
            }
        };
        List<ShopProduct> shopProductByBranchIdListCompanyIdListPathStatusUrl = shopProductRepository.findByBranchIdListCompanyIdListPathListStatusUrlSort(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), url.getPath(), Status.Active, url.getUrl(), Sort.by(sort));

        if(!shopProductByBranchIdListCompanyIdListPathStatusUrl.isEmpty()) {

            result.setShopProduct(shopProductByBranchIdListCompanyIdListPathStatusUrl.get(0));

            result.setResponse("Shop product entry by branch id list company id list  path list status url loaded");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public ShopProductResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        ShopProductResponse result = new ShopProductResponse() {
            {
                setResponse("Failed to load shop product pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setShopProductList(globalRepository.findPagination(query, ShopProduct.class));
        result.setLink(data.initializePaginationLink(request, "/shop/product", page, size, result.getShopProductList().size()));

        result.setResponse("Shop product pagination loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public ShopProductResponse loadPaginationByBranchIdListBrandNameCompanyIdList(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, UrlResponse pathUrl, int page) {

        ShopProductResponse result = new ShopProductResponse() {
            {
                setResponse("Failed to load shop product pagination by branch id list brand name company id list");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));
        query.addCriteria(Criteria.where("branch.idList").is(global.getSetting().getCompanyBranch()));
        query.addCriteria(Criteria.where("company.idList").is(global.getSetting().getCompany()));

        if(!pathUrl.getUrl().isEmpty()) {

            query.addCriteria(Criteria.where("brand.name").is(pathUrl.getUrl()));

        }

        result.setShopProductList(globalRepository.findPagination(query, ShopProduct.class));

        pathUrl.setUrl("/brand");

        if(!pathUrl.getPath().isEmpty()) {

            pathUrl.setUrl(pathUrl.getUrl() + "/" + pathUrl.getPath());

        }

        result.setLink(data.initializePaginationLink(request, pathUrl.getUrl(), page, size, result.getShopProductList().size()));

        result.setResponse("Shop product pagination by branch id list brand name company id list loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public ShopProductResponse loadPaginationByBranchIdListCompanyIdList(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        ShopProductResponse result = new ShopProductResponse() {
            {
                setResponse("Failed to load shop product pagination by branch id list company id list");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));
        query.addCriteria(Criteria.where("branch.idList").is(global.getSetting().getCompanyBranch()));
        query.addCriteria(Criteria.where("company.idList").is(global.getSetting().getCompany()));

        result.setShopProductList(globalRepository.findPagination(query, ShopProduct.class));
        result.setLink(data.initializePaginationLink(request, "/shop/product", page, size, result.getShopProductList().size()));

        result.setResponse("Shop product pagination by branch id list company id list loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public ShopProductResponse loadPaginationByBranchIdListCompanyIdListPath(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, UrlResponse pathUrl, int page) {

        ShopProductResponse result = new ShopProductResponse() {
            {
                setResponse("Failed to load shop product pagination by branch id list company id list path");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));
        query.addCriteria(Criteria.where("branch.idList").is(global.getSetting().getCompanyBranch()));
        query.addCriteria(Criteria.where("company.idList").is(global.getSetting().getCompany()));

        String path = pathUrl.getPath() + "/" + pathUrl.getUrl();

        if(path.startsWith("/")) {

            path = path.replaceFirst("/", "");

        }

        if(!path.isEmpty()) {

            query.addCriteria(Criteria.where("pathList").is(path));

        }

        result.setShopProductList(globalRepository.findPagination(query, ShopProduct.class));

        if(!path.isEmpty()) {

            path = "/category/" + path;

        } else {

            path = "/category";

        }

        result.setLink(data.initializePaginationLink(request, path, page, size, result.getShopProductList().size()));

        result.setResponse("Shop product pagination by branch id list company id list path loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, ShopProduct updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update shop product",
                false
        );

        updateData = initializeInput(updateData);

        ShopProduct shopProductById = shopProductRepository.findById(updateData.getId()).orElse(null);

        if(shopProductById != null) {

            Map<String, BaseResponse> validation = inputValidation(shopProductById.getId(), updateData.getName(), updateData.getCategory().getIdList());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                updateData.setUrl(data.incrementUrl(TypeIncrement.ShopProduct, shopProductById.getId(), shopProductById.getCategory().getIdList(), "", shopProductById.getUrl()));
                updateData.setPathList(data.initializeMultiplePath(shopProductById.getCategory().getPathList(), shopProductById.getCategory().getUrlList()));

                for(String string : shopProductById.getImageList()) {

                    if(!updateData.getImageList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/shop/product", string);

                    }

                }

                for(String string : shopProductById.getThumbnailList()) {

                    if(!updateData.getThumbnailList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/shop/product/thumbnail", string);

                    }

                }

                shopProductLogDataRepository.save(data.initializeLogData("shopProductId", shopProductById, account, ShopProductLogData.class));

                updateData.setCreated(shopProductById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                shopProductRepository.save(updateData);

                userLogService.insert(request, "", "Update shop product id " + updateData.getId(), UserLogType.UpdateShopProduct, shopProductById.getId(), shopProductById.getName(), account);

                result.setResponse("Shop product updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Shop product doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
