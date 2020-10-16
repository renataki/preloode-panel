package com.preloode.panel.controller.crm;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.controller.user.UserGroupController;
import com.preloode.panel.model.crm.CrmDatabaseSource;
import com.preloode.panel.model.crm.CrmDatabaseSourceResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.model.user.UserGroup;
import com.preloode.panel.model.user.UserGroupResponse;
import com.preloode.panel.service.crm.CrmDatabaseSourceService;
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
@RequestMapping(value = "/crm/database/source")
public class CrmDatabaseSourceController {


    private static final Logger logger = LoggerFactory.getLogger(CrmDatabaseSourceController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private CrmDatabaseSourceService crmDatabaseSourceService;

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

        global.initializeSetting(request, "CRM Database Source");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                page = 1;

                CrmDatabaseSourceResponse crmDatabaseSourcePagination = crmDatabaseSourceService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(crmDatabaseSourcePagination.isResult()) {

                    model.addAttribute("link", crmDatabaseSourcePagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", crmDatabaseSourcePagination.getCrmDatabaseSourceList());

                }

                logger.info("CRM database source page initialized");

                return "crm/database-source";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                CrmDatabaseSourceResponse crmDatabaseSourcePagination = crmDatabaseSourceService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(crmDatabaseSourcePagination.isResult()) {

                    model.addAttribute("link", crmDatabaseSourcePagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", crmDatabaseSourcePagination.getCrmDatabaseSourceList());

                }

                logger.info("CRM database source page initialized");

                return "crm/database-source";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new CrmDatabaseSource());

                logger.info("CRM database source entry page initialized");

                return "crm/database-source-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", crmDatabaseSourceService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("CRM database source entry page initialized");

                return "crm/database-source-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public CrmDatabaseSourceResponse initializePagination(HttpServletRequest request) {

        CrmDatabaseSourceResponse result = new CrmDatabaseSourceResponse() {
            {
                setResponse("Failed to initialize CRM database source pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseSourceService.initializePagination(request, account, page);

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
                "Failed to filter CRM database source pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseSourceService.filterPagination(response, account, filter);

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
                "Failed to set CRM database source pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseSourceService.setPagination(response, account, pagination);

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
                "Failed to remove filter CRM database source pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseSourceService.removeFilterPagination(request, response, account);

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
    public CrmDatabaseSourceResponse initializeData(@RequestBody String id) {

        CrmDatabaseSourceResponse result = new CrmDatabaseSourceResponse() {
            {
                setResponse("Failed to initialize CRM database source data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseSourceService.initializeData(account, id);

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
    public BaseResponse insert(HttpServletRequest request, @RequestBody CrmDatabaseSource crmDatabaseSource) {

        BaseResponse result = new BaseResponse(
                "Failed to insert CRM database source",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseSourceService.insert(request, account, crmDatabaseSource);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody CrmDatabaseSource crmDatabaseSource) {

        BaseResponse result = new BaseResponse(
                "Failed to update CRM database source",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseSourceService.update(request, account, crmDatabaseSource);

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
                "Failed to delete CRM database source",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getCrmDatabaseSource());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseSourceService.delete(request, account, id);

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
