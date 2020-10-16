package com.preloode.panel.controller.crm;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.crm.CrmDatabase;
import com.preloode.panel.model.crm.CrmDatabaseResponse;
import com.preloode.panel.model.crm.CrmPhoneResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.crm.CrmPhoneService;
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
@RequestMapping(value = "/crm/phone")
public class CrmPhoneController {


    private static final Logger logger = LoggerFactory.getLogger(CrmPhoneController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private CrmPhoneService crmPhoneService;

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

        global.initializeSetting(request, "CRM Phone");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmPhone());

            if(checkPrivilege.isResult()) {

                page = 1;

                CrmDatabaseResponse crmDatabasePagination = crmPhoneService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(crmDatabasePagination.isResult()) {

                    model.addAttribute("link", crmDatabasePagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", crmDatabasePagination.getCrmDatabaseList());

                }

                logger.info("CRM phone page initialized");

                return "crm/phone";

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

                CrmDatabaseResponse crmDatabasePagination = crmPhoneService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(crmDatabasePagination.isResult()) {

                    model.addAttribute("link", crmDatabasePagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", crmDatabasePagination.getCrmDatabaseList());

                }

                logger.info("CRM phone page initialized");

                return "crm/phone";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCrmPhone());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new CrmDatabase());

                logger.info("CRM phone entry page initialized");

                return "crm/phone-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCrmPhone());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", crmPhoneService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("CRM phone entry page initialized");

                return "crm/phone-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/call", method = RequestMethod.GET)
    public String call(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCrmPhoneCall());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", crmPhoneService.loadEntryCall(account).getCrmDatabase());

                logger.info("CRM phone call page initialized");

                return "crm/phone-call";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public CrmPhoneResponse initializePagination(HttpServletRequest request) {

        CrmPhoneResponse result = new CrmPhoneResponse() {
            {
                setResponse("Failed to initialize CRM phone pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmPhone());

            if(checkPrivilege.isResult()) {

                result = crmPhoneService.initializePagination(request, account, page);

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmPhone());

            if(checkPrivilege.isResult()) {

                result = crmPhoneService.filterPagination(response, account, filter);

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
                "Failed to set CRM phone pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmPhone());

            if(checkPrivilege.isResult()) {

                result = crmPhoneService.setPagination(response, account, pagination);

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
                "Failed to remove filter CRM phone pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmPhone());

            if(checkPrivilege.isResult()) {

                result = crmPhoneService.removeFilterPagination(request, response, account);

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
    public CrmPhoneResponse initializeData(@RequestBody String id) {

        CrmPhoneResponse result = new CrmPhoneResponse() {
            {
                setResponse("Failed to initialize CRM phone data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmPhone());

            if(checkPrivilege.isResult()) {

                result = crmPhoneService.initializeData(account, id);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/call/initialize-data")
    @ResponseBody
    public CrmPhoneResponse initializeCallData(@RequestBody String id) {

        CrmPhoneResponse result = new CrmPhoneResponse() {
            {
                setResponse("Failed to initialize CRM phone call data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCrmPhoneCall());

            if(checkPrivilege.isResult()) {

                result = crmPhoneService.initializeCallData(id);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/update-status")
    @ResponseBody
    public BaseResponse update(HttpServletRequest request, @RequestBody CrmDatabase crmDatabase) {

        BaseResponse result = new BaseResponse(
                "Failed to update CRM database status",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCrmPhoneCall());

            if(checkPrivilege.isResult()) {

                result = crmPhoneService.updateStatus(request, account, crmDatabase);

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
