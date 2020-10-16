package com.preloode.panel.service.payment;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.payment.PaymentType;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.payment.PaymentMethod;
import com.preloode.panel.model.payment.PaymentMethodLogData;
import com.preloode.panel.model.payment.PaymentMethodResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.payment.PaymentMethodLogDataRepository;
import com.preloode.panel.repository.payment.PaymentMethodRepository;
import com.preloode.panel.service.user.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class PaymentMethodService {


    private static final Logger logger = LoggerFactory.getLogger(PaymentMethodService.class);

    private static final String cookieFilter = "fltpynmtd";

    private static final String cookiePagination = "pgnpynmtd";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private FileComponent file;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private PaymentMethodLogDataRepository paymentMethodLogDataRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete payment method",
                false
        );

        PaymentMethod paymentMethodById = paymentMethodRepository.findById(id).orElse(null);

        if(paymentMethodById != null) {

            paymentMethodLogDataRepository.save(data.initializeLogData("paymentMethodId", paymentMethodById, account, PaymentMethodLogData.class));

            paymentMethodRepository.delete(paymentMethodById);

            file.deleteImage(global.getSetting().getPath().getImage() + "/payment/method", paymentMethodById.getFooter().getLogo());
            file.deleteImage(global.getSetting().getPath().getImage() + "/payment/method", paymentMethodById.getPayment().getLogo());
            file.deleteImage(global.getSetting().getPath().getImage() + "/payment/method", paymentMethodById.getSidebar().getLogo());

            userLogService.insert(request, "", "Delete payment method id " + paymentMethodById.getId(), UserLogType.DeletePaymentMethod, paymentMethodById.getId(), paymentMethodById.getName(), account);

            result.setResponse("Payment method deleted");
            result.setResult(true);

        } else {

            result.setResponse("Payment method doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public PaymentMethodResponse initializeData(User account, String id) {

        PaymentMethodResponse result = new PaymentMethodResponse() {
            {
                setResponse("Failed to initialize payment method data");
                setResult(false);
            }
        };

        result.setPaymentMethod(paymentMethodRepository.findById(id).orElse(new PaymentMethod()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setPaymentTypeList(Stream.of(PaymentType.values()).collect(Collectors.toList()));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("Payment method data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private PaymentMethod initializeInput(PaymentMethod input) {

        PaymentMethod result = input;

        if(result.getFee().getProvider() == null) {

            result.getFee().setProvider(BigDecimal.ZERO);

        }

        if(result.getFee().getService() == null) {

            result.getFee().setService(BigDecimal.ZERO);

        }

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        logger.info("Payment method input initialized");

        return result;

    }


    public PaymentMethodResponse initializePagination(HttpServletRequest request, User account, int page) {

        PaymentMethodResponse result = new PaymentMethodResponse() {
            {
                setResponse("Failed to initialize payment method pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setPaymentTypeList(Stream.of(PaymentType.values()).collect(Collectors.toList()));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("Payment method pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("paymentMethodName", data.validatePaymentMethodName(id, name));
            }
        };

        logger.info("Payment method input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, PaymentMethod insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert payment method",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getName());

        boolean valid = true;

        for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

            if(!map.getValue().isResult()) {

                valid = false;

                result.setResponse(map.getValue().getResponse());

                break;

            }

        }

        if(valid) {

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            paymentMethodRepository.save(insertData);

            userLogService.insert(request, "", "Insert payment method id " + insertData.getId(), UserLogType.InsertPaymentMethod, insertData.getId(), insertData.getName(), account);

            result.setResponse("Payment method inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public PaymentMethod loadEntry(String id) {

        return paymentMethodRepository.findById(id).orElse(new PaymentMethod());

    }


    public List<PaymentMethod> loadListByCompanyBranchIdListCompanyIdListFooterDisplayStatus() {

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };

        return paymentMethodRepository.findByCompanyBranchIdListCompanyIdListFooterDisplayStatusSort(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), true, Status.Active, Sort.by(sort));

    }


    public PaymentMethodResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        PaymentMethodResponse result = new PaymentMethodResponse() {
            {
                setResponse("Failed to load payment method pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setPaymentMethodList(globalRepository.findPagination(query, PaymentMethod.class));
        result.setLink(data.initializePaginationLink(request, "/payment/method", page, size, result.getPaymentMethodList().size()));

        result.setResponse("Payment method pagination loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse removeFilterPagination(HttpServletRequest request, HttpServletResponse response, User account) {

        return data.removeFilter(request, response, account.getId(), global.getCookie().getPrefix() + cookieFilter);

    }


    public BaseResponse setPagination(HttpServletResponse response, User account, int pagination) {

        return data.setPagination(response, account.getId(), global.getCookie().getPrefix() + cookiePagination, pagination);

    }


    public BaseResponse update(HttpServletRequest request, User account, PaymentMethod updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update payment method",
                false
        );

        updateData = initializeInput(updateData);

        PaymentMethod paymentMethodById = paymentMethodRepository.findById(updateData.getId()).orElse(null);

        if(paymentMethodById != null) {

            Map<String, BaseResponse> validation = inputValidation(paymentMethodById.getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                if(!updateData.getFooter().getLogo().contains(paymentMethodById.getFooter().getLogo())) {

                    file.deleteImage(global.getSetting().getPath().getImage() + "/payment/method", paymentMethodById.getFooter().getLogo());

                }

                if(!updateData.getPayment().getLogo().contains(paymentMethodById.getPayment().getLogo())) {

                    file.deleteImage(global.getSetting().getPath().getImage() + "/payment/method", paymentMethodById.getPayment().getLogo());

                }

                if(!updateData.getSidebar().getLogo().contains(paymentMethodById.getSidebar().getLogo())) {

                    file.deleteImage(global.getSetting().getPath().getImage() + "/payment/method", paymentMethodById.getSidebar().getLogo());

                }

                paymentMethodLogDataRepository.save(data.initializeLogData("paymentMethodId", paymentMethodById, account, PaymentMethodLogData.class));

                updateData.setCreated(paymentMethodById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                paymentMethodRepository.save(updateData);

                userLogService.insert(request, "", "Update payment method id " + updateData.getId(), UserLogType.UpdatePaymentMethod, paymentMethodById.getId(), paymentMethodById.getName(), account);

                result.setResponse("Payment method updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Payment method doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
