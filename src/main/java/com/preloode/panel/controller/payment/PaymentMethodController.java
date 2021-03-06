package com.preloode.panel.controller.payment;

import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.FileSize;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.FileResponse;
import com.preloode.panel.model.payment.PaymentMethod;
import com.preloode.panel.model.payment.PaymentMethodResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.payment.PaymentMethodService;
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
@RequestMapping(value = "/payment/method")
public class PaymentMethodController {


    private static final Logger logger = LoggerFactory.getLogger(PaymentMethodController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private FileComponent file;

    @Autowired
    private PaymentMethodService paymentMethodService;

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

        global.initializeSetting(request, "Payment Method");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                page = 1;

                PaymentMethodResponse paymentMethodPagination = paymentMethodService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(paymentMethodPagination.isResult()) {

                    model.addAttribute("link", paymentMethodPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", paymentMethodPagination.getPaymentMethodList());

                }

                logger.info("Payment method page initialized");

                return "payment/method";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                PaymentMethodResponse paymentMethodPagination = paymentMethodService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(paymentMethodPagination.isResult()) {

                    model.addAttribute("link", paymentMethodPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", paymentMethodPagination.getPaymentMethodList());

                }

                logger.info("Payment method page initialized");

                return "payment/method";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new PaymentMethod());

                logger.info("Payment method entry page initialized");

                return "payment/method-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", paymentMethodService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("Payment method entry page initialized");

                return "payment/method-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public PaymentMethodResponse initializePagination(HttpServletRequest request) {

        PaymentMethodResponse result = new PaymentMethodResponse() {
            {
                setResponse("Failed to initialize payment method pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                result = paymentMethodService.initializePagination(request, account, page);

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
                "Failed to filter payment method pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                result = paymentMethodService.filterPagination(response, account, filter);

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
                "Failed to set payment method pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                result = paymentMethodService.setPagination(response, account, pagination);

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
                "Failed to remove filter payment method pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                result = paymentMethodService.removeFilterPagination(request, response, account);

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
    public PaymentMethodResponse initializeData(@RequestBody String id) {

        PaymentMethodResponse result = new PaymentMethodResponse() {
            {
                setResponse("Failed to initialize payment method data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                result = paymentMethodService.initializeData(account, id);

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
                setResponse("Failed to upload payment method image");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                result = file.uploadResizeImage(multipartRequest, FileSize.Large, "/payment/method");

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
    public BaseResponse insert(HttpServletRequest request, @RequestBody PaymentMethod paymentMethod) {

        BaseResponse result = new BaseResponse(
                "Failed to insert payment method",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                result = paymentMethodService.insert(request, account, paymentMethod);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody PaymentMethod paymentMethod) {

        BaseResponse result = new BaseResponse(
                "Failed to update payment method",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                result = paymentMethodService.update(request, account, paymentMethod);

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
                "Failed to delete payment method",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getPaymentMethod());

            if(checkPrivilege.isResult()) {

                result = paymentMethodService.delete(request, account, id);

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
