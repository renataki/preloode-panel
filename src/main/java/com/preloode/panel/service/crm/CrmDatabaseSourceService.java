package com.preloode.panel.service.crm;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.crm.CrmDatabaseSource;
import com.preloode.panel.model.crm.CrmDatabaseSourceLogData;
import com.preloode.panel.model.crm.CrmDatabaseSourceResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.model.user.UserGroup;
import com.preloode.panel.model.user.UserGroupLogData;
import com.preloode.panel.model.user.UserGroupResponse;
import com.preloode.panel.repository.crm.CrmDatabaseSourceLogDataRepository;
import com.preloode.panel.repository.crm.CrmDatabaseSourceRepository;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.user.UserGroupLogDataRepository;
import com.preloode.panel.repository.user.UserGroupRepository;
import com.preloode.panel.service.user.UserGroupService;
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
public class CrmDatabaseSourceService {


    private static final Logger logger = LoggerFactory.getLogger(CrmDatabaseSourceService.class);

    private static final String cookieFilter = "fltcrmdtbsrc";

    private static final String cookiePagination = "pgncrmdtbsrc";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private CrmDatabaseSourceLogDataRepository crmDatabaseSourceLogDataRepository;

    @Autowired
    private CrmDatabaseSourceRepository crmDatabaseSourceRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete CRM database source",
                false
        );

        CrmDatabaseSource crmDatabaseSourceById = crmDatabaseSourceRepository.findById(id).orElse(null);

        if(crmDatabaseSourceById != null) {

            crmDatabaseSourceLogDataRepository.save(data.initializeLogData("crmDatabaseSourceId", crmDatabaseSourceById, account, CrmDatabaseSourceLogData.class));

            crmDatabaseSourceRepository.delete(crmDatabaseSourceById);

            userLogService.insert(request, "", "Delete CRM database source id " + crmDatabaseSourceById.getId(), UserLogType.DeleteCrmDatabaseSource, crmDatabaseSourceById.getId(), crmDatabaseSourceById.getName(), account);

            result.setResponse("CRM database source deleted");
            result.setResult(true);

        } else {

            result.setResponse("CRM database source doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public CrmDatabaseSourceResponse initializeData(User account, String id) {

        CrmDatabaseSourceResponse result = new CrmDatabaseSourceResponse() {
            {
                setResponse("Failed to initialize CRM database source data");
                setResult(false);
            }
        };

        result.setCrmDatabaseSource(crmDatabaseSourceRepository.findById(id).orElse(new CrmDatabaseSource()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("CRM database source data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private CrmDatabaseSource initializeInput(CrmDatabaseSource input) {

        CrmDatabaseSource result = input;

        logger.info("User group input initialized");

        return result;

    }


    public CrmDatabaseSourceResponse initializePagination(HttpServletRequest request, User account, int page) {

        CrmDatabaseSourceResponse result = new CrmDatabaseSourceResponse() {
            {
                setResponse("Failed to initialize CRM database source pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("CRM database source pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("crmDatabaseSourceName", data.validateCrmDatabaseSourceName(id, name));
            }
        };

        logger.info("CRM database source input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, CrmDatabaseSource insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert CRM database source",
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
            crmDatabaseSourceRepository.save(insertData);

            userLogService.insert(request, "", "Insert CRM database source id " + insertData.getId(), UserLogType.InsertCrmDatabaseSource, insertData.getId(), insertData.getName(), account);

            result.setResponse("CRM database source inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public CrmDatabaseSource loadEntry(String id) {

        return crmDatabaseSourceRepository.findById(id).orElse(new CrmDatabaseSource());

    }


    public CrmDatabaseSourceResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        CrmDatabaseSourceResponse result = new CrmDatabaseSourceResponse() {
            {
                setResponse("Failed to load CRM database source pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setCrmDatabaseSourceList(globalRepository.findPagination(query, CrmDatabaseSource.class));
        result.setLink(data.initializePaginationLink(request, "/crm/database/source", page, size, result.getCrmDatabaseSourceList().size()));

        result.setResponse("CRM database source pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, CrmDatabaseSource updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update CRM database source",
                false
        );

        updateData = initializeInput(updateData);

        CrmDatabaseSource crmDatabaseSourceById = crmDatabaseSourceRepository.findById(updateData.getId()).orElse(null);

        if(crmDatabaseSourceById != null) {

            Map<String, BaseResponse> validation = inputValidation(crmDatabaseSourceById.getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                crmDatabaseSourceLogDataRepository.save(data.initializeLogData("crmDatabaseSourceId", crmDatabaseSourceById, account, CrmDatabaseSourceLogData.class));

                updateData.setCreated(crmDatabaseSourceById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                crmDatabaseSourceRepository.save(updateData);

                userLogService.insert(request, "", "Update CRM database source id " + updateData.getId(), UserLogType.UpdateCrmDatabaseSource, crmDatabaseSourceById.getId(), crmDatabaseSourceById.getName(), account);

                result.setResponse("CRM database source updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("CRM database source doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
