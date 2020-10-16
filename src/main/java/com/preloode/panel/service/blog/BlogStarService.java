package com.preloode.panel.service.blog;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.global.TypeIncrement;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.blog.BlogStar;
import com.preloode.panel.model.blog.BlogStarLogData;
import com.preloode.panel.model.blog.BlogStarResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.blog.BlogCategoryRepository;
import com.preloode.panel.repository.blog.BlogStarLogDataRepository;
import com.preloode.panel.repository.blog.BlogStarRepository;
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
public class BlogStarService {


    private static final Logger logger = LoggerFactory.getLogger(BlogStarService.class);

    private static final String cookieFilter = "fltblgstr";

    private static final String cookiePagination = "pgnblgstr";

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
    private BlogCategoryRepository blogCategoryRepository;

    @Autowired
    private BlogStarLogDataRepository blogStarLogDataRepository;

    @Autowired
    private BlogStarRepository blogStarRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete blog star",
                false
        );

        BlogStar blogStarById = blogStarRepository.findById(id).orElse(null);

        if(blogStarById != null) {

            blogStarLogDataRepository.save(data.initializeLogData("blogStarId", blogStarById, account, BlogStarLogData.class));

            blogStarRepository.delete(blogStarById);

            for(String string : blogStarById.getImageList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/blog/star", string);

            }

            for(String string : blogStarById.getThumbnailList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/blog/star/thumbnail", string);

            }

            userLogService.insert(request, "", "Delete blog star id " + blogStarById.getId(), UserLogType.DeleteBlogStar, blogStarById.getId(), blogStarById.getName(), account);

            result.setResponse("Blog star deleted");
            result.setResult(true);

        } else {

            result.setResponse("Blog star doesn't exist");

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
        List<BlogStar> blogStarByStatusSort = blogStarRepository.findByBranchIdListCompanyIdListStatusSort(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), Status.Active, Sort.by(sort));

        if(!blogStarByStatusSort.isEmpty()) {

            for(BlogStar blogStar : blogStarByStatusSort) {

                String url = global.getSetting().getUrl().getBase() + "/star/";

                if(!blogStar.getPath().isEmpty()) {

                    url += blogStar.getPath() + "/";

                }

                url += blogStar.getUrl() + "/";

                result += "<li>" +
                        "<a href=\"" + url + "\">" +
                        "<p class=\"title\">" + blogStar.getName() + "</p>" +
                        "</a>" +
                        "</li>";

            }

        }

        result += "</ul>";

        logger.info("Blog star menu initialized");

        return result;

    }


    public BlogStarResponse initializeData(User account, String id) {

        BlogStarResponse result = new BlogStarResponse() {
            {
                setResponse("Failed to initialize blog star data");
                setResult(false);
            }
        };

        result.setBlogStar(blogStarRepository.findById(id).orElse(new BlogStar()));

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

        result.setResponse("Blog star data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private BlogStar initializeInput(BlogStar input) {

        BlogStar result = input;

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

        logger.info("Blog star input initialized");

        return result;

    }


    public BlogStarResponse initializePagination(HttpServletRequest request, User account, int page) {

        BlogStarResponse result = new BlogStarResponse() {
            {
                setResponse("Failed to initialize blog star pagination");
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
        result.setBlogCategoryList(blogCategoryRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Blog star pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("blogStarName", data.validateBlogStarName(id, name));
            }
        };

        logger.info("Blog star input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, BlogStar insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert blog star",
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

            insertData.setUrl(data.incrementUrl(TypeIncrement.BlogStar, "", new ArrayList<>(), "", insertData.getUrl()));

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            blogStarRepository.save(insertData);

            userLogService.insert(request, "", "Insert blog star id " + insertData.getId(), UserLogType.InsertBlogStar, insertData.getId(), insertData.getName(), account);

            result.setResponse("Blog star inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public BlogStar loadEntry(String id) {

        return blogStarRepository.findById(id).orElse(new BlogStar());

    }


    public BlogStarResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        BlogStarResponse result = new BlogStarResponse() {
            {
                setResponse("Failed to load blog star pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setBlogStarList(globalRepository.findPagination(query, BlogStar.class));
        result.setLink(data.initializePaginationLink(request, "/blog/star", page, size, result.getBlogStarList().size()));

        result.setResponse("Blog star pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, BlogStar updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update blog star",
                false
        );

        updateData = initializeInput(updateData);

        BlogStar blogStarById = blogStarRepository.findById(updateData.getId()).orElse(null);

        if(blogStarById != null) {

            Map<String, BaseResponse> validation = inputValidation(blogStarById.getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                updateData.setUrl(data.incrementUrl(TypeIncrement.BlogStar, blogStarById.getId(), new ArrayList<String>(), "", updateData.getUrl()));

                for(String string : blogStarById.getImageList()) {

                    if(!updateData.getImageList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/blog/star", string);

                    }

                }

                for(String string : blogStarById.getThumbnailList()) {

                    if(!updateData.getThumbnailList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/blog/star/thumbnail", string);

                    }

                }

                blogStarLogDataRepository.save(data.initializeLogData("blogStarId", blogStarById, account, BlogStarLogData.class));

                updateData.setCreated(blogStarById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                blogStarRepository.save(updateData);

                userLogService.insert(request, "", "Update blog star id " + updateData.getId(), UserLogType.UpdateBlogStar, blogStarById.getId(), blogStarById.getName(), account);

                result.setResponse("Blog star updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Blog star doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
