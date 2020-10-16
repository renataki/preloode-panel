package com.preloode.panel.controller.crm;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.crm.CrmDatabase;
import com.preloode.panel.model.crm.CrmDatabaseResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.payment.PaymentAccountResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.crm.CrmDatabaseService;
import com.preloode.panel.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@Controller
@RequestMapping(value = "/crm/database")
public class CrmDatabaseController {


    private static final Logger logger = LoggerFactory.getLogger(CrmDatabaseController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private CrmDatabaseService crmDatabaseService;

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

        global.initializeSetting(request, "CRM Database");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                page = 1;

                CrmDatabaseResponse crmDatabasePagination = crmDatabaseService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(crmDatabasePagination.isResult()) {

                    model.addAttribute("link", crmDatabasePagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", crmDatabasePagination.getCrmDatabaseList());

                }

                logger.info("CRM database page initialized");

                return "crm/database";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                CrmDatabaseResponse crmDatabasePagination = crmDatabaseService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(crmDatabasePagination.isResult()) {

                    model.addAttribute("link", crmDatabasePagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", crmDatabasePagination.getCrmDatabaseList());

                }

                logger.info("CRM database page initialized");

                return "crm/database";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new CrmDatabase());

                logger.info("CRM database entry page initialized");

                return "crm/database-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", crmDatabaseService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("CRM database entry page initialized");

                return "crm/database-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public CrmDatabaseResponse initializePagination(HttpServletRequest request) {

        CrmDatabaseResponse result = new CrmDatabaseResponse() {
            {
                setResponse("Failed to initialize CRM database pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseService.initializePagination(request, account, page);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/import-database", method = RequestMethod.POST)
    @ResponseBody
    public CrmDatabaseResponse importDatabase(MultipartHttpServletRequest multipartRequest) {

        CrmDatabaseResponse result = new CrmDatabaseResponse() {
            {
                setResponse("Failed to import CRM database file");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseService.importDatabase(multipartRequest, account);

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
                "Failed to filter CRM database pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseService.filterPagination(response, account, filter);

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
                "Failed to set CRM database pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseService.setPagination(response, account, pagination);

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
                "Failed to remove filter CRM database pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseService.removeFilterPagination(request, response, account);

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
    public CrmDatabaseResponse initializeData(@RequestBody String id) {

        CrmDatabaseResponse result = new CrmDatabaseResponse() {
            {
                setResponse("Failed to initialize CRM database data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseService.initializeData(account, id);

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
    public BaseResponse insert(HttpServletRequest request, @RequestBody CrmDatabase crmDatabase) {

        BaseResponse result = new BaseResponse(
                "Failed to insert CRM database",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseService.insert(request, account, crmDatabase);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody CrmDatabase crmDatabase) {

        BaseResponse result = new BaseResponse(
                "Failed to update CRM database",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseService.update(request, account, crmDatabase);

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
                "Failed to delete CRM database",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getCrmDatabase());

            if(checkPrivilege.isResult()) {

                result = crmDatabaseService.delete(request, account, id);

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
