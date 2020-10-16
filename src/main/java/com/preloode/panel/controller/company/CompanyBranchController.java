package com.preloode.panel.controller.company;

import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.FileSize;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.company.CompanyBranchResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.FileResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.company.CompanyBranchService;
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
@RequestMapping(value = "/company/branch")
public class CompanyBranchController {


    private static final Logger logger = LoggerFactory.getLogger(CompanyBranchController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private FileComponent file;

    @Autowired
    private CompanyBranchService companyBranchService;

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

        global.initializeSetting(request, "Company Branch");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                page = 1;

                CompanyBranchResponse companyBranchPagination = companyBranchService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(companyBranchPagination.isResult()) {

                    model.addAttribute("link", companyBranchPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", companyBranchPagination.getCompanyBranchList());

                }

                logger.info("Company branch page initialized");

                return "company/branch";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                CompanyBranchResponse companyBranchPagination = companyBranchService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(companyBranchPagination.isResult()) {

                    model.addAttribute("link", companyBranchPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", companyBranchPagination.getCompanyBranchList());

                }

                logger.info("Company branch page initialized");

                return "company/branch";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new CompanyBranch());

                logger.info("Company branch entry page initialized");

                return "company/branch-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", companyBranchService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("Company branch entry page initialized");

                return "company/branch-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public CompanyBranchResponse initializePagination(HttpServletRequest request) {

        CompanyBranchResponse result = new CompanyBranchResponse() {
            {
                setResponse("Failed to initialize company branch pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                result = companyBranchService.initializePagination(request, account, page);

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
                "Failed to filter company branch pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                result = companyBranchService.filterPagination(response, account, filter);

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
                "Failed to set company branch pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                result = companyBranchService.setPagination(response, account, pagination);

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
                "Failed to remove filter company branch pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                result = companyBranchService.removeFilterPagination(request, response, account);

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
    public CompanyBranchResponse initializeData(@RequestBody String id) {

        CompanyBranchResponse result = new CompanyBranchResponse() {
            {
                setResponse("Failed to initialize company branch data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                result = companyBranchService.initializeData(account, id);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/upload-image")
    @ResponseBody
    public FileResponse uploadImage(MultipartHttpServletRequest multipartRequest) {

        FileResponse result = new FileResponse() {
            {
                setResponse("Failed to upload company branch image");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                result = file.uploadResizeImage(multipartRequest, FileSize.Medium, "/setting");

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
    public BaseResponse insert(HttpServletRequest request, @RequestBody CompanyBranch companyBranch) {

        BaseResponse result = new BaseResponse(
                "Failed to insert company branch",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                result = companyBranchService.insert(request, account, companyBranch);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody CompanyBranch companyBranch) {

        BaseResponse result = new BaseResponse(
                "Failed to update company branch",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                result = companyBranchService.update(request, account, companyBranch);

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
                "Failed to delete company branch",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getCompanyBranch());

            if(checkPrivilege.isResult()) {

                result = companyBranchService.delete(request, account, id);

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
