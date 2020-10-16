package com.preloode.panel.service.thirdParty;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.thirdParty.ThirdPartyAccount;
import com.preloode.panel.model.thirdParty.ThirdPartyAccountLogData;
import com.preloode.panel.model.thirdParty.ThirdPartyAccountResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.thirdParty.ThirdPartyAccountLogDataRepository;
import com.preloode.panel.repository.thirdParty.ThirdPartyAccountRepository;
import com.preloode.panel.repository.thirdParty.ThirdPartyProviderRepository;
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
public class ThirdPartyAccountService {


    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyAccountService.class);

    private static final String cookieFilter = "fltthdprtacn";

    private static final String cookiePagination = "pgnthdprtacn";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private ThirdPartyAccountLogDataRepository thirdPartyAccountLogDataRepository;

    @Autowired
    private ThirdPartyAccountRepository thirdPartyAccountRepository;

    @Autowired
    private ThirdPartyProviderRepository thirdPartyProviderRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete third party account",
                false
        );

        ThirdPartyAccount thirdPartyAccountById = thirdPartyAccountRepository.findById(id).orElse(null);

        if(thirdPartyAccountById != null) {

            thirdPartyAccountLogDataRepository.save(data.initializeLogData("thirdPartyAccountId", thirdPartyAccountById, account, ThirdPartyAccountLogData.class));

            thirdPartyAccountRepository.delete(thirdPartyAccountById);

            userLogService.insert(request, "", "Delete third party account id " + thirdPartyAccountById.getId(), UserLogType.DeleteThirdPartyAccount, thirdPartyAccountById.getId(), thirdPartyAccountById.getUsername(), account);

            result.setResponse("Third party account deleted");
            result.setResult(true);

        } else {

            result.setResponse("Third party account doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public ThirdPartyAccountResponse initializeData(User account, String id) {

        ThirdPartyAccountResponse result = new ThirdPartyAccountResponse() {
            {
                setResponse("Failed to initialize third party account data");
                setResult(false);
            }
        };

        result.setThirdPartyAccount(thirdPartyAccountRepository.findById(id).orElse(new ThirdPartyAccount()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setThirdPartyProviderList(thirdPartyProviderRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Third party account data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private ThirdPartyAccount initializeInput(ThirdPartyAccount input) {

        ThirdPartyAccount result = input;

        if(result.getCredit() == null) {

            result.setCredit(BigDecimal.ZERO);

        }

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        logger.info("Third party account input initialized");

        return result;

    }


    public ThirdPartyAccountResponse initializePagination(HttpServletRequest request, User account, int page) {

        ThirdPartyAccountResponse result = new ThirdPartyAccountResponse() {
            {
                setResponse("Failed to initialize third party account pagination");
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
        result.setThirdPartyProviderList(thirdPartyProviderRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("Third party account pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String providerId, String username) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("thirdPartyAccountUsername", data.validationThirdPartyAccountUsername(id, providerId, username));
            }
        };

        logger.info("Third party account input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, ThirdPartyAccount insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert third party account",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getProvider().getId(), insertData.getUsername());

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
            thirdPartyAccountRepository.save(insertData);

            userLogService.insert(request, "", "Insert third party account id " + insertData.getId(), UserLogType.InsertThirdPartyAccount, insertData.getId(), insertData.getUsername(), account);

            result.setResponse("Third party account inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public ThirdPartyAccount loadEntry(String id) {

        return thirdPartyAccountRepository.findById(id).orElse(new ThirdPartyAccount());

    }


    public ThirdPartyAccountResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        ThirdPartyAccountResponse result = new ThirdPartyAccountResponse() {
            {
                setResponse("Failed to load third party account pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setThirdPartyAccountList(globalRepository.findPagination(query, ThirdPartyAccount.class));
        result.setLink(data.initializePaginationLink(request, "/third-party/account", page, size, result.getThirdPartyAccountList().size()));

        result.setResponse("Third party account pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, ThirdPartyAccount updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update third party account",
                false
        );

        updateData = initializeInput(updateData);

        ThirdPartyAccount thirdPartyAccountById = thirdPartyAccountRepository.findById(updateData.getId()).orElse(null);

        if(thirdPartyAccountById != null) {

            Map<String, BaseResponse> validation = inputValidation(thirdPartyAccountById.getId(), updateData.getProvider().getId(), updateData.getUsername());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                updateData.setCredit(thirdPartyAccountById.getCredit());

                thirdPartyAccountLogDataRepository.save(data.initializeLogData("thirdPartyAccountId", thirdPartyAccountById, account, ThirdPartyAccountLogData.class));

                updateData.setCreated(thirdPartyAccountById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                thirdPartyAccountRepository.save(updateData);

                userLogService.insert(request, "", "Update third party account id " + updateData.getId(), UserLogType.UpdateThirdPartyAccount, thirdPartyAccountById.getId(), thirdPartyAccountById.getUsername(), account);

                result.setResponse("Third party account updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Third party account doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
