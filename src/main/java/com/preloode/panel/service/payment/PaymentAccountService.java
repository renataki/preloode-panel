package com.preloode.panel.service.payment;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.payment.PaymentAccount;
import com.preloode.panel.model.payment.PaymentAccountLogData;
import com.preloode.panel.model.payment.PaymentAccountResponse;
import com.preloode.panel.model.transaction.Transaction;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.payment.PaymentAccountLogDataRepository;
import com.preloode.panel.repository.payment.PaymentAccountRepository;
import com.preloode.panel.repository.payment.PaymentMethodRepository;
import com.preloode.panel.service.transaction.TransactionService;
import com.preloode.panel.service.user.UserLogService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
public class PaymentAccountService {


    private static final Logger logger = LoggerFactory.getLogger(PaymentAccountService.class);

    private static final String cookieFilter = "fltpynacn";

    private static final String cookiePagination = "pgnpynacn";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private FileComponent file;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private PaymentAccountLogDataRepository paymentAccountLogDataRepository;

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete payment account",
                false
        );

        PaymentAccount paymentAccountById = paymentAccountRepository.findById(id).orElse(null);

        if(paymentAccountById != null) {

            paymentAccountLogDataRepository.save(data.initializeLogData("paymentAccountId", paymentAccountById, account, PaymentAccountLogData.class));

            paymentAccountRepository.delete(paymentAccountById);

            file.deleteImage(global.getSetting().getPath().getImage() + "/payment/account", paymentAccountById.getQrCode());

            userLogService.insert(request, "", "Delete payment account id " + paymentAccountById.getId(), UserLogType.DeletePaymentAccount, paymentAccountById.getId(), paymentAccountById.getName(), account);

            result.setResponse("Payment account deleted");
            result.setResult(true);

        } else {

            result.setResponse("Payment account doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public PaymentAccountResponse exportMutation(HttpServletRequest request, User account, String id) {

        PaymentAccountResponse result = new PaymentAccountResponse() {
            {
                setResponse("Failed to export payment account mutation");
                setResult(false);
            }
        };

        String cookie = global.getCookie().getPrefix() + cookieFilter + "mtn";
        List<Transaction> transactionList = transactionService.loadMutation(id);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        CellStyle vCenter = workbook.createCellStyle();
        vCenter.setVerticalAlignment(VerticalAlignment.CENTER);

        CellStyle vCenterWrap = workbook.createCellStyle();
        vCenterWrap.setVerticalAlignment(VerticalAlignment.CENTER);
        vCenterWrap.setWrapText(true);

        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("No");
        row.createCell(1).setCellValue("Ticket Number");
        row.createCell(2).setCellValue("From");
        row.createCell(3).setCellValue("To");
        row.createCell(4).setCellValue("Amount");
        row.createCell(5).setCellValue("Type");
        row.createCell(6).setCellValue("Status");
        row.createCell(7).setCellValue("Created Date");
        row.createCell(8).setCellValue("Last Modified Date");

        int rowNumber = 1;

        for(Transaction transaction : transactionList) {

            row = sheet.createRow(rowNumber);

            Cell cell = row.createCell(0);
            cell.setCellStyle(vCenter);
            cell.setCellValue(rowNumber);

            cell = row.createCell(1);
            cell.setCellStyle(vCenter);
            cell.setCellValue(transaction.getTicketNumber());

            String from = "";

            if(!transaction.getFrom().getPayment().getAccount().getName().isEmpty()) {

                if(!transaction.getFrom().getPayment().getMethod().getName().isEmpty()) {

                    from += transaction.getFrom().getPayment().getMethod().getName();

                }

                if(!transaction.getFrom().getPayment().getAccount().getName().isEmpty()) {

                    from += "\n" + transaction.getFrom().getPayment().getAccount().getName() + " - " + transaction.getFrom().getPayment().getAccount().getNumber();

                }

            } else if(!transaction.getFrom().getUser().getId().isEmpty()) {

                from += transaction.getFrom().getUser().getUsername();

                if(!transaction.getFrom().getUser().getAccount().getUsername().isEmpty()) {

                    from += "\n" + transaction.getFrom().getUser().getAccount().getThirdParty().getProvider().getName() + " - " + transaction.getFrom().getUser().getAccount().getUsername();

                }

            }

            cell = row.createCell(2);
            cell.setCellStyle(vCenterWrap);
            cell.setCellValue(from);

            String to = "";

            if(!transaction.getTo().getPayment().getAccount().getName().isEmpty()) {

                if(!transaction.getTo().getPayment().getMethod().getName().isEmpty()) {

                    to += transaction.getTo().getPayment().getMethod().getName();

                }

                if(!transaction.getTo().getPayment().getAccount().getName().isEmpty()) {

                    to += "\n" + transaction.getTo().getPayment().getAccount().getName() + " - " + transaction.getTo().getPayment().getAccount().getNumber();

                }

            } else if(!transaction.getTo().getUser().getId().isEmpty()) {

                to += transaction.getTo().getUser().getUsername();

                if(!transaction.getTo().getUser().getAccount().getUsername().isEmpty()) {

                    to += "\n" + transaction.getTo().getUser().getAccount().getThirdParty().getProvider().getName() + " - " + transaction.getTo().getUser().getAccount().getUsername();

                }

            }

            cell = row.createCell(3);
            cell.setCellStyle(vCenterWrap);
            cell.setCellValue(to);

            cell = row.createCell(4);
            cell.setCellStyle(vCenter);
            cell.setCellValue(transaction.getAmount().getMain().toString());

            cell = row.createCell(5);
            cell.setCellStyle(vCenter);
            cell.setCellValue(transaction.getType().toString());

            cell = row.createCell(6);
            cell.setCellStyle(vCenter);
            cell.setCellValue(transaction.getStatus().toString());

            cell = row.createCell(7);
            cell.setCellStyle(vCenter);
            cell.setCellValue(transaction.getCreated().getTimestamp());

            cell = row.createCell(8);
            cell.setCellStyle(vCenter);
            cell.setCellValue(transaction.getModified().getTimestamp());

            rowNumber++;

        }

        result.setWorkbook(workbook);

        result.setResponse("Payment account mutation exported");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public PaymentAccountResponse initializeData(User account, String id) {

        PaymentAccountResponse result = new PaymentAccountResponse() {
            {
                setResponse("Failed to initialize payment account data");
                setResult(false);
            }
        };

        result.setPaymentAccount(paymentAccountRepository.findById(id).orElse(new PaymentAccount()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setPaymentMethodList(paymentMethodRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Payment account data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private PaymentAccount initializeInput(PaymentAccount input) {

        PaymentAccount result = input;

        if(result.getCredit() == null) {

            result.setCredit(BigDecimal.ZERO);

        }

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        logger.info("Payment account input initialized");

        return result;

    }


    public PaymentAccountResponse initializePagination(HttpServletRequest request, User account, int page) {

        PaymentAccountResponse result = new PaymentAccountResponse() {
            {
                setResponse("Failed to initialize payment account pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setPaymentMethodList(paymentMethodRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Payment account pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String methodId, String number) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("paymentAccount", data.validatePaymentAccount(id, methodId, number));
            }
        };

        logger.info("Payment account input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, PaymentAccount insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert payment account",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getMethod().getId(), insertData.getName());

        boolean valid = true;

        for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

            if(!map.getValue().isResult()) {

                valid = false;

                result.setResponse(map.getValue().getResponse());

                break;

            }

        }

        if(valid) {

            insertData.setCredit(BigDecimal.ZERO);

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            paymentAccountRepository.save(insertData);

            userLogService.insert(request, "", "Insert payment account id " + insertData.getId(), UserLogType.InsertPaymentAccount, insertData.getId(), insertData.getName(), account);

            result.setResponse("Payment account inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public PaymentAccount loadEntry(String id) {

        return paymentAccountRepository.findById(id).orElse(new PaymentAccount());

    }


    public PaymentAccountResponse loadMutationPagination(HttpServletRequest request, User account, String id, int page) {

        PaymentAccountResponse result = new PaymentAccountResponse() {
            {
                setResponse("Failed to load payment account mutation pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination);
        result.setTransactionList(transactionService.loadMutationPagination(id, page, size));

        result.setResponse("Payment account mutation pagination loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public PaymentAccountResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        PaymentAccountResponse result = new PaymentAccountResponse() {
            {
                setResponse("Failed to load payment account pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setPaymentAccountList(globalRepository.findPagination(query, PaymentAccount.class));
        result.setLink(data.initializePaginationLink(request, "/payment/account", page, size, result.getPaymentAccountList().size()));

        result.setResponse("Payment account pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, PaymentAccount updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update payment account",
                false
        );

        updateData = initializeInput(updateData);

        PaymentAccount paymentAccountById = paymentAccountRepository.findById(updateData.getId()).orElse(null);

        if(paymentAccountById != null) {

            updateData.setCredit(paymentAccountById.getCredit());

            Map<String, BaseResponse> validation = inputValidation(paymentAccountById.getId(), updateData.getMethod().getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                updateData.setCredit(paymentAccountById.getCredit());

                if(!updateData.getQrCode().contains(paymentAccountById.getQrCode())) {

                    file.deleteImage(global.getSetting().getPath().getImage() + "/payment/account", paymentAccountById.getQrCode());

                }

                paymentAccountLogDataRepository.save(data.initializeLogData("paymentAccountId", paymentAccountById, account, PaymentAccountLogData.class));

                updateData.setCreated(paymentAccountById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                paymentAccountRepository.save(updateData);

                userLogService.insert(request, "", "Update payment account id " + updateData.getId(), UserLogType.UpdatePaymentAccount, paymentAccountById.getId(), paymentAccountById.getName(), account);

                result.setResponse("Payment account updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Payment account doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
