package com.preloode.panel.controller.crm;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.controller.user.UserGroupController;
import com.preloode.panel.model.crm.CrmGroup;
import com.preloode.panel.model.crm.CrmGroupResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.model.user.UserGroup;
import com.preloode.panel.model.user.UserGroupResponse;
import com.preloode.panel.service.crm.CrmGroupService;
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
@RequestMapping(value = "/crm/group")
public class CrmGroupController {


    private static final Logger logger = LoggerFactory.getLogger(CrmGroupController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private CrmGroupService crmGroupService;

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

        global.initializeSetting(request, "CRM Group");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                page = 1;

                CrmGroupResponse crmGroupPagination = crmGroupService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(crmGroupPagination.isResult()) {

                    model.addAttribute("link", crmGroupPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", crmGroupPagination.getCrmGroupList());

                }

                logger.info("CRM group page initialized");

                return "crm/group";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                CrmGroupResponse crmGroupPagination = crmGroupService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(crmGroupPagination.isResult()) {

                    model.addAttribute("link", crmGroupPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", crmGroupPagination.getCrmGroupList());

                }

                logger.info("CRM group page initialized");

                return "crm/group";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new CrmGroup());

                logger.info("CRM group entry page initialized");

                return "crm/group-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", crmGroupService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("CRM group entry page initialized");

                return "crm/group-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public CrmGroupResponse initializePagination(HttpServletRequest request) {

        CrmGroupResponse result = new CrmGroupResponse() {
            {
                setResponse("Failed to initialize CRM group pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                result = crmGroupService.initializePagination(request, account, page);

            } else {

                result.setResponse("CRM privilege denied");

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
                "Failed to filter CRM group pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                result = crmGroupService.filterPagination(response, account, filter);

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
                "Failed to set CRM group pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                result = crmGroupService.setPagination(response, account, pagination);

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
                "Failed to remove filter CRM group pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                result = crmGroupService.removeFilterPagination(request, response, account);

            } else {

                result.setResponse("CRM privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/initialize-data")
    @ResponseBody
    public CrmGroupResponse initializeData(@RequestBody String id) {

        CrmGroupResponse result = new CrmGroupResponse() {
            {
                setResponse("Failed to initialize CRM group data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                result = crmGroupService.initializeData(account, id);

            } else {

                result.setResponse("CRM privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/insert")
    @ResponseBody
    public BaseResponse insert(HttpServletRequest request, @RequestBody CrmGroup crmGroup) {

        BaseResponse result = new BaseResponse(
                "Failed to insert CRM group",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                result = crmGroupService.insert(request, account, crmGroup);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody CrmGroup crmGroup) {

        BaseResponse result = new BaseResponse(
                "Failed to update CRM group",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                result = crmGroupService.update(request, account, crmGroup);

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
                "Failed to delete CRM group",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getCrmGroup());

            if(checkPrivilege.isResult()) {

                result = crmGroupService.delete(request, account, id);

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
