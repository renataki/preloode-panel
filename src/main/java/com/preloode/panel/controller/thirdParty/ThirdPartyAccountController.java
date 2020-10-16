package com.preloode.panel.controller.thirdParty;

import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.thirdParty.ThirdPartyAccount;
import com.preloode.panel.model.thirdParty.ThirdPartyAccountResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.thirdParty.ThirdPartyAccountService;
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
@RequestMapping(value = "/third-party/account")
public class ThirdPartyAccountController {


    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyAccountController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private ThirdPartyAccountService thirdPartyAccountService;

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

        global.initializeSetting(request, "Third Party Account");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                page = 1;

                ThirdPartyAccountResponse thirdPartyAccountPagination = thirdPartyAccountService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(thirdPartyAccountPagination.isResult()) {

                    model.addAttribute("link", thirdPartyAccountPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", thirdPartyAccountPagination.getThirdPartyAccountList());

                }

                logger.info("Third party account page initialized");

                return "thirdParty/account";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                ThirdPartyAccountResponse thirdPartyAccountPagination = thirdPartyAccountService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(thirdPartyAccountPagination.isResult()) {

                    model.addAttribute("link", thirdPartyAccountPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", thirdPartyAccountPagination.getThirdPartyAccountList());

                }

                logger.info("Third party account page initialized");

                return "thirdParty/account";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new ThirdPartyAccount());

                logger.info("Third party account entry page initialized");

                return "thirdParty/account-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", thirdPartyAccountService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("Third party account entry page initialized");

                return "thirdParty/account-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public ThirdPartyAccountResponse initializePagination(HttpServletRequest request) {

        ThirdPartyAccountResponse result = new ThirdPartyAccountResponse() {
            {
                setResponse("Failed to initialize third party account pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                result = thirdPartyAccountService.initializePagination(request, account, page);

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
                "Failed to filter third party account pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                result = thirdPartyAccountService.filterPagination(response, account, filter);

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
                "Failed to set third party account pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                result = thirdPartyAccountService.setPagination(response, account, pagination);

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
                "Failed to remove filter third party account pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                result = thirdPartyAccountService.removeFilterPagination(request, response, account);

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
    public ThirdPartyAccountResponse initializeData(@RequestBody String id) {

        ThirdPartyAccountResponse result = new ThirdPartyAccountResponse() {
            {
                setResponse("Failed to initialize third party account data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                result = thirdPartyAccountService.initializeData(account, id);

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
    public BaseResponse insert(HttpServletRequest request, @RequestBody ThirdPartyAccount thirdPartyAccount) {

        BaseResponse result = new BaseResponse(
                "Failed to insert third party account",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                result = thirdPartyAccountService.insert(request, account, thirdPartyAccount);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody ThirdPartyAccount thirdPartyAccount) {

        BaseResponse result = new BaseResponse(
                "Failed to update third party account",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                result = thirdPartyAccountService.update(request, account, thirdPartyAccount);

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
                "Failed to delete third party account",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getThirdPartyAccount());

            if(checkPrivilege.isResult()) {

                result = thirdPartyAccountService.delete(request, account, id);

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
