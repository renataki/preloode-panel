package com.preloode.panel.service.blog;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.blog.BlogType;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.global.TypeIncrement;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.blog.BlogPost;
import com.preloode.panel.model.blog.BlogPostLogData;
import com.preloode.panel.model.blog.BlogPostResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.UrlResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.blog.BlogCategoryRepository;
import com.preloode.panel.repository.blog.BlogPostLogDataRepository;
import com.preloode.panel.repository.blog.BlogPostRepository;
import com.preloode.panel.repository.blog.BlogStarRepository;
import com.preloode.panel.repository.global.GlobalRepository;
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
public class BlogPostService {


    private static final Logger logger = LoggerFactory.getLogger(BlogPostService.class);

    private static final String cookieFilter = "fltblgpst";

    private static final String cookiePagination = "pgnblgpst";

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
    private BlogStarRepository blogStarRepository;

    @Autowired
    private BlogPostLogDataRepository blogPostLogDataRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete shop product",
                false
        );

        BlogPost blogPostById = blogPostRepository.findById(id).orElse(null);

        if(blogPostById != null) {

            blogPostLogDataRepository.save(data.initializeLogData("blogPostId", blogPostById, account, BlogPostLogData.class));

            blogPostRepository.delete(blogPostById);

            for(String string : blogPostById.getImageList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/blog/post", string);

            }

            for(String string : blogPostById.getThumbnailList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/blog/post/thumbnail", string);

            }

            userLogService.insert(request, "", "Delete blog post id " + blogPostById.getId(), UserLogType.DeleteBlogPost, blogPostById.getId(), blogPostById.getTitle(), account);

            result.setResponse("Blog post deleted");
            result.setResult(true);

        } else {

            result.setResponse("Blog post doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public BlogPostResponse initializeData(User account, String id) {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to initialize blog post data");
                setResult(false);
            }
        };

        result.setBlogPost(blogPostRepository.findById(id).orElse(new BlogPost()));

        result.setBlogTypeList(Stream.of(BlogType.values()).collect(Collectors.toList()));
        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setBlogStarList(blogStarRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setBlogCategoryList(blogCategoryRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Blog post data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private BlogPost initializeInput(BlogPost input) {

        BlogPost result = input;

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

            result.setUrl(result.getTitle().replaceAll("[^A-Za-z0-9- ]", "").replaceAll(" ", "-").toLowerCase());

        }

        if(!result.getUrl().substring(result.getUrl().length() - 1).equals("/")) {

            result.setUrl(result.getUrl() + "/");

        }

        if(result.getView() == null) {

            result.setView(BigInteger.ZERO);

        }

        logger.info("Blog post input initialized");

        return result;

    }


    public BlogPostResponse initializePagination(HttpServletRequest request, User account, int page) {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to initialize blog post pagination");
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
        result.setBlogStarList(blogStarRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setBlogCategoryList(blogCategoryRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Blog post pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, List<String> categoryId, String title) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("blogPostTitle", data.validateBlogPostTitle(id, title, new HashSet(categoryId)));
            }
        };

        logger.info("Blog post input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, BlogPost insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert blog post",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getCategory().getIdList(), insertData.getTitle());

        boolean valid = true;

        for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

            if(!map.getValue().isResult()) {

                valid = false;

                result.setResponse(map.getValue().getResponse());

                break;

            }

        }

        if(valid) {

            insertData.setUrl(data.incrementUrl(TypeIncrement.BlogPost, "", insertData.getCategory().getIdList(), "", insertData.getUrl()));
            insertData.setPathList(data.initializeMultiplePath(insertData.getCategory().getPathList(), insertData.getCategory().getUrlList()));

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            blogPostRepository.save(insertData);

            userLogService.insert(request, "", "Insert blog post id " + insertData.getId(), UserLogType.InsertBlogPost, insertData.getId(), insertData.getTitle(), account);

            result.setResponse("Blog post inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public BlogPostResponse loadBranchIdListCompanyIdListFeatured() {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to load blog post branch id list company id list featured");
                setResult(false);
            }
        };

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.DESC, "modified.timestamp"));
            }
        };
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(sort));
        result.setBlogPostList(blogPostRepository.findByBranchIdListCompanyIdListFeaturedStatusPageable(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), true, Status.Active, pageRequest));

        result.setResponse("Blog post branch id list company id list featured loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BlogPostResponse loadBranchIdListCompanyIdListLatest() {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to load blog post branch id list company id list latest");
                setResult(false);
            }
        };

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.DESC, "modified.timestamp"));
            }
        };
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(sort));
        result.setBlogPostList(blogPostRepository.findByBranchIdListCompanyIdListStatusPageable(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), Status.Active, pageRequest));

        result.setResponse("Blog post branch id list company id list latest loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BlogPost loadEntry(String id) {

        return blogPostRepository.findById(id).orElse(new BlogPost());

    }


    public BlogPostResponse loadEntryByBranchIdListCompanyIdListPathListStatusUrl(UrlResponse url) {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to load blog post entry by branch id list company id list path list status url");
                setResult(false);
                setBlogPost(new BlogPost());
            }
        };

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "created.timestamp"));
            }
        };
        List<BlogPost> blogPostByBranchIdListCompanyIdListPathStatusUrl = blogPostRepository.findByBranchIdListCompanyIdListPathListStatusUrlSort(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), url.getPath(), Status.Active, url.getUrl(), Sort.by(sort));

        if(!blogPostByBranchIdListCompanyIdListPathStatusUrl.isEmpty()) {

            result.setBlogPost(blogPostByBranchIdListCompanyIdListPathStatusUrl.get(0));

            result.setResponse("Blog post entry by branch id list company id list  path list status url loaded");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public BlogPostResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to load blog post pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setBlogPostList(globalRepository.findPagination(query, BlogPost.class));
        result.setLink(data.initializePaginationLink(request, "/blog/post", page, size, result.getBlogPostList().size()));

        result.setResponse("Blog post pagination loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BlogPostResponse loadPaginationByBranchIdListCompanyIdListStarName(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, UrlResponse pathUrl, int page) {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to load blog post pagination by branch id list company id list star name");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));
        query.addCriteria(Criteria.where("branch.idList").is(global.getSetting().getCompanyBranch()));
        query.addCriteria(Criteria.where("company.idList").is(global.getSetting().getCompany()));

        if(!pathUrl.getUrl().isEmpty()) {

            query.addCriteria(Criteria.where("star.name").is(pathUrl.getUrl()));

        }

        result.setBlogPostList(globalRepository.findPagination(query, BlogPost.class));

        pathUrl.setUrl("/star");

        if(!pathUrl.getPath().isEmpty()) {

            pathUrl.setUrl(pathUrl.getUrl() + "/" + pathUrl.getPath());

        }

        result.setLink(data.initializePaginationLink(request, pathUrl.getUrl(), page, size, result.getBlogPostList().size()));

        result.setResponse("Blog post pagination by branch id list company id list star name loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BlogPostResponse loadPaginationByBranchIdListCompanyIdList(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to load blog post pagination by branch id list company id list");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));
        query.addCriteria(Criteria.where("branch.idList").is(global.getSetting().getCompanyBranch()));
        query.addCriteria(Criteria.where("company.idList").is(global.getSetting().getCompany()));

        result.setBlogPostList(globalRepository.findPagination(query, BlogPost.class));
        result.setLink(data.initializePaginationLink(request, "/blog/post", page, size, result.getBlogPostList().size()));

        result.setResponse("Blog post pagination by branch id list company id list loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BlogPostResponse loadPaginationByBranchIdListCompanyIdListPath(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, UrlResponse pathUrl, int page) {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to load blog post pagination by branch id list company id list path");
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

        result.setBlogPostList(globalRepository.findPagination(query, BlogPost.class));

        if(!path.isEmpty()) {

            path = "/category/" + path;

        } else {

            path = "/category";

        }

        result.setLink(data.initializePaginationLink(request, path, page, size, result.getBlogPostList().size()));

        result.setResponse("Blog post pagination by branch id list company id list path loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, BlogPost updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update blog post",
                false
        );

        updateData = initializeInput(updateData);

        BlogPost blogPostById = blogPostRepository.findById(updateData.getId()).orElse(null);

        if(blogPostById != null) {

            Map<String, BaseResponse> validation = inputValidation(blogPostById.getId(), updateData.getCategory().getIdList(), updateData.getTitle());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                updateData.setUrl(data.incrementUrl(TypeIncrement.BlogPost, blogPostById.getId(), blogPostById.getCategory().getIdList(), "", blogPostById.getUrl()));
                updateData.setPathList(data.initializeMultiplePath(blogPostById.getCategory().getPathList(), blogPostById.getCategory().getUrlList()));

                for(String string : blogPostById.getImageList()) {

                    if(!updateData.getImageList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/blog/post", string);

                    }

                }

                for(String string : blogPostById.getThumbnailList()) {

                    if(!updateData.getThumbnailList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/blog/post/thumbnail", string);

                    }

                }

                blogPostLogDataRepository.save(data.initializeLogData("blogPostId", blogPostById, account, BlogPostLogData.class));

                updateData.setCreated(blogPostById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                blogPostRepository.save(updateData);

                userLogService.insert(request, "", "Update blog post id " + updateData.getId(), UserLogType.UpdateBlogPost, blogPostById.getId(), blogPostById.getTitle(), account);

                result.setResponse("Blog post updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Blog post doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
