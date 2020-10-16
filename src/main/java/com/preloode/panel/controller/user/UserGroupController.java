package com.preloode.panel.controller.user;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.model.user.UserGroup;
import com.preloode.panel.model.user.UserGroupResponse;
import com.preloode.panel.service.user.UserGroupService;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
@RequestMapping(value = "/user/group")
public class UserGroupController {


    private static final Logger logger = LoggerFactory.getLogger(UserGroupController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private UserGroupService userGroupService;

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

        global.initializeSetting(request, "User Group");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                page = 1;

                UserGroupResponse userGroupPagination = userGroupService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(userGroupPagination.isResult()) {

                    model.addAttribute("link", userGroupPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", userGroupPagination.getUserGroupList());

                }

                logger.info("User group page initialized");

                return "user/group";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                UserGroupResponse userGroupPagination = userGroupService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(userGroupPagination.isResult()) {

                    model.addAttribute("link", userGroupPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", userGroupPagination.getUserGroupList());

                }

                logger.info("User group page initialized");

                return "user/group";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new UserGroup());

                logger.info("User group entry page initialized");

                return "user/group-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", userGroupService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("User group entry page initialized");

                return "user/group-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public UserGroupResponse initializePagination(HttpServletRequest request) {

        UserGroupResponse result = new UserGroupResponse() {
            {
                setResponse("Failed to initialize user group pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                result = userGroupService.initializePagination(request, account, page);

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
                "Failed to filter user group pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                result = userGroupService.filterPagination(response, account, filter);

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
                "Failed to set user group pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                result = userGroupService.setPagination(response, account, pagination);

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
                "Failed to remove filter user group pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                result = userGroupService.removeFilterPagination(request, response, account);

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
    public UserGroupResponse initializeData(@RequestBody String id) {

        UserGroupResponse result = new UserGroupResponse() {
            {
                setResponse("Failed to initialize user group data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                result = userGroupService.initializeData(account, id);

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
    public BaseResponse insert(HttpServletRequest request, @RequestBody UserGroup userGroup) {

        BaseResponse result = new BaseResponse(
                "Failed to insert user group",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                result = userGroupService.insert(request, account, userGroup);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody UserGroup userGroup) {

        BaseResponse result = new BaseResponse(
                "Failed to update user group",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                result = userGroupService.update(request, account, userGroup);

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
                "Failed to delete user group",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getUserGroup());

            if(checkPrivilege.isResult()) {

                result = userGroupService.delete(request, account, id);

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
