package com.preloode.panel.service.setting;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.setting.SettingSlider;
import com.preloode.panel.model.setting.SettingSliderLogData;
import com.preloode.panel.model.setting.SettingSliderResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.setting.SettingSliderLogDataRepository;
import com.preloode.panel.repository.setting.SettingSliderRepository;
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
public class SettingSliderService {


    private static final Logger logger = LoggerFactory.getLogger(SettingSliderService.class);

    private static final String cookieFilter = "fltstgsld";

    private static final String cookiePagination = "pgnstgsld";

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
    private SettingSliderLogDataRepository settingSliderLogDataRepository;

    @Autowired
    private SettingSliderRepository settingSliderRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete setting slider",
                false
        );

        SettingSlider settingSliderById = settingSliderRepository.findById(id).orElse(null);

        if(settingSliderById != null) {

            settingSliderLogDataRepository.save(data.initializeLogData("settingSliderId", settingSliderById, account, SettingSliderLogData.class));

            settingSliderRepository.delete(settingSliderById);

            for(String string : settingSliderById.getImageList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/setting/slider", string);

            }

            userLogService.insert(request, "", "Delete setting slider id " + settingSliderById.getId(), UserLogType.DeleteSettingSlider, settingSliderById.getId(), settingSliderById.getName(), account);

            result.setResponse("Setting slider deleted");
            result.setResult(true);

        } else {

            result.setResponse("Setting slider doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public SettingSliderResponse initializeData(User account, String id) {

        SettingSliderResponse result = new SettingSliderResponse() {
            {
                setResponse("Failed to initialize setting slider data");
                setResult(false);
            }
        };

        result.setSettingSlider(settingSliderRepository.findById(id).orElse(new SettingSlider()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("Setting slider data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private SettingSlider initializeInput(SettingSlider input) {

        SettingSlider result = input;

        if(result.getSequence() == null) {

            result.setSequence(BigInteger.ZERO);

        }

        if(!result.getUrl().isEmpty()) {

            if(!result.getUrl().substring(result.getUrl().length() - 1).equals("/")) {

                result.setUrl(result.getUrl() + "/");

            }

        }

        logger.info("Setting slider input initialized");

        return result;

    }


    public SettingSliderResponse initializePagination(HttpServletRequest request, User account, int page) {

        SettingSliderResponse result = new SettingSliderResponse() {
            {
                setResponse("Failed to initialize setting slider pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setStatusList(Stream.of(Status.values()).collect(Collectors.toList()));

        result.setResponse("Setting slider pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("settingSliderName", data.validateSettingSliderName(id, name));
            }
        };

        logger.info("Setting slider input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, SettingSlider insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert setting slider",
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
            settingSliderRepository.save(insertData);

            userLogService.insert(request, "", "Insert setting slider id " + insertData.getId(), UserLogType.InsertSettingSlider, insertData.getId(), insertData.getName(), account);

            result.setResponse("Setting slider inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public SettingSliderResponse loadBranchIdListCompanyIdListActive() {

        SettingSliderResponse result = new SettingSliderResponse() {
            {
                setResponse("Failed to load setting slider branch id list company id list active");
                setResult(false);
            }
        };

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setSettingSliderList(settingSliderRepository.findByBranchIdListCompanyIdListStatusSort(global.getSetting().getCompanyBranch(), global.getSetting().getCompany(), Status.Active, Sort.by(sort)));

        result.setResponse("Setting slider branch id list company id list active loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public SettingSlider loadEntry(String id) {

        return settingSliderRepository.findById(id).orElse(new SettingSlider());

    }


    public SettingSliderResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        SettingSliderResponse result = new SettingSliderResponse() {
            {
                setResponse("Failed to load setting slider pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setSettingSliderList(globalRepository.findPagination(query, SettingSlider.class));
        result.setLink(data.initializePaginationLink(request, "/setting/slider", page, size, result.getSettingSliderList().size()));

        result.setResponse("Setting slider pagination loaded");
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


    public BaseResponse update(HttpServletRequest request, User account, SettingSlider updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update setting slider",
                false
        );

        updateData = initializeInput(updateData);

        SettingSlider settingSliderById = settingSliderRepository.findById(updateData.getId()).orElse(null);

        if(settingSliderById != null) {

            Map<String, BaseResponse> validation = inputValidation(settingSliderById.getId(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                for(String string : settingSliderById.getImageList()) {

                    if(!updateData.getImageList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/setting/slider", string);

                    }

                }

                settingSliderLogDataRepository.save(data.initializeLogData("settingSliderId", settingSliderById, account, SettingSliderLogData.class));

                updateData.setCreated(settingSliderById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                settingSliderRepository.save(updateData);

                userLogService.insert(request, "", "Update setting slider id " + updateData.getId(), UserLogType.UpdateSettingSlider, settingSliderById.getId(), settingSliderById.getName(), account);

                result.setResponse("Setting slider updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Setting slider doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
