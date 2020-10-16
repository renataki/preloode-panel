package com.preloode.panel.service.thirdParty;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.thirdParty.ThirdPartyProvider;
import com.preloode.panel.model.thirdParty.ThirdPartyProviderLogData;
import com.preloode.panel.model.thirdParty.ThirdPartyProviderResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.thirdParty.ThirdPartyProviderLogDataRepository;
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
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class ThirdPartyProviderService {


    private static final Logger logger = LoggerFactory.getLogger(ThirdPartyProviderService.class);

    private static final String cookieFilter = "fltthdprtpvd";

    private static final String cookiePagination = "pgnthdprtpvd";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private ThirdPartyProviderLogDataRepository thirdPartyProviderLogDataRepository;

    @Autowired
    private ThirdPartyProviderRepository thirdPartyProviderRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete third party provider",
                false
        );

        ThirdPartyProvider thirdPartyProviderById = thirdPartyProviderRepository.findById(id).orElse(null);

        if(thirdPartyProviderById != null) {

            thirdPartyProviderLogDataRepository.save(data.initializeLogData("thirdPartyProviderId", thirdPartyProviderById, account, ThirdPartyProviderLogData.class));

            thirdPartyProviderRepository.delete(thirdPartyProviderById);

            userLogService.insert(request, "", "Delete third party provider id " + thirdPartyProviderById.getId(), UserLogType.DeleteThirdPartyProvider, thirdPartyProviderById.getId(), thirdPartyProviderById.getName(), account);

            result.setResponse("Third party provider deleted");
            result.setResult(true);

        } else {

            result.setResponse("Third party provider doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public ThirdPartyProviderResponse initializeData(User account, String id) {

        ThirdPartyProviderResponse result = new ThirdPartyProviderResponse() {
            {
                setResponse("Failed to initialize third party provider data");
                setResult(false);
            }
        };

        result.setThirdPartyProvider(thirdPartyProviderRepository.findById(id).orElse(new ThirdPartyProvider()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("Third party provider data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private ThirdPartyProvider initializeInput(ThirdPartyProvider input) {

        ThirdPartyProvider result = input;

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        logger.info("Third party provider input initialized");

        return result;

    }


    public ThirdPartyProviderResponse initializePagination(HttpServletRequest request, User account, int page) {

        ThirdPartyProviderResponse result = new ThirdPartyProviderResponse() {
            {
                setResponse("Failed to initialize third party provider pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("Third party provider pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("thirdPartyProviderName", data.validateThirdPartyProviderName(id, name));
            }
        };

        logger.info("Third party provider input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, ThirdPartyProvider insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert third party provider",
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
            thirdPartyProviderRepository.save(insertData);

            userLogService.insert(request, "", "Insert third party provider id " + insertData.getId(), UserLogType.InsertThirdPartyProvider, insertData.getId(), insertData.getName(), account);

            result.setResponse("Third party provider inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public ThirdPartyProvider loadEntry(String id) {

        return thirdPartyProviderRepository.findById(id).orElse(new ThirdPartyProvider());

    }


    public ThirdPartyProviderResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        ThirdPartyProviderResponse result = new ThirdPartyProviderResponse() {
            {
                setResponse("Failed to load third party provider pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setThirdPartyProviderList(globalRepository.findPagination(query, ThirdPartyProvider.class));
        result.setLink(data.initializePaginationLink(request, "/third-party/provider", page, size, result.getThirdPartyProviderList().size()));

        result.setResponse("Third party provider pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, ThirdPartyProvider updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update third party provider",
                false
        );

        updateData = initializeInput(updateData);

        ThirdPartyProvider thirdPartyProviderById = thirdPartyProviderRepository.findById(updateData.getId()).orElse(null);

        if(thirdPartyProviderById != null) {

            Map<String, BaseResponse> validation = inputValidation(thirdPartyProviderById.getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                thirdPartyProviderLogDataRepository.save(data.initializeLogData("thirdPartyProviderId", thirdPartyProviderById, account, ThirdPartyProviderLogData.class));

                updateData.setCreated(thirdPartyProviderById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                thirdPartyProviderRepository.save(updateData);

                userLogService.insert(request, "", "Update third party provider id " + updateData.getId(), UserLogType.UpdateThirdPartyProvider, thirdPartyProviderById.getId(), thirdPartyProviderById.getName(), account);

                result.setResponse("Third party provider updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Third party provider doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
