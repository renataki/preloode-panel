package com.preloode.panel.controller.payment;

import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.FileSize;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.FileResponse;
import com.preloode.panel.model.payment.PaymentAccount;
import com.preloode.panel.model.payment.PaymentAccountResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.service.payment.PaymentAccountService;
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
@RequestMapping(value = "/payment/account")
public class PaymentAccountController {


    private static final Logger logger = LoggerFactory.getLogger(PaymentAccountController.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private FileComponent file;

    @Autowired
    private PaymentAccountService paymentAccountService;

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

        global.initializeSetting(request, "Payment Account");

        return global;

    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                page = 1;

                PaymentAccountResponse paymentAccountPagination = paymentAccountService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(paymentAccountPagination.isResult()) {

                    model.addAttribute("link", paymentAccountPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", paymentAccountPagination.getPaymentAccountList());

                }

                logger.info("Payment account page initialized");

                return "payment/account";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                PaymentAccountResponse paymentAccountPagination = paymentAccountService.loadPagination(request, account, Sort.Direction.DESC, "created.timestamp", page);

                if(paymentAccountPagination.isResult()) {

                    model.addAttribute("link", paymentAccountPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", paymentAccountPagination.getPaymentAccountList());

                }

                logger.info("Payment account page initialized");

                return "payment/account";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", new PaymentAccount());

                logger.info("Payment account entry page initialized");

                return "payment/account-entry";

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

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                model.addAttribute("entry", paymentAccountService.loadEntry(id.replaceAll("[^A-Za-z0-9]", "")));

                logger.info("Payment account entry page initialized");

                return "payment/account-entry";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/mutation/{id}", method = RequestMethod.GET)
    public String mutation(HttpServletRequest request, @PathVariable("id") String id, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                page = 1;

                PaymentAccountResponse paymentAccountMutationPagination = paymentAccountService.loadMutationPagination(request, account, id.replaceAll("[^A-Za-z0-9]", ""), page);

                if(paymentAccountMutationPagination.isResult()) {

                    model.addAttribute("link", paymentAccountMutationPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", paymentAccountMutationPagination.getTransactionList());

                }

                logger.info("Payment account mutation page initialized");

                return "payment/account-mutation";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/mutation/{id}/{page:page-[\\d]}", method = RequestMethod.GET)
    public String mutationPage(HttpServletRequest request, @PathVariable("id") String id, @PathVariable("page") String pagePath, Model model) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                page = Integer.parseInt(pagePath.replaceAll("[^0-9]", ""));

                PaymentAccountResponse paymentAccountMutationPagination = paymentAccountService.loadMutationPagination(request, account, id.replaceAll("[^A-Za-z0-9]", ""), page);

                if(paymentAccountMutationPagination.isResult()) {

                    model.addAttribute("link", paymentAccountMutationPagination.getLink());
                    model.addAttribute("page", page);
                    model.addAttribute("pagination", paymentAccountMutationPagination.getTransactionList());

                }

                logger.info("Payment account mutation page initialized");

                return "payment/account-mutation";

            }

            logger.info("Error restricted access initialized");

            return "restricted-access";

        }

        logger.info("Redirected to login page");

        return "redirect:/login/";

    }


    @RequestMapping(value = "/mutation/{id}/export", method = RequestMethod.GET)
    public void mutationExport(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        String checkLogin = userService.checkLogin(request);

        if(!checkLogin.isEmpty()) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                PaymentAccountResponse paymentAccountExportMutation = paymentAccountService.exportMutation(request, account, id.replaceAll("[^A-Za-z0-9]", ""));

                if(paymentAccountExportMutation.isResult()) {

                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment; filename=export.xlsx");

                    try {

                        ServletOutputStream servletOutputStream = response.getOutputStream();
                        paymentAccountExportMutation.getWorkbook().write(servletOutputStream);
                        paymentAccountExportMutation.getWorkbook().close();
                        servletOutputStream.close();

                    } catch(Exception exception) {

                        logger.error(exception.getMessage());

                    }

                }

                logger.info("Payment account mutation exported");

            } else {

                logger.info("Error restricted access initialized");

            }

        } else {

            logger.info("Session expired");

        }

    }


    @RequestMapping(value = "/initialize-pagination")
    @ResponseBody
    public PaymentAccountResponse initializePagination(HttpServletRequest request) {

        PaymentAccountResponse result = new PaymentAccountResponse() {
            {
                setResponse("Failed to initialize payment account pagination");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                result = paymentAccountService.initializePagination(request, account, page);

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
                "Failed to filter payment account pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                result = paymentAccountService.filterPagination(response, account, filter);

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
                "Failed to set payment account pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                result = paymentAccountService.setPagination(response, account, pagination);

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
                "Failed to remove filter payment account pagination",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                result = paymentAccountService.removeFilterPagination(request, response, account);

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
    public PaymentAccountResponse initializeData(@RequestBody String id) {

        PaymentAccountResponse result = new PaymentAccountResponse() {
            {
                setResponse("Failed to initialize payment account data");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                result = paymentAccountService.initializeData(account, id);

            } else {

                result.setResponse("User privilege denied");

            }

        } else {

            result.setResponse("Session expired");

        }

        logger.info(result.getResponse());

        return result;

    }


    @RequestMapping(value = "/upload-qr-code")
    @ResponseBody
    public FileResponse uploadQrCode(MultipartHttpServletRequest multipartRequest) {

        FileResponse result = new FileResponse() {
            {
                setResponse("Failed to upload payment account qr code");
                setResult(false);
            }
        };

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("view", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                result = file.uploadResizeImage(multipartRequest, FileSize.Medium, "/payment/account");

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
    public BaseResponse insert(HttpServletRequest request, @RequestBody PaymentAccount paymentAccount) {

        BaseResponse result = new BaseResponse(
                "Failed to insert payment account",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("add", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                result = paymentAccountService.insert(request, account, paymentAccount);

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
    public BaseResponse update(HttpServletRequest request, @RequestBody PaymentAccount paymentAccount) {

        BaseResponse result = new BaseResponse(
                "Failed to update payment account",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("edit", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                result = paymentAccountService.update(request, account, paymentAccount);

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
                "Failed to delete payment account",
                false
        );

        if(account.getUsername() != null) {

            BaseResponse checkPrivilege = userService.checkPrivilege("delete", account.getPrivilege().getPaymentAccount());

            if(checkPrivilege.isResult()) {

                result = paymentAccountService.delete(request, account, id);

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
