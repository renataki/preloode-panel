package com.preloode.panel.service.company;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.global.CountryCode;
import com.preloode.panel.enumeration.setting.ApplicationStatus;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.company.CompanyBranchLogData;
import com.preloode.panel.model.company.CompanyBranchResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.company.CompanyBranchLogDataRepository;
import com.preloode.panel.repository.company.CompanyBranchRepository;
import com.preloode.panel.repository.global.GlobalRepository;
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
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class CompanyBranchService {


    private static final Logger logger = LoggerFactory.getLogger(CompanyBranchService.class);

    private static final String cookieFilter = "fltcpnbnc";

    private static final String cookiePagination = "pgncpnbnc";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private FileComponent file;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private CompanyBranchLogDataRepository companyBranchLogDataRepository;

    @Autowired
    private CompanyBranchRepository companyBranchRepository;

    @Autowired
    private GlobalRepository globalRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete company branch",
                false
        );

        CompanyBranch companyBranchById = companyBranchRepository.findById(id).orElse(null);

        if(companyBranchById != null) {

            companyBranchLogDataRepository.save(data.initializeLogData("companyBranchId", companyBranchById, account, CompanyBranchLogData.class));

            companyBranchRepository.delete(companyBranchById);

            file.deleteImage(global.getSetting().getPath().getImage() + "/setting/thumbnail", companyBranchById.getFavicon());
            file.deleteImage(global.getSetting().getPath().getImage() + "/setting/thumbnail", companyBranchById.getLogo());

            userLogService.insert(request, "", "Delete company branch id " + companyBranchById.getId(), UserLogType.DeleteCompanyBranch, companyBranchById.getId(), companyBranchById.getName(), account);

            result.setResponse("Company branch deleted");
            result.setResult(true);

        } else {

            result.setResponse("Company branch doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public CompanyBranchResponse initializeData(User account, String id) {

        CompanyBranchResponse result = new CompanyBranchResponse() {
            {
                setResponse("Failed to initialize company branch data");
                setResult(false);
            }
        };

        result.setCompanyBranch(companyBranchRepository.findById(id).orElse(new CompanyBranch()));

        result.setApplicationStatusList(Stream.of(ApplicationStatus.values()).collect(Collectors.toList()));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setCountryCodeList(Stream.of(CountryCode.values()).map(CountryCode::toString).collect(Collectors.toList()));
        result.setCountryList(Stream.of(Country.values()).collect(Collectors.toList()));

        result.setResponse("Company branch data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private CompanyBranch initializeInput(CompanyBranch input) {

        CompanyBranch result = input;

        if(result.getAddToCart().getAverageTime() == null) {

            result.getAddToCart().setAverageTime(BigInteger.ZERO);

        }

        if(result.getAddToCart().getMaximum() == null) {

            result.getAddToCart().setMaximum(BigDecimal.ZERO);

        }

        result.getAddToCart().setMaximum(result.getAddToCart().getMaximum());

        if(result.getAddToCart().getMinimum() == null) {

            result.getAddToCart().setMinimum(BigDecimal.ZERO);

        }

        result.getAddToCart().setMinimum(result.getAddToCart().getMinimum());

        if(result.getAdjustment().getAverageTime() == null) {

            result.getAdjustment().setAverageTime(BigInteger.ZERO);

        }

        if(result.getAdjustment().getMaximum() == null) {

            result.getAdjustment().setMaximum(BigDecimal.ZERO);

        }

        result.getAdjustment().setMaximum(result.getAdjustment().getMaximum());

        if(result.getAdjustment().getMinimum() == null) {

            result.getAdjustment().setMinimum(BigDecimal.ZERO);

        }

        result.getAdjustment().setMinimum(result.getAdjustment().getMinimum());

        if(result.getCheckout().getAverageTime() == null) {

            result.getCheckout().setAverageTime(BigInteger.ZERO);

        }

        if(result.getCheckout().getMaximum() == null) {

            result.getCheckout().setMaximum(BigDecimal.ZERO);

        }

        result.getCheckout().setMaximum(result.getCheckout().getMaximum());

        if(result.getCheckout().getMinimum() == null) {

            result.getCheckout().setMinimum(BigDecimal.ZERO);

        }

        result.getCheckout().setMinimum(result.getCheckout().getMinimum());

        if(result.getDeposit().getAverageTime() == null) {

            result.getDeposit().setAverageTime(BigInteger.ZERO);

        }

        if(result.getDeposit().getMaximum() == null) {

            result.getDeposit().setMaximum(BigDecimal.ZERO);

        }

        result.getDeposit().setMaximum(result.getDeposit().getMaximum());

        if(result.getDeposit().getMinimum() == null) {

            result.getDeposit().setMinimum(BigDecimal.ZERO);

        }

        result.getDeposit().setMinimum(result.getDeposit().getMinimum());

        if(result.getExpense().getAverageTime() == null) {

            result.getExpense().setAverageTime(BigInteger.ZERO);

        }

        if(result.getExpense().getMaximum() == null) {

            result.getExpense().setMaximum(BigDecimal.ZERO);

        }

        result.getExpense().setMaximum(result.getExpense().getMaximum());

        if(result.getExpense().getMinimum() == null) {

            result.getExpense().setMinimum(BigDecimal.ZERO);

        }

        result.getExpense().setMinimum(result.getExpense().getMinimum());

        if(result.getFee().getAverageTime() == null) {

            result.getFee().setAverageTime(BigInteger.ZERO);

        }

        if(result.getFee().getMaximum() == null) {

            result.getFee().setMaximum(BigDecimal.ZERO);

        }

        result.getFee().setMaximum(result.getFee().getMaximum());

        if(result.getFee().getMinimum() == null) {

            result.getFee().setMinimum(BigDecimal.ZERO);

        }

        result.getFee().setMinimum(result.getFee().getMinimum());

        if(result.getInternal().getAverageTime() == null) {

            result.getInternal().setAverageTime(BigInteger.ZERO);

        }

        if(result.getInternal().getMaximum() == null) {

            result.getInternal().setMaximum(BigDecimal.ZERO);

        }

        result.getInternal().setMaximum(result.getInternal().getMaximum());

        if(result.getInternal().getMinimum() == null) {

            result.getInternal().setMinimum(BigDecimal.ZERO);

        }

        result.getInternal().setMinimum(result.getInternal().getMinimum());

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        if(result.getTransfer().getAverageTime() == null) {

            result.getTransfer().setAverageTime(BigInteger.ZERO);

        }

        if(result.getTransfer().getMaximum() == null) {

            result.getTransfer().setMaximum(BigDecimal.ZERO);

        }

        result.getTransfer().setMaximum(result.getTransfer().getMaximum());

        if(result.getTransfer().getMinimum() == null) {

            result.getTransfer().setMinimum(BigDecimal.ZERO);

        }

        result.getTransfer().setMinimum(result.getTransfer().getMinimum());

        if(result.getWithdrawal().getAverageTime() == null) {

            result.getWithdrawal().setAverageTime(BigInteger.ZERO);

        }

        if(result.getWithdrawal().getAverageTime() == null) {

            result.getWithdrawal().setAverageTime(BigInteger.ZERO);

        }

        if(result.getWithdrawal().getMaximum() == null) {

            result.getWithdrawal().setMaximum(BigDecimal.ZERO);

        }

        result.getWithdrawal().setMaximum(result.getWithdrawal().getMaximum());

        if(result.getWithdrawal().getMinimum() == null) {

            result.getWithdrawal().setMinimum(BigDecimal.ZERO);

        }

        result.getWithdrawal().setMinimum(result.getWithdrawal().getMinimum());

        logger.info("Company branch input initialized");

        return result;

    }


    public CompanyBranchResponse initializePagination(HttpServletRequest request, User account, int page) {

        CompanyBranchResponse result = new CompanyBranchResponse() {
            {
                setResponse("Failed to initialize company branch pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setApplicationStatusList(Stream.of(ApplicationStatus.values()).collect(Collectors.toList()));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setCountryList(Stream.of(Country.values()).collect(Collectors.toList()));

        result.setResponse("Company branch pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String contactEmailAddress, String contactFaxNumber, String contactLineId, String contactPhoneNumber, String contactWechatId, String contactWhatsappNumber, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("companyBranchName", data.validateCompanyBranchName(id, name));
            }
        };

        if(!contactEmailAddress.isEmpty()) {

            result.put("companyBranchContactEmailAddress", data.validateCompanyBranchContactEmailAddress(id, contactEmailAddress));

        }

        if(!contactFaxNumber.isEmpty()) {

            result.put("companyBranchContactFaxNumber", data.validateCompanyBranchContactFaxNumber(id, contactFaxNumber));

        }

        if(!contactLineId.isEmpty()) {

            result.put("companyBranchContactLineId", data.validateCompanyBranchContactLineId(id, contactLineId));

        }

        if(!contactPhoneNumber.isEmpty()) {

            result.put("companyBranchContactPhoneNumber", data.validateCompanyBranchContactPhoneNumber(id, contactPhoneNumber));

        }

        if(!contactWechatId.isEmpty()) {

            result.put("companyBranchContactWechatId", data.validateCompanyBranchContactWechatId(id, contactWechatId));

        }

        if(!contactWhatsappNumber.isEmpty()) {

            result.put("companyBranchContactWhatsappNumber", data.validateCompanyBranchContactWhatsappNumber(id, contactWhatsappNumber));

        }

        logger.info("Company branch input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, CompanyBranch insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert company branch",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getContact().getEmailAddress(), insertData.getContact().getFaxNumber(), insertData.getContact().getLineId(), insertData.getContact().getPhoneNumber(), insertData.getContact().getWechatId(), insertData.getContact().getWhatsappNumber(), insertData.getName());

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
            companyBranchRepository.save(insertData);

            userLogService.insert(request, "", "Insert company branch id " + insertData.getId(), UserLogType.InsertCompanyBranch, insertData.getId(), insertData.getName(), account);

            result.setResponse("Company branch inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public CompanyBranch loadEntry(String id) {

        return companyBranchRepository.findById(id).orElse(new CompanyBranch());

    }


    public CompanyBranchResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        CompanyBranchResponse result = new CompanyBranchResponse() {
            {
                setResponse("Failed to load company branch pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setCompanyBranchList(globalRepository.findPagination(query, CompanyBranch.class));
        result.setLink(data.initializePaginationLink(request, "/company/branch", page, size, result.getCompanyBranchList().size()));

        result.setResponse("Company branch pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, CompanyBranch updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update company branch",
                false
        );

        updateData = initializeInput(updateData);

        CompanyBranch companyBranchById = companyBranchRepository.findById(updateData.getId()).orElse(null);

        if(companyBranchById != null) {

            Map<String, BaseResponse> validation = inputValidation(companyBranchById.getId(), updateData.getContact().getEmailAddress(), updateData.getContact().getFaxNumber(), updateData.getContact().getLineId(), updateData.getContact().getPhoneNumber(), updateData.getContact().getWechatId(), updateData.getContact().getWhatsappNumber(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                if(!updateData.getFavicon().equals(companyBranchById.getFavicon())) {

                    file.deleteImage(global.getSetting().getPath().getImage() + "/setting/thumbnail", companyBranchById.getFavicon());

                }

                if(!updateData.getLogo().equals(companyBranchById.getLogo())) {

                    file.deleteImage(global.getSetting().getPath().getImage() + "/setting/thumbnail", companyBranchById.getLogo());

                }

                companyBranchLogDataRepository.save(data.initializeLogData("companyBranchId", companyBranchById, account, CompanyBranchLogData.class));

                updateData.setCreated(companyBranchById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                companyBranchRepository.save(updateData);

                userLogService.insert(request, "", "Update company branch id " + updateData.getId(), UserLogType.UpdateCompanyBranch, companyBranchById.getId(), companyBranchById.getName(), account);

                result.setResponse("Company branch updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Company branch doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
