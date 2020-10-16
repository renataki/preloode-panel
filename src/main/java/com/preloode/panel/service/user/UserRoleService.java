package com.preloode.panel.service.user;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.model.user.UserRole;
import com.preloode.panel.model.user.UserRoleLogData;
import com.preloode.panel.model.user.UserRoleResponse;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.user.UserRoleLogDataRepository;
import com.preloode.panel.repository.user.UserRoleRepository;
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
public class UserRoleService {


    private static final Logger logger = LoggerFactory.getLogger(UserRoleService.class);

    private static final String cookieFilter = "fltusrrl";

    private static final String cookiePagination = "pgnusrrl";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private UserRoleLogDataRepository userRoleLogDataRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete user role",
                false
        );

        UserRole userRoleById = userRoleRepository.findById(id).orElse(null);

        if(userRoleById != null) {

            userRoleLogDataRepository.save(data.initializeLogData("userRoleId", userRoleById, account, UserRoleLogData.class));

            userRoleRepository.delete(userRoleById);

            userLogService.insert(request, "", "Delete user role id " + userRoleById.getId(), UserLogType.DeleteUserRole, userRoleById.getId(), userRoleById.getName(), account);

            result.setResponse("User role deleted");
            result.setResult(true);

        } else {

            result.setResponse("User role doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public UserRoleResponse initializeData(User account, String id) {

        UserRoleResponse result = new UserRoleResponse() {
            {
                setResponse("Failed to initialize user role data");
                setResult(false);
            }
        };

        result.setUserRole(userRoleRepository.findById(id).orElse(new UserRole()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("User role data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private UserRole initializeInput(UserRole input) {

        UserRole result = input;

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        logger.info("User role input initialized");

        return result;

    }


    public UserRoleResponse initializePagination(HttpServletRequest request, User account, int page) {

        UserRoleResponse result = new UserRoleResponse() {
            {
                setResponse("Failed to initialize user role pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("User role pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("userRoleName", data.validateUserRoleName(id, name));
            }
        };

        logger.info("User role input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, UserRole insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert user role",
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
            userRoleRepository.save(insertData);

            userLogService.insert(request, "", "Insert user role id " + insertData.getId(), UserLogType.InsertUser, insertData.getId(), insertData.getName(), account);

            result.setResponse("User role inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public UserRole loadEntry(String id) {

        return userRoleRepository.findById(id).orElse(new UserRole());

    }


    public UserRoleResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        UserRoleResponse result = new UserRoleResponse() {
            {
                setResponse("Failed to load user role pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setUserRoleList(globalRepository.findPagination(query, UserRole.class));
        result.setLink(data.initializePaginationLink(request, "/user/role", page, size, result.getUserRoleList().size()));

        result.setResponse("User role pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, UserRole updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update user role",
                false
        );

        updateData = initializeInput(updateData);

        UserRole userRoleById = userRoleRepository.findById(updateData.getId()).orElse(null);

        if(userRoleById != null) {

            Map<String, BaseResponse> validation = inputValidation(userRoleById.getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                userRoleLogDataRepository.save(data.initializeLogData("userRoleId", userRoleById, account, UserRoleLogData.class));

                updateData.setCreated(userRoleById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                userRoleRepository.save(updateData);

                userLogService.insert(request, "", "Update user role id " + updateData.getId(), UserLogType.UpdateUserRole, userRoleById.getId(), userRoleById.getName(), account);

                result.setResponse("User role updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("User role doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
