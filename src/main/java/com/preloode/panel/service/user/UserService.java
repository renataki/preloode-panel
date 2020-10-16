package com.preloode.panel.service.user;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.EncryptionComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.*;
import com.preloode.panel.enumeration.setting.ApplicationStatus;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.enumeration.user.UserStatus;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.CreditReference;
import com.preloode.panel.model.setting.Setting;
import com.preloode.panel.model.user.*;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.payment.PaymentMethodRepository;
import com.preloode.panel.repository.setting.SettingRepository;
import com.preloode.panel.repository.thirdParty.ThirdPartyAccountRepository;
import com.preloode.panel.repository.thirdParty.ThirdPartyProviderRepository;
import com.preloode.panel.repository.user.*;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String cookieFilter = "fltusr";

    private static final String cookiePagination = "pgnusr";

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private EncryptionComponent encryption;

    @Autowired
    private FileComponent file;

    @Autowired
    private UserLogService userLogService;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private ThirdPartyAccountRepository thirdPartyAccountRepository;

    @Autowired
    private ThirdPartyProviderRepository thirdPartyProviderRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserLogDataRepository userLogDataRepository;

    @Autowired
    private UserLogRepository userLogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    public void checkAccess(HttpServletResponse response) {

        Setting setting = settingRepository.findOneSort(Sort.by(Sort.Direction.DESC, "created.timestamp"));

        if(setting != null) {

            if(setting.getStatus() == ApplicationStatus.UnderMaintenance) {

                try {

                    response.setHeader("Location", global.getSetting().getUrl().getBase() + "/under-maintenance/");
                    response.setStatus(302);

                    logger.info("Redirected to under maintenance page");

                } catch(Exception exception) {

                    logger.error(exception.getMessage());

                }

            }

        } else {

            try {

                response.setHeader("Location", global.getSetting().getUrl().getBase() + "/installation/");
                response.setStatus(302);

                logger.info("Redirected to installation page");

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

    }


    public String checkLogin(HttpServletRequest request) {

        String result = "";

        UserAgentReference userAgent = initializeAgent(request);

        List<UserLog> userLogByAuthenticationDesc = userLogRepository.findByAuthenticationSort(data.initializeCookie(request, global.getCookie().getPrefix() + "acn"), Sort.by(Sort.Direction.DESC, "created.timestamp"));

        if(!userLogByAuthenticationDesc.isEmpty()) {

            for(UserLog userLog : userLogByAuthenticationDesc) {

                if(userLog.getType() == UserLogType.Login && userLog.getAgent().getBrowser().getId().equals(userAgent.getBrowser().getId()) && userLog.getAgent().getBrowser().getManufacturer().equals(userAgent.getBrowser().getManufacturer()) && userLog.getAgent().getBrowser().getName().equals(userAgent.getBrowser().getName()) && userLog.getAgent().getBrowser().getRenderingEngine().equals(userAgent.getBrowser().getRenderingEngine()) && userLog.getAgent().getBrowser().getType().equals(userAgent.getBrowser().getType()) && userLog.getAgent().getBrowser().getVersion().equals(userAgent.getBrowser().getVersion()) && userLog.getAgent().getDevice().getId().equals(userAgent.getDevice().getId()) && userLog.getAgent().getDevice().getManufacturer().equals(userAgent.getDevice().getManufacturer()) && userLog.getAgent().getDevice().getOperatingSystem().equals(userAgent.getDevice().getOperatingSystem()) && userLog.getAgent().getDevice().getType().equals(userAgent.getDevice().getType()) && userLog.getAgent().getId().equals(userAgent.getId()) && userLog.getAgent().getIp().equals(userAgent.getIp()) && userLog.getAgent().getMac().equals(userAgent.getMac())) {

                    result = userLog.getUser().getId();

                    break;

                } else if(userLog.getType() == UserLogType.Logout) {

                    break;

                }

            }

        }

        logger.info("User login checked");

        return result;

    }


    public BaseResponse checkPassword(HttpServletRequest request, HttpServletResponse response, User user) {

        BaseResponse result = new BaseResponse(
                "Failed to check user password",
                false
        );

        user.getPassword().setMain(encryption.rsaDecrypt(global.getEncryption().getServerRsaPrivateKey(), user.getPassword().getMain()));

        String authentication = encryption.rsaEncrypt(global.getEncryption().getServerRsaPublicKey(), global.getCookie().getPrefix() + "pnllog" + new Date().toString());

        User userByUsername = userRepository.findByUsername(user.getUsername());

        if(userByUsername != null) {

            if(encryption.rsaDecrypt(global.getEncryption().getServerRsaPrivateKey(), userByUsername.getPassword().getMain()).equals(user.getPassword().getMain()) || encryption.rsaDecrypt(global.getEncryption().getServerRsaPrivateKey(), userByUsername.getPassword().getRecovery()).equals(user.getPassword().getMain())) {

                if(userByUsername.getStatus() == UserStatus.Active) {

                    List<UserLog> userLogByCreatedUserIdDesc = userLogRepository.findByCreatedUserIdSort(userByUsername.getId(), Sort.by(Sort.Direction.DESC, "created.timestamp"));

                    if(!userLogByCreatedUserIdDesc.isEmpty()) {

                        for(UserLog userLog : userLogByCreatedUserIdDesc) {

                            if(userLog.getType() == UserLogType.Login) {

                                userLogService.insert(request, userLog.getAuthentication(), "Logout", UserLogType.Logout, userByUsername.getId(), userByUsername.getUsername(), userByUsername);

                                break;

                            } else if(userLog.getType() == UserLogType.Logout) {

                                break;

                            }

                        }

                    }

                    userLogService.insert(request, authentication, "Login", UserLogType.Login, userByUsername.getId(), userByUsername.getUsername(), userByUsername);

                    Cookie servletCookie = new Cookie(global.getCookie().getPrefix() + "acn", authentication);
                    servletCookie.setPath(global.getCookie().getPath());
                    servletCookie.setMaxAge(365 * 24 * 60 * 60);
                    response.addCookie(servletCookie);

                    result.setResponse("Password invalid");
                    result.setResult(true);

                } else if(userByUsername.getStatus() == UserStatus.Blocked) {

                    result.setResponse("Account blocked");

                } else if(userByUsername.getStatus() == UserStatus.Inactive) {

                    result.setResponse("Account inactive");

                } else if(userByUsername.getStatus() == UserStatus.Suspended) {

                    result.setResponse("Account suspended");

                }

            } else {

                result.setResponse("Password doesn't match");

            }

        } else {

            result.setResponse("Username doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse checkPrivilege(String action, String privilege) {

        BaseResponse result = new BaseResponse(
                "Failed to check user privilege",
                false
        );

        if(action.equals("view") && Integer.parseInt(privilege.substring(0, 1)) == 7) {

            result.setResponse("User view privilege granted");
            result.setResult(true);

        } else if(action.equals("add") && Integer.parseInt(privilege.substring(1, 2)) == 7) {

            result.setResponse("User add privilege granted");
            result.setResult(true);

        } else if(action.equals("edit") && Integer.parseInt(privilege.substring(2, 3)) == 7) {

            result.setResponse("User edit privilege granted");
            result.setResult(true);

        } else if(action.equals("delete") && Integer.parseInt(privilege.substring(3, 4)) == 7) {

            result.setResponse("User delete privilege granted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public UserLoginResponse checkUsername(User user) {

        UserLoginResponse result = new UserLoginResponse() {
            {
                setResponse("Failed to check user username");
                setResult(false);
            }
        };

        User userByUsername = userRepository.findByUsername(user.getUsername());

        if(userByUsername != null) {

            result.setAvatar(userByUsername.getAvatar());
            result.setName(initializeName(userByUsername));
            result.setUsername(userByUsername.getUsername());

            result.setResponse("Login username exist");
            result.setResult(true);

        } else {

            result.setResponse("Login username doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    private UserPasswordReference convertPasswordToClientEncryption(UserPasswordReference password) {

        UserPasswordReference result = password;

        if(result.getMain() != null) {

            String passwordMain = encryption.rsaDecrypt(global.getEncryption().getServerRsaPrivateKey(), result.getMain());
            result.setMain(encryption.rsaEncrypt(global.getEncryption().getClientRsaPublicKey(), passwordMain));

        }

        if(result.getRecovery() != null) {

            String passwordRecovery = encryption.rsaDecrypt(global.getEncryption().getServerRsaPrivateKey(), result.getRecovery());
            result.setRecovery(encryption.rsaEncrypt(global.getEncryption().getClientRsaPublicKey(), passwordRecovery));

        }

        logger.info("Password converted to client encryption");

        return result;

    }


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete user",
                false
        );

        User userById = userRepository.findById(id).orElse(null);

        if(userById != null) {

            userLogDataRepository.save(data.initializeLogData("userId", userById, account, UserLogData.class));

            userRepository.delete(userById);

            file.deleteImage(global.getSetting().getPath().getImage() + "/user", userById.getAvatar());

            userLogService.insert(request, "", "Delete user id " + userById.getId(), UserLogType.DeleteUser, userById.getId(), userById.getUsername(), account);

            result.setResponse("User deleted");
            result.setResult(true);

        } else {

            result.setResponse("User doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public User initializeAccount(HttpServletRequest request) {

        User result = new User();

        String checkLogin = checkLogin(request);

        if(!checkLogin.isEmpty()) {

            result = userRepository.findById(checkLogin).orElse(new User());

        }

        logger.info("User account initialized");

        return result;

    }


    public UserAgentReference initializeAgent(HttpServletRequest request) {

        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

        UserAgentReference result = new UserAgentReference(
                Integer.toString(userAgent.getId()),
                new UserBrowserReference(
                        Integer.toString(userAgent.getBrowser().getId()),
                        userAgent.getBrowser().getName(),
                        userAgent.getBrowser().getManufacturer().getName(),
                        userAgent.getBrowser().getRenderingEngine().getName(),
                        userAgent.getBrowser().getBrowserType().getName(),
                        userAgent.getBrowserVersion().getVersion()
                ),
                new UserDeviceReference(
                        Integer.toString(userAgent.getOperatingSystem().getId()),
                        userAgent.getOperatingSystem().getManufacturer().getName(),
                        userAgent.getOperatingSystem().getName(),
                        userAgent.getOperatingSystem().getDeviceType().getName()
                ),
                request.getHeader("X-FORWARDED-FOR"),
                data.initializeCookie(request, "JSESSIONID"),
                ""
        );

        if(result.getIp() == null) {

            result.setIp(request.getRemoteAddr());

        }

        try {

            InetAddress inetAddress = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(inetAddress);
            byte[] hardwareAddress = network.getHardwareAddress();

            StringBuilder stringBuilder = new StringBuilder();

            for(int i = 0; i < hardwareAddress.length; i++) {

                stringBuilder.append(String.format("%02X%s", hardwareAddress[i], (i < hardwareAddress.length - 1) ? "-" : ""));

            }

            result.setMac(stringBuilder.toString());

            logger.info("Client MAC address initialized");

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        logger.info("User agent initialized");

        return result;

    }


    public UserResponse initializeData(User account, String id) {

        UserResponse result = new UserResponse() {
            {
                setResponse("Failed to initialize user data");
                setResult(false);
            }
        };

        result.setUser(userRepository.findById(id).orElse(new User()));

        if(result.getUser().getPassword() != null) {

            result.getUser().setPassword(convertPasswordToClientEncryption(result.getUser().getPassword()));

        }

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));
        result.setCountryCodeList(Stream.of(CountryCode.values()).map(CountryCode::toString).collect(Collectors.toList()));
        result.setCountryList(Stream.of(Country.values()).collect(Collectors.toList()));
        result.setGenderList(Stream.of(Gender.values()).collect(Collectors.toList()));
        result.setLanguageList(Stream.of(Language.values()).collect(Collectors.toList()));
        result.setUserStatusList(Stream.of(UserStatus.values()).collect(Collectors.toList()));
        result.setUserTypeList(Stream.of(UserType.values()).collect(Collectors.toList()));

        if(account.getType() != UserType.System) {

            result.setUserTypeList(Stream.of(UserType.values()).filter(userType -> !userType.equals(UserType.System)).collect(Collectors.toList()));

        }

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setPaymentMethodList(paymentMethodRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setUserGroupList(userGroupRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setUserRoleList(userRoleRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setThirdPartyAccountList(thirdPartyAccountRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setThirdPartyProviderList(thirdPartyProviderRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("User data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private User initializeInput(User input) {

        User result = input;

        if(result.getAccount().getCreditList() == null) {

            result.getAccount().setCreditList(new ArrayList<>());

        }

        for(int i = 0; i < result.getAccount().getThirdParty().getAccount().getIdList().size(); i++) {

            if(i < result.getAccount().getCreditList().size()) {

                if(result.getAccount().getCreditList().get(i) == null) {

                    result.getAccount().getCreditList().set(i, BigDecimal.ZERO);

                }

            } else {

                result.getAccount().getCreditList().add(BigDecimal.ZERO);

            }

        }

        if(result.getCredit() == null) {

            result.setCredit(new CreditReference(BigDecimal.ZERO, BigDecimal.ZERO));

        }

        if(result.getCredit().getMain() == null) {

            result.getCredit().setMain(BigDecimal.ZERO);

        }

        if(result.getCredit().getPromotion() == null) {

            result.getCredit().setPromotion(BigDecimal.ZERO);

        }

        logger.info("User input initialized");

        return result;

    }


    public String initializeName(User user) {

        String result = user.getName().getFirst();

        if(!user.getName().getMiddle().isEmpty()) {

            result += " " + user.getName().getMiddle();

        }

        if(!user.getName().getLast().isEmpty()) {

            result += " " + user.getName().getLast();

        }

        logger.info("User name initialized");

        return result;

    }


    public UserResponse initializePagination(HttpServletRequest request, User account, int page) {

        UserResponse result = new UserResponse() {
            {
                setResponse("Failed to initialize user pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setCountryList(Stream.of(Country.values()).collect(Collectors.toList()));
        result.setGenderList(Stream.of(Gender.values()).collect(Collectors.toList()));
        result.setUserStatusList(Stream.of(UserStatus.values()).collect(Collectors.toList()));
        result.setUserTypeList(Stream.of(UserType.values()).collect(Collectors.toList()));

        if(account.getType() != UserType.System) {

            result.setUserTypeList(Stream.of(UserType.values()).filter(userType -> !userType.equals(UserType.System)).collect(Collectors.toList()));

        }

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setUserGroupList(userGroupRepository.findByStatusSort(Status.Active, Sort.by(sort)));
        result.setUserRoleList(userRoleRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("User pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public UserResponse initializePaymentMethod() {

        UserResponse result = new UserResponse() {
            {
                setResponse("Failed to initialize user payment method");
                setResult(false);
            }
        };

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setPaymentMethodList(paymentMethodRepository.findByStatusSort(Status.Active, Sort.by(sort)));

        result.setResponse("User payment method initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public UserResponse initializeRole(String id) {

        UserResponse result = new UserResponse() {
            {
                setResponse("Failed to initialize user role");
                setResult(false);
            }
        };

        result.setUserRole(userRoleRepository.findById(id).orElse(new UserRole()));

        result.setResponse("User role initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String id, String contactEmailAddress, String contactFaxNumber, String contactLineId, String contactPhoneNumber, String contactWechatId, String contactWhatsappNumber, String username) {

        Map<String, BaseResponse> result = new HashMap<>() {
            {
                put("userUsername", data.validateUserUsername(id, username));
            }
        };

        if(!contactEmailAddress.isEmpty()) {

            result.put("userContactEmailAddress", data.validateUserContactEmailAddress(id, contactEmailAddress));

        }

        if(!contactFaxNumber.isEmpty()) {

            result.put("userContactFaxNumber", data.validateUserContactFaxNumber(id, contactFaxNumber));

        }

        if(!contactLineId.isEmpty()) {

            result.put("userContactLineId", data.validateUserContactLineId(id, contactLineId));

        }

        if(!contactPhoneNumber.isEmpty()) {

            result.put("userContactPhoneNumber", data.validateUserContactPhoneNumber(id, contactPhoneNumber));

        }

        if(!contactWechatId.isEmpty()) {

            result.put("userContactWechatId", data.validateUserContactWechatId(id, contactWechatId));

        }

        if(!contactWhatsappNumber.isEmpty()) {

            result.put("userContactWhatsappNumber", data.validateUserContactWhatsappNumber(id, contactWhatsappNumber));

        }

        logger.info("User input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, User insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert user",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getContact().getEmailAddress(), insertData.getContact().getFaxNumber(), insertData.getContact().getLineId(), insertData.getContact().getPhoneNumber(), insertData.getContact().getWechatId(), insertData.getContact().getWhatsappNumber(), insertData.getUsername());

        boolean valid = true;

        for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

            if(!map.getValue().isResult()) {

                valid = false;

                result.setResponse(map.getValue().getResponse());

                break;

            }

        }

        if(valid) {

            for(int i = 0; i < insertData.getAccount().getThirdParty().getAccount().getIdList().size(); i++) {

                insertData.getAccount().getCreditList().set(i, BigDecimal.ZERO);

            }

            insertData.setCredit(new CreditReference(BigDecimal.ZERO, BigDecimal.ZERO));

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            userRepository.save(insertData);

            userLogService.insert(request, "", "Insert user id " + insertData.getId(), UserLogType.InsertUser, insertData.getId(), insertData.getUsername(), account);

            result.setResponse("User inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public User loadEntry(String id) {

        return userRepository.findById(id).orElse(new User());

    }


    public UserResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        UserResponse result = new UserResponse() {
            {
                setResponse("Failed to load user pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);

        if(account.getType() != UserType.System) {

            query = query.addCriteria(Criteria.where("type").ne(UserType.System));

        }

        query = query.with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setUserList(globalRepository.findPagination(query, User.class));
        result.setLink(data.initializePaginationLink(request, "/user", page, size, result.getUserList().size()));

        result.setResponse("User pagination loaded");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse logout(HttpServletRequest request, HttpServletResponse response) {

        BaseResponse result = new BaseResponse(
                "Logout failed",
                false
        );

        String checkLogin = checkLogin(request);

        if(!checkLogin.isEmpty()) {

            User userById = userRepository.findById(checkLogin).orElse(null);

            if(userById != null) {

                userLogService.insert(request, "", "Logout", UserLogType.Logout, userById.getId(), userById.getUsername(), userById);

                data.removeAllCookie(request, response);

                result.setResponse("Logged out");
                result.setResult(true);

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse removeFilterPagination(HttpServletRequest request, HttpServletResponse response, User account) {

        return data.removeFilter(request, response, account.getId(), global.getCookie().getPrefix() + cookieFilter);

    }


    public BaseResponse setPagination(HttpServletResponse response, User account, int pagination) {

        return data.setPagination(response, account.getId(), global.getCookie().getPrefix() + cookiePagination, pagination);

    }


    public BaseResponse update(HttpServletRequest request, User account, User updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update user",
                false
        );

        updateData = initializeInput(updateData);

        User userById = userRepository.findById(updateData.getId()).orElse(null);

        if(userById != null) {

            Map<String, BaseResponse> validation = inputValidation(userById.getId(), updateData.getContact().getEmailAddress(), updateData.getContact().getFaxNumber(), updateData.getContact().getLineId(), updateData.getContact().getPhoneNumber(), updateData.getContact().getWechatId(), updateData.getContact().getWhatsappNumber(), updateData.getUsername());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                for(int i = 0; i < updateData.getAccount().getThirdParty().getAccount().getIdList().size(); i++) {

                    for(int j = 0; j < userById.getAccount().getThirdParty().getAccount().getIdList().size(); j++) {

                        if(userById.getAccount().getThirdParty().getAccount().getIdList().get(j).equals(updateData.getAccount().getThirdParty().getAccount().getIdList().get(i))) {

                            updateData.getAccount().getCreditList().set(i, userById.getAccount().getCreditList().get(j));

                        }

                    }

                }

                updateData.setCredit(userById.getCredit());

                if(!updateData.getAvatar().contains(userById.getAvatar())) {

                    file.deleteImage(global.getSetting().getPath().getImage() + "/user", userById.getAvatar());

                }

                userLogDataRepository.save(data.initializeLogData("userId", userById, account, UserLogData.class));

                updateData.setCreated(userById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                userRepository.save(updateData);

                userLogService.insert(request, "", "Update user id " + updateData.getId(), UserLogType.UpdateUser, userById.getId(), userById.getUsername(), account);

                result.setResponse("User updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("User doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
