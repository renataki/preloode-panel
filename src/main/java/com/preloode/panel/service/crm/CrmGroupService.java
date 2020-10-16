package com.preloode.panel.service.crm;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.crm.CrmGroup;
import com.preloode.panel.model.crm.CrmGroupLogData;
import com.preloode.panel.model.crm.CrmGroupResponse;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.crm.CrmGroupLogDataRepository;
import com.preloode.panel.repository.crm.CrmGroupRepository;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.user.UserGroupRepository;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class CrmGroupService {


    private static final Logger logger = LoggerFactory.getLogger(CrmGroupService.class);

    private static final String cookieFilter = "fltcrmgrp";

    private static final String cookiePagination = "pgncrmgrp";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private CrmGroupLogDataRepository crmGroupLogDataRepository;

    @Autowired
    private CrmGroupRepository crmGroupRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete CRM group",
                false
        );

        CrmGroup crmGroupById = crmGroupRepository.findById(id).orElse(null);

        if(crmGroupById != null) {

            crmGroupLogDataRepository.save(data.initializeLogData("crmGroupId", crmGroupById, account, CrmGroupLogData.class));

            crmGroupRepository.delete(crmGroupById);

            userLogService.insert(request, "", "Delete CRM group id " + crmGroupById.getId(), UserLogType.DeleteCrmGroup, crmGroupById.getId(), crmGroupById.getName(), account);

            result.setResponse("CRM group deleted");
            result.setResult(true);

        } else {

            result.setResponse("CRM group doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public CrmGroupResponse initializeData(User account, String id) {

        CrmGroupResponse result = new CrmGroupResponse() {
            {
                setResponse("Failed to initialize CRM group data");
                setResult(false);
            }
        };

        result.setCrmGroup(crmGroupRepository.findById(id).orElse(new CrmGroup()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setUserGroupList(userGroupRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("CRM group data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private CrmGroup initializeInput(CrmGroup input) {

        CrmGroup result = input;

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        logger.info("CRM group input initialized");

        return result;

    }


    public CrmGroupResponse initializePagination(HttpServletRequest request, User account, int page) {

        CrmGroupResponse result = new CrmGroupResponse() {
            {
                setResponse("Failed to initialize CRM group pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("CRM group pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("crmGroupName", data.validateCrmGroupName(id, name));
            }
        };

        logger.info("CRM group input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, CrmGroup insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert CRM group",
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
            crmGroupRepository.save(insertData);

            userLogService.insert(request, "", "Insert CRM group id " + insertData.getId(), UserLogType.InsertCrmGroup, insertData.getId(), insertData.getName(), account);

            result.setResponse("CRM group inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public CrmGroup loadEntry(String id) {

        return crmGroupRepository.findById(id).orElse(new CrmGroup());

    }


    public CrmGroupResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        CrmGroupResponse result = new CrmGroupResponse() {
            {
                setResponse("Failed to load CRM group pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setCrmGroupList(globalRepository.findPagination(query, CrmGroup.class));
        result.setLink(data.initializePaginationLink(request, "/crm/group", page, size, result.getCrmGroupList().size()));

        result.setResponse("CRM group pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, CrmGroup updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update CRM group",
                false
        );

        updateData = initializeInput(updateData);

        CrmGroup crmGroupById = crmGroupRepository.findById(updateData.getId()).orElse(null);

        if(crmGroupById != null) {

            Map<String, BaseResponse> validation = inputValidation(crmGroupById.getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                crmGroupLogDataRepository.save(data.initializeLogData("crmGroupId", crmGroupById, account, CrmGroupLogData.class));

                updateData.setCreated(crmGroupById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                crmGroupRepository.save(updateData);

                userLogService.insert(request, "", "Update CRM group id " + updateData.getId(), UserLogType.UpdateCrmGroup, crmGroupById.getId(), crmGroupById.getName(), account);

                result.setResponse("CRM group updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("CRM group doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
