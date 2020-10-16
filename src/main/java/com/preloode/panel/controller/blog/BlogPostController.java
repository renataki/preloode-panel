package com.preloode.panel.controller.blog;

import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.FileSize;
import com.preloode.panel.model.blog.BlogPost;
import com.preloode.panel.model.blog.BlogPostResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.FileResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.blog.BlogPostService;
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
@RequestMapping(value = "/blog/post")
public class BlogPostController {


    private static final Logger logger = LoggerFactory.getLogger(BlogPostController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private FileComponent file;

    @Autowired
    private BlogPostService blogPostService;

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

        global.initializeSetting(request, "Blog Post");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                page = 1;

                BlogPostResponse blogPostPagination = blogPostService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(blogPostPagination.isResult()) {

                    model.addAttribute("link", blogPostPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", blogPostPagination.getBlogPostList());

                }

                logger.info("Blog post page initialized");

                return "blog/post";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                BlogPostResponse blogPostPagination = blogPostService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(blogPostPagination.isResult()) {

                    model.addAttribute("link", blogPostPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", blogPostPagination.getBlogPostList());

                }

                logger.info("Blog post page initialized");

                return "blog/post";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new BlogPost());

                logger.info("Blog post entry page initialized");

                return "blog/post-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", blogPostService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("Blog post entry page initialized");

                return "blog/post-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public BlogPostResponse initializePagination(HttpServletRequest request) {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to initialize blog post pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = blogPostService.initializePagination(request, account, page);

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
                "Failed to filter blog post pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = blogPostService.filterPagination(response, account, filter);

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
                "Failed to set blog post pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = blogPostService.setPagination(response, account, pagination);

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
                "Failed to remove filter blog post pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = blogPostService.removeFilterPagination(request, response, account);

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
    public BlogPostResponse initializeData(@RequestBody String id) {

        BlogPostResponse result = new BlogPostResponse() {
            {
                setResponse("Failed to initialize blog post data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = blogPostService.initializeData(account, id);

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
                setResponse("Failed to upload blog post image list");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = file.uploadResizeImage(multipartRequest, FileSize.Large, "/blog/post");

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
                setResponse("Failed to upload blog post thumbnail list");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = file.uploadResizeImage(multipartRequest, FileSize.Medium, "/blog/post/thumbnail");

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
                setResponse("Failed to upload blog post tinymce");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = file.uploadResizeTinymceImage(multipartRequest, "/blog/post/tinymce");

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
    public BaseResponse insert(HttpServletRequest request, @RequestBody BlogPost blogPost) {

        BaseResponse result = new BaseResponse(
                "Failed to insert blog post",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = blogPostService.insert(request, account, blogPost);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody BlogPost blogPost) {

        BaseResponse result = new BaseResponse(
                "Failed to update blog post",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = blogPostService.update(request, account, blogPost);

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
                "Failed to delete blog post",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getBlogPost());

            if(checkPrivilege.isResult()) {

                result = blogPostService.delete(request, account, id);

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
