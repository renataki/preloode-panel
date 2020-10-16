package com.preloode.panel.service.blog;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.global.TypeIncrement;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.blog.BlogCategory;
import com.preloode.panel.model.blog.BlogCategoryLogData;
import com.preloode.panel.model.blog.BlogCategoryResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.UrlResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.blog.BlogCategoryLogDataRepository;
import com.preloode.panel.repository.blog.BlogCategoryRepository;
import com.preloode.panel.repository.global.GlobalRepository;
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
public class BlogCategoryService {


    private static final Logger logger = LoggerFactory.getLogger(BlogCategoryService.class);

    private static final String cookieFilter = "fltblgctg";

    private static final String cookiePagination = "pgnblgctg";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private FileComponent file;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private BlogCategoryLogDataRepository blogCategoryLogDataRepository;

    @Autowired
    private BlogCategoryRepository blogCategoryRepository;

    @Autowired
    private GlobalRepository globalRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete blog category",
                false
        );

        BlogCategory blogCategoryById = blogCategoryRepository.findById(id).orElse(null);

        if(blogCategoryById != null) {

            blogCategoryLogDataRepository.save(data.initializeLogData("blogCategoryId", blogCategoryById, account, BlogCategoryLogData.class));

            blogCategoryRepository.delete(blogCategoryById);

            for(String string : blogCategoryById.getImageList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/blog/category", string);

            }

            for(String string : blogCategoryById.getThumbnailList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/blog/category/thumbnail", string);

            }

            userLogService.insert(request, "", "Delete blog category id " + blogCategoryById.getId(), UserLogType.DeleteBlogCategory, blogCategoryById.getId(), blogCategoryById.getName(), account);

            result.setResponse("Blog category deleted");
            result.setResult(true);

        } else {

            result.setResponse("Blog category doesn't exist");

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
        result += initializeMenuTree(blogCategoryRepository.findByBranchIdListCompanyIdListStatusSort(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), Status.Active, Sort.by(sort)), "0", 0);

        result += "</ul>";

        logger.info("Blog category menu initialized");

        return result;

    }


    public String initializeSidebar(UrlResponse pathUrl) {

        String result = "<ul class=\"category-sidebar\">";

        pathUrl.setPath(pathUrl.getPath() + "/" + pathUrl.getUrl());

        if(pathUrl.getPath().startsWith("/")) {

            pathUrl.setPath(pathUrl.getPath().replaceFirst("/", ""));

        }

        List<BlogCategory> blogCategoryLikePathByStatus = blogCategoryRepository.findLikePathByBranchIdListCompanyIdListStatus("(?i)" + pathUrl.getPath() + "(?-i)", global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), Status.Active);

        String parentId = "0";

        if(!pathUrl.getPath().isEmpty()) {

            List<Sort.Order> sort = new ArrayList<>() {
                {
                    add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                    add(new Sort.Order(Sort.Direction.ASC, "name"));
                }
            };
            List<BlogCategory> blogCategoryByStatusUrl = blogCategoryRepository.findByStatusUrlSort(Status.Active, pathUrl.getUrl(), Sort.by(sort));

            if(!blogCategoryByStatusUrl.isEmpty()) {

                parentId = blogCategoryByStatusUrl.get(0).getId();

            }

        }

        result += initializeSidebarTree(blogCategoryLikePathByStatus, parentId, 0);

        result += "</ul>";

        logger.info("Blog category sidebar initialized");

        return result;

    }


    private String initializeSidebarTree(List<BlogCategory> blogCategoryList, String parentId, int level) {

        String result = "";

        for(BlogCategory blogCategory : blogCategoryList) {

            if(blogCategory.getParent().getId().equals(parentId)) {

                String url = global.getSetting().getUrl().getBase() + "/category/";

                if(!blogCategory.getPath().isEmpty()) {

                    url += blogCategory.getPath() + "/";

                }

                url += blogCategory.getUrl() + "/";

                result += "<li>" +
                        "<a href=\"" + url + "\">" +
                        "<p class=\"title\">" + blogCategory.getName() + "</p>" +
                        "</a>" +
                        "<p class=\"icon\"><i class=\"toggle-black square-10\"></i></p>";

                level++;

                if(level > 0) {

                    result += "<ul>";

                }

                result += initializeSidebarTree(blogCategoryList, blogCategory.getId(), level);

                if(level > 0) {

                    result += "</ul>";

                }

                result += "</li>";

                level--;

            }

        }

        logger.info("Blog category icon tree initialized");

        return result;

    }


    private String initializeMenuTree(List<BlogCategory> blogCategoryList, String parentId, int level) {

        String result = "";

        for(BlogCategory blogCategory : blogCategoryList) {

            if(blogCategory.getParent().getId().equals(parentId)) {

                String url = global.getSetting().getUrl().getBase() + "/category/";

                if(!blogCategory.getPath().isEmpty()) {

                    url += blogCategory.getPath() + "/";

                }

                url += blogCategory.getUrl() + "/";

                result += "<li>" +
                        "<a href=\"" + url + "\">" +
                        "<p class=\"title\">" + blogCategory.getName() + "</p>" +
                        "</a>";

                level++;

                if(level > 0) {

                    result += "<ul>";

                }

                result += initializeMenuTree(blogCategoryList, blogCategory.getId(), level);

                if(level > 0) {

                    result += "</ul>";

                }

                result += "</li>";

                level--;

            }

        }

        logger.info("Blog category tree initialized");

        return result;

    }


    public BlogCategoryResponse initializeData(User account, String id) {

        BlogCategoryResponse result = new BlogCategoryResponse() {
            {
                setResponse("Failed to initialize blog category data");
                setResult(false);
            }
        };

        result.setBlogCategory(blogCategoryRepository.findById(id).orElse(new BlogCategory()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setBlogCategoryList(blogCategoryRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Blog category data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private BlogCategory initializeInput(BlogCategory input) {

        BlogCategory result = input;

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

        logger.info("Blog category input initialized");

        return result;

    }


    public BlogCategoryResponse initializePagination(HttpServletRequest request, User account, int page) {

        BlogCategoryResponse result = new BlogCategoryResponse() {
            {
                setResponse("Failed to initialize blog category pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("Blog category pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name, String parentId) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("blogCategoryName", data.validateBlogCategoryName(id, name, parentId));
                put("blogCategoryParent", data.validateBlogCategoryParentId(parentId));
            }
        };

        logger.info("Blog category input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, BlogCategory insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert blog category",
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

            insertData.setUrl(data.incrementUrl(TypeIncrement.BlogCategory, "", new ArrayList<>(), insertData.getParent().getId(), insertData.getUrl()));
            insertData.setPath(insertData.getParent().getPath() + "/" + insertData.getParent().getUrl());

            if(insertData.getPath().startsWith("/")) {

                insertData.setPath(insertData.getPath().replaceFirst("/", ""));

            }

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            blogCategoryRepository.save(insertData);

            userLogService.insert(request, "", "Insert blog category id " + insertData.getId(), UserLogType.InsertBlogCategory, insertData.getId(), insertData.getName(), account);

            result.setResponse("Blog category inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public BlogCategory loadEntry(String id) {

        return blogCategoryRepository.findById(id).orElse(new BlogCategory());

    }


    public BlogCategoryResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        BlogCategoryResponse result = new BlogCategoryResponse() {
            {
                setResponse("Failed to load blog category pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setBlogCategoryList(globalRepository.findPagination(query, BlogCategory.class));
        result.setLink(data.initializePaginationLink(request, "/blog/category", page, size, result.getBlogCategoryList().size()));

        result.setResponse("Blog category pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, BlogCategory updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update blog category",
                false
        );

        updateData = initializeInput(updateData);

        BlogCategory blogCategoryById = blogCategoryRepository.findById(updateData.getId()).orElse(null);

        if(blogCategoryById != null) {

            Map<String, BaseResponse> validation = inputValidation(blogCategoryById.getId(), updateData.getName(), updateData.getParent().getId());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                updateData.setUrl(data.incrementUrl(TypeIncrement.BlogCategory, blogCategoryById.getId(), new ArrayList<>(), blogCategoryById.getParent().getId(), blogCategoryById.getUrl()));
                updateData.setPath(updateData.getParent().getPath() + "/" + updateData.getParent().getUrl());

                if(updateData.getPath().startsWith("/")) {

                    updateData.setPath(updateData.getPath().replaceFirst("/", ""));

                }

                for(String string : blogCategoryById.getImageList()) {

                    if(!updateData.getImageList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/blog/category", string);

                    }

                }

                for(String string : blogCategoryById.getThumbnailList()) {

                    if(!updateData.getThumbnailList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/blog/category/thumbnail", string);

                    }

                }

                blogCategoryLogDataRepository.save(data.initializeLogData("blogCategoryId", blogCategoryById, account, BlogCategoryLogData.class));

                updateData.setCreated(blogCategoryById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                blogCategoryRepository.save(updateData);

                userLogService.insert(request, "", "Update blog category id " + updateData.getId(), UserLogType.UpdateBlogCategory, blogCategoryById.getId(), blogCategoryById.getName(), account);

                result.setResponse("Blog category updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Blog category doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
