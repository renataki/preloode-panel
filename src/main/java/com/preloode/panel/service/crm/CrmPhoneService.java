package com.preloode.panel.service.crm;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.crm.CrmDatabaseStatus;
import com.preloode.panel.enumeration.crm.CrmDatabaseType;
import com.preloode.panel.enumeration.global.*;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.crm.*;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.crm.CrmDatabaseLogDataRepository;
import com.preloode.panel.repository.crm.CrmDatabaseRepository;
import com.preloode.panel.repository.crm.CrmDatabaseSourceRepository;
import com.preloode.panel.repository.crm.CrmGroupRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class CrmPhoneService {


    private static final Logger logger = LoggerFactory.getLogger(CrmPhoneService.class);

    private static final String cookieFilter = "fltcrmphn";

    private static final String cookiePagination = "pgncrmphn";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private CrmDatabaseLogDataRepository crmDatabaseLogDataRepository;

    @Autowired
    private CrmDatabaseRepository crmDatabaseRepository;

    @Autowired
    private CrmDatabaseSourceRepository crmDatabaseSourceRepository;

    @Autowired
    private CrmGroupRepository crmGroupRepository;


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public CrmPhoneResponse initializeCallData(String id) {

        CrmPhoneResponse result = new CrmPhoneResponse() {
            {
                setResponse("Failed to initialize CRM phone data");
                setResult(false);
            }
        };

        result.setCrmDatabase(crmDatabaseRepository.findById(id).orElse(new CrmDatabase()));

        result.setResponse("CRM phone call data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public CrmPhoneResponse initializeData(User account, String id) {

        CrmPhoneResponse result = new CrmPhoneResponse() {
            {
                setResponse("Failed to initialize CRM phone data");
                setResult(false);
            }
        };

        result.setCrmDatabase(crmDatabaseRepository.findById(id).orElse(new CrmDatabase()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setCountryCodeList(Stream.of(CountryCode.values()).map(CountryCode::toString).collect(Collectors.toList()));
        result.setCountryList(Stream.of(Country.values()).collect(Collectors.toList()));
        result.setCrmDatabaseStatusList(Stream.of(CrmDatabaseStatus.values()).collect(Collectors.toList()));
        result.setCrmDatabaseTypeList(Stream.of(CrmDatabaseType.values()).collect(Collectors.toList()));
        result.setGenderList(Stream.of(Gender.values()).collect(Collectors.toList()));
        result.setLanguageList(Stream.of(Language.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setCrmDatabaseSourceList(crmDatabaseSourceRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setCrmGroupList(crmGroupRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("CRM database data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private CrmDatabase initializeInput(CrmDatabase input) {

        CrmDatabase result = input;

        logger.info("CRM database input initialized");

        return result;

    }


    public CrmPhoneResponse initializePagination(HttpServletRequest request, User account, int page) {

        CrmPhoneResponse result = new CrmPhoneResponse() {
            {
                setResponse("Failed to initialize CRM phone pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setCrmDatabaseStatusList(Stream.of(CrmDatabaseStatus.values()).collect(Collectors.toList()));
        result.setCrmDatabaseTypeList(Stream.of(CrmDatabaseType.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setCrmDatabaseSourceList(crmDatabaseSourceRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setCrmGroupList(crmGroupRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("CRM database pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public CrmDatabase loadEntry(String id) {

        return crmDatabaseRepository.findById(id).orElse(new CrmDatabase());

    }


    public CrmDatabaseResponse loadEntryCall(User account) {

        CrmDatabaseResponse result = new CrmDatabaseResponse() {
            {
                setCrmDatabase(new CrmDatabase());
                setResponse("Failed to load CRM database entry call");
                setResult(false);
            }
        };

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.DESC, "created.timestamp"));
            }
        };
        List<CrmGroup> crmGroupByUserGroupIdListStatus = crmGroupRepository.findByUserGroupIdListStatusSort(account.getGroup().getId(), Status.Active, Sort.by(sort));

        String crmGroupId = "";

        if(!crmGroupByUserGroupIdListStatus.isEmpty()) {

            crmGroupId = crmGroupByUserGroupIdListStatus.get(0).getId();

        }

        PageRequest pageRequest = PageRequest.of(0, 1, Sort.by(sort));
        List<CrmDatabase> crmDatabaseByGroupIdStatus = crmDatabaseRepository.findByGroupIdStatusPageable(crmGroupId, CrmDatabaseStatus.Pending, pageRequest);

        if(!crmDatabaseByGroupIdStatus.isEmpty()) {

            result.setCrmDatabase(crmDatabaseByGroupIdStatus.get(0));

        }

        result.setResponse("CRM database entry call loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public CrmDatabaseResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        CrmDatabaseResponse result = new CrmDatabaseResponse() {
            {
                setResponse("Failed to load CRM database pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setCrmDatabaseList(globalRepository.findPagination(query, CrmDatabase.class));
        result.setLink(data.initializePaginationLink(request, "/crm/database", page, size, result.getCrmDatabaseList().size()));

        result.setResponse("CRM database pagination loaded");
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


    public BaseResponse updateStatus(HttpServletRequest request, User account, CrmDatabase updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update CRM database status",
                false
        );

        CrmDatabase crmDatabaseById = crmDatabaseRepository.findById(updateData.getId()).orElse(null);

        if(crmDatabaseById != null) {

            crmDatabaseLogDataRepository.save(data.initializeLogData("crmDatabaseId", crmDatabaseById, account, CrmDatabaseLogData.class));

            updateData.setCreated(crmDatabaseById.getCreated());
            updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            crmDatabaseRepository.save(updateData);

            userLogService.insert(request, "", "Update CRM database id " + updateData.getId(), UserLogType.UpdateCrmDatabase, crmDatabaseById.getId(), crmDatabaseById.getName().getFirst(), account);

            result.setResponse("CRM database updated");
            result.setResult(true);

        } else {

            result.setResponse("CRM database doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
