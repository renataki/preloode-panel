package com.preloode.panel.service.setting;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.global.CountryCode;
import com.preloode.panel.enumeration.setting.ApplicationStatus;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.setting.Setting;
import com.preloode.panel.model.setting.SettingLogData;
import com.preloode.panel.model.setting.SettingResponse;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.setting.SettingLogDataRepository;
import com.preloode.panel.repository.setting.SettingRepository;
import com.preloode.panel.service.user.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class SettingService {


    private static final Logger logger = LoggerFactory.getLogger(SettingService.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private FileComponent file;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private SettingLogDataRepository settingLogDataRepository;

    @Autowired
    private SettingRepository settingRepository;


    public SettingResponse initializeData() {

        SettingResponse result = new SettingResponse() {
            {
                setResponse("Failed to initialize setting data");
                setResult(false);
            }
        };

        result.setSetting(settingRepository.findOneSort(Sort.by(Sort.Direction.DESC, "created.timestamp")));

        result.setApplicationStatusList(Stream.of(ApplicationStatus.values()).collect(Collectors.toList()));
        result.setCountryCodeList(Stream.of(CountryCode.values()).map(CountryCode::toString).collect(Collectors.toList()));
        result.setCountryList(Stream.of(Country.values()).collect(Collectors.toList()));

        result.setResponse("Setting data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Setting initializeInput(Setting input) {

        Setting result = input;

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

        logger.info("Setting input initialized");

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String contactEmailAddress, String contactFaxNumber, String contactLineId, String contactPhoneNumber, String contactWechatId, String contactWhatsappNumber, String name) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("settingName", data.validateCompanyName(id, name));
            }
        };

        if(!contactEmailAddress.isEmpty()) {

            result.put("settingContactEmailAddress", data.validateCompanyContactEmailAddress(id, contactEmailAddress));

        }

        if(!contactFaxNumber.isEmpty()) {

            result.put("settingContactFaxNumber", data.validateCompanyContactEmailAddress(id, contactFaxNumber));

        }

        if(!contactLineId.isEmpty()) {

            result.put("settingContactLineId", data.validateCompanyContactLineId(id, contactLineId));

        }

        if(!contactPhoneNumber.isEmpty()) {

            result.put("settingContactPhoneNumber", data.validateCompanyContactPhoneNumber(id, contactPhoneNumber));

        }

        if(!contactWechatId.isEmpty()) {

            result.put("settingContactWechatId", data.validateCompanyContactWechatId(id, contactWechatId));

        }

        if(!contactWhatsappNumber.isEmpty()) {

            result.put("settingContactWhatsappNumber", data.validateCompanyContactWhatsappNumber(id, contactWhatsappNumber));

        }

        logger.info("Setting input validated");

        return result;

    }


    public Setting loadEntry() {

        return settingRepository.findOneSort(Sort.by(Sort.Direction.DESC, "created.timestamp"));

    }


    public BaseResponse update(HttpServletRequest request, User account, Setting updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update setting",
                false
        );

        updateData = initializeInput(updateData);

        Setting settingById = settingRepository.findById(updateData.getId()).orElse(null);

        if(settingById != null) {

            Map<String, BaseResponse> validation = inputValidation(settingById.getId(), updateData.getContact().getEmailAddress(), updateData.getContact().getFaxNumber(), updateData.getContact().getLineId(), updateData.getContact().getPhoneNumber(), updateData.getContact().getWechatId(), updateData.getContact().getWhatsappNumber(), updateData.getName());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                if(!updateData.getFavicon().equals(settingById.getFavicon())) {

                    file.deleteImage(global.getSetting().getPath().getImage() + "/setting/thumbnail", settingById.getFavicon());

                }

                if(!updateData.getLogo().equals(settingById.getLogo())) {

                    file.deleteImage(global.getSetting().getPath().getImage() + "/setting/thumbnail", settingById.getLogo());

                }

                settingLogDataRepository.save(data.initializeLogData("settingId", settingById, account, SettingLogData.class));

                updateData.setCreated(settingById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                settingRepository.save(updateData);

                userLogService.insert(request, "", "Update setting id " + updateData.getId(), UserLogType.UpdateSetting, settingById.getId(), settingById.getName(), account);

                result.setResponse("Setting updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Setting doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
