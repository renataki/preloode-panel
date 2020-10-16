package com.preloode.panel.service.user;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.model.user.UserGroup;
import com.preloode.panel.model.user.UserGroupLogData;
import com.preloode.panel.model.user.UserGroupResponse;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.user.UserGroupLogDataRepository;
import com.preloode.panel.repository.user.UserGroupRepository;
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
public class UserGroupService {


    private static final Logger logger = LoggerFactory.getLogger(UserGroupService.class);

    private static final String cookieFilter = "fltusrgrp";

    private static final String cookiePagination = "pgnusrgrp";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private UserGroupLogDataRepository userGroupLogDataRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete user group",
                false
        );

        UserGroup userGroupById = userGroupRepository.findById(id).orElse(null);

        if(userGroupById != null) {

            userGroupLogDataRepository.save(data.initializeLogData("userGroupId", userGroupById, account, UserGroupLogData.class));

            userGroupRepository.delete(userGroupById);

            userLogService.insert(request, "", "Delete user group id " + userGroupById.getId(), UserLogType.DeleteUserGroup, userGroupById.getId(), userGroupById.getName(), account);

            result.setResponse("User group deleted");
            result.setResult(true);

        } else {

            result.setResponse("User group doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public UserGroupResponse initializeData(User account, String id) {

        UserGroupResponse result = new UserGroupResponse() {
            {
                setResponse("Failed to initialize user group data");
                setResult(false);
            }
        };

        result.setUserGroup(userGroupRepository.findById(id).orElse(new UserGroup()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));
        result.setUserTypeList(Stream.of(UserType.values()).collect(Collectors.toList()));

        if(account.getType() != UserType.System) {

            result.setUserTypeList(Stream.of(UserType.values()).filter(userType -> !userType.equals(UserType.System)).collect(Collectors.toList()));

        }

        result.setResponse("User group data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private UserGroup initializeInput(UserGroup input) {

        UserGroup result = input;

        if(result.getDeposit().getAmount().getEnd() == null) {

            result.getDeposit().getAmount().setEnd(BigDecimal.ZERO);

        }

        result.getDeposit().getAmount().setEnd(result.getDeposit().getAmount().getEnd());

        if(result.getDeposit().getAmount().getStart() == null) {

            result.getDeposit().getAmount().setStart(BigDecimal.ZERO);

        }

        result.getDeposit().getAmount().setStart(result.getDeposit().getAmount().getStart());

        if(result.getDeposit().getCount().getEnd() == null) {

            result.getDeposit().getCount().setEnd(BigInteger.ZERO);

        }

        if(result.getDeposit().getCount().getStart() == null) {

            result.getDeposit().getCount().setStart(BigInteger.ZERO);

        }

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        if(result.getTransaction().getAmount().getEnd() == null) {

            result.getTransaction().getAmount().setEnd(BigDecimal.ZERO);

        }

        result.getTransaction().getAmount().setEnd(result.getTransaction().getAmount().getEnd());

        if(result.getTransaction().getAmount().getStart() == null) {

            result.getTransaction().getAmount().setStart(BigDecimal.ZERO);

        }

        result.getTransaction().getAmount().setStart(result.getTransaction().getAmount().getStart());

        if(result.getTransaction().getCount().getEnd() == null) {

            result.getTransaction().getCount().setEnd(BigInteger.ZERO);

        }

        if(result.getTransaction().getCount().getStart() == null) {

            result.getTransaction().getCount().setStart(BigInteger.ZERO);

        }

        if(result.getWithdrawal().getAmount().getEnd() == null) {

            result.getWithdrawal().getAmount().setEnd(BigDecimal.ZERO);

        }

        result.getWithdrawal().getAmount().setEnd(result.getWithdrawal().getAmount().getEnd());

        if(result.getWithdrawal().getAmount().getStart() == null) {

            result.getWithdrawal().getAmount().setStart(BigDecimal.ZERO);

        }

        result.getWithdrawal().getAmount().setStart(result.getWithdrawal().getAmount().getStart());

        if(result.getWithdrawal().getCount().getEnd() == null) {

            result.getWithdrawal().getCount().setEnd(BigInteger.ZERO);

        }

        if(result.getWithdrawal().getCount().getStart() == null) {

            result.getWithdrawal().getCount().setStart(BigInteger.ZERO);

        }

        logger.info("User group input initialized");

        return result;

    }


    public UserGroupResponse initializePagination(HttpServletRequest request, User account, int page) {

        UserGroupResponse result = new UserGroupResponse() {
            {
                setResponse("Failed to initialize user group pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));
        result.setUserTypeList(Stream.of(UserType.values()).collect(Collectors.toList()));

        if(account.getType() != UserType.System) {

            result.setUserTypeList(Stream.of(UserType.values()).filter(userType -> !userType.equals(UserType.System)).collect(Collectors.toList()));

        }

        result.setResponse("User group pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("userGroupName", data.validateUserGroupName(id, name));
            }
        };

        logger.info("User group input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, UserGroup insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert user group",
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
            userGroupRepository.save(insertData);

            userLogService.insert(request, "", "Insert user group id " + insertData.getId(), UserLogType.InsertUserGroup, insertData.getId(), insertData.getName(), account);

            result.setResponse("User group inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public UserGroup loadEntry(String id) {

        return userGroupRepository.findById(id).orElse(new UserGroup());

    }


    public UserGroupResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        UserGroupResponse result = new UserGroupResponse() {
            {
                setResponse("Failed to load user group pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setUserGroupList(globalRepository.findPagination(query, UserGroup.class));
        result.setLink(data.initializePaginationLink(request, "/user/group", page, size, result.getUserGroupList().size()));

        result.setResponse("User group pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, UserGroup updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update user group",
                false
        );

        updateData = initializeInput(updateData);

        UserGroup userGroupById = userGroupRepository.findById(updateData.getId()).orElse(null);

        if(userGroupById != null) {

            Map<String, BaseResponse> validation = inputValidation(userGroupById.getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                userGroupLogDataRepository.save(data.initializeLogData("userGroupId", userGroupById, account, UserGroupLogData.class));

                updateData.setCreated(userGroupById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                userGroupRepository.save(updateData);

                userLogService.insert(request, "", "Update user group id " + updateData.getId(), UserLogType.UpdateUserGroup, userGroupById.getId(), userGroupById.getName(), account);

                result.setResponse("User group updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("User group doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
