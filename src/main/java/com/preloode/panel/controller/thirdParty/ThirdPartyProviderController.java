package com.preloode.panel.controller.thirdParty;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.payment.PaymentMethod;
import com.preloode.panel.model.thirdParty.ThirdPartyProvider;
import com.preloode.panel.model.thirdParty.ThirdPartyProviderResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.thirdParty.ThirdPartyProviderService;
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
@RequestMapping(value = "/third-party/provider")
public class ThirdPartyProviderController {


    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyProviderController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private ThirdPartyProviderService thirdPartyProviderService;

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

        global.initializeSetting(request, "Third Party Provider");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                page = 1;

                ThirdPartyProviderResponse thirdPartyProviderPagination = thirdPartyProviderService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(thirdPartyProviderPagination.isResult()) {

                    model.addAttribute("link", thirdPartyProviderPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", thirdPartyProviderPagination.getThirdPartyProviderList());

                }

                logger.info("Third party provider page initialized");

                return "thirdParty/provider";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                ThirdPartyProviderResponse thirdPartyProviderPagination = thirdPartyProviderService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(thirdPartyProviderPagination.isResult()) {

                    model.addAttribute("link", thirdPartyProviderPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", thirdPartyProviderPagination.getThirdPartyProviderList());

                }

                logger.info("Third party provider page initialized");

                return "thirdParty/provider";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new PaymentMethod());

                logger.info("Payment method entry page initialized");

                return "thirdParty/provider-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", thirdPartyProviderService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("Third party provider entry page initialized");

                return "thirdParty/provider-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public ThirdPartyProviderResponse initializePagination(HttpServletRequest request) {

        ThirdPartyProviderResponse result = new ThirdPartyProviderResponse() {
            {
                setResponse("Failed to initialize third party provider pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                result = thirdPartyProviderService.initializePagination(request, account, page);

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
                "Failed to filter third party provider pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                result = thirdPartyProviderService.filterPagination(response, account, filter);

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
                "Failed to set third party provider pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                result = thirdPartyProviderService.setPagination(response, account, pagination);

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
                "Failed to remove filter third party provider pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                result = thirdPartyProviderService.removeFilterPagination(request, response, account);

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
    public ThirdPartyProviderResponse initializeData(@RequestBody String id) {

        ThirdPartyProviderResponse result = new ThirdPartyProviderResponse() {
            {
                setResponse("Failed to initialize third party provider data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                result = thirdPartyProviderService.initializeData(account, id);

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
    public BaseResponse insert(HttpServletRequest request, @RequestBody ThirdPartyProvider thirdPartyProvider) {

        BaseResponse result = new BaseResponse(
                "Failed to insert third party provider",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                result = thirdPartyProviderService.insert(request, account, thirdPartyProvider);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody ThirdPartyProvider thirdPartyProvider) {

        BaseResponse result = new BaseResponse(
                "Failed to update third party provider",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                result = thirdPartyProviderService.update(request, account, thirdPartyProvider);

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
                "Failed to delete third party provider",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getThirdPartyProvider());

            if(checkPrivilege.isResult()) {

                result = thirdPartyProviderService.delete(request, account, id);

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
