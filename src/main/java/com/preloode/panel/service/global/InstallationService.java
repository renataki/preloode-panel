package com.preloode.panel.service.global;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.EncryptionComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Country;
import com.preloode.panel.enumeration.global.Gender;
import com.preloode.panel.enumeration.global.Language;
import com.preloode.panel.enumeration.global.SwitchStatus;
import com.preloode.panel.enumeration.setting.ApplicationStatus;
import com.preloode.panel.enumeration.user.UserStatus;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.company.*;
import com.preloode.panel.model.global.*;
import com.preloode.panel.model.setting.*;
import com.preloode.panel.model.user.*;
import com.preloode.panel.repository.company.CompanyBranchRepository;
import com.preloode.panel.repository.company.CompanyRepository;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.setting.SettingRepository;
import com.preloode.panel.repository.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;


@Service
public class InstallationService {


    private static final Logger logger = LoggerFactory.getLogger(InstallationService.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private DataComponent data;

    @Autowired
    private EncryptionComponent encryption;

    @Autowired
    private CompanyBranchRepository companyBranchRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private GlobalRepository globalRepository;

    @Autowired
    private SettingRepository settingRepository;

    @Autowired
    private UserRepository userRepository;


    public BaseResponse checkInstallation() {

        BaseResponse result = new BaseResponse(
                "Failed to check installation",
                false
        );

        Setting setting = settingRepository.findOneSort(Sort.by(Sort.Direction.DESC, "created.timestamp"));

        if(setting != null) {

            result.setResponse("Installation initialized");
            result.setResult(true);

        } else {

            result.setResponse("Installation doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    private BaseResponse initializeData() {

        BaseResponse result = new BaseResponse(
                "Failed to initialize installation data",
                false
        );

        Setting settingData = new Setting(
                null,
                data.initializeTimestampReference("0", "System", null),
                data.initializeTimestampReference("0", "System", null),
                new SettingActivationReference(
                        true,
                        true
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Jakarta Utara",
                new ContactReference(
                        "renataki@preloode.com",
                        "",
                        "renataki",
                        "+6285772723989",
                        "renataki",
                        "+6285772723989"
                ),
                Country.Indonesia,
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "favicon.ico",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "logo.png",
                new SettingMaintenanceReference(
                        new Date(),
                        new Date()
                ),
                new SettingMembershipReference(
                        true
                ),
                new MetaReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        "preloode,data,admin,panel,admin panel,data admin panel,preloode data admin panel,preloode data admin panel,management,system,management system,data management system,data management system,customize data panel,customize management system",
                        ""
                ),
                "Preloode Panel",
                new OgReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        ""
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "",
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "DKI Jakarta",
                ApplicationStatus.Online,
                "Jl. Pluit Karang Permai III No.42 RT 8/15",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "14450"
        );
        settingRepository.save(settingData);

        User userData = new User(
                null,
                data.initializeTimestampReference("0", "System", null),
                data.initializeTimestampReference("0", "System", null),
                new UserAccountReference(
                        new ArrayList<>(),
                        new UserThirdPartyReference(
                                new UserThirdPartyAccountReference(
                                        new ArrayList<>(),
                                        new ArrayList<>()
                                ),
                                new UserThirdPartyProviderReference(
                                        new ArrayList<>(),
                                        new ArrayList<>()
                                )
                        ),
                        new ArrayList<>()
                ),
                "renataki-avatar.png",
                "Jakarta Utara",
                new CompanyListReference(
                        new CompanyBranchListReference(
                                new ArrayList<>(),
                                new ArrayList<>()
                        ),
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new ContactReference(
                        "renataki@preloode.com",
                        "",
                        "renataki",
                        "+6285724646711",
                        "renataki",
                        "+6285724646711"
                ),
                Country.Indonesia,
                new CreditReference(
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                Gender.Male,
                new UserGroupReference(
                        "0",
                        "System",
                        UserType.System
                ),
                Language.English,
                new NameReference(
                        "Ren",
                        "Ataki",
                        ""
                ),
                new UserPasswordReference(
                        encryption.rsaEncrypt(global.getEncryption().getServerRsaPublicKey(), "Brokenbone89."),
                        encryption.rsaEncrypt(global.getEncryption().getServerRsaPublicKey(), "Brokenbone89.")
                ),
                new UserPaymentReference(
                        new ArrayList<>(),
                        new UserPaymentMethodReference(
                                new ArrayList<>(),
                                new ArrayList<>(),
                                new ArrayList<>()
                        ),
                        new ArrayList<>(),
                        new ArrayList<>()
                ),
                new UserPrivilegeReference(
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777",
                        "7777"
                ),
                "",
                new UserRoleReference(
                        "0",
                        "System"
                ),
                "DKI Jakarta",
                UserStatus.Active,
                "Jl. Pluit Karang Permai III No.42 RT 8/15",
                UserType.System,
                "renataki",
                "14450"
        );
        userRepository.save(userData);

        result.setResponse("System data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse initializeDemoData() {

        BaseResponse result = new BaseResponse(
                "Failed to initialize demo data",
                false
        );

        Company companyData = new Company(
                null,
                data.initializeTimestampReference("0", "System", null),
                data.initializeTimestampReference("0", "System", null),
                new SettingActivationReference(
                        true,
                        true
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Jakarta Utara",
                new ContactReference(
                        "renataki@preloode.com",
                        "",
                        "renataki",
                        "+6285772723989",
                        "renataki",
                        "+6285772723989"
                ),
                Country.Indonesia,
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "favicon.ico",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "logo.png",
                new SettingMaintenanceReference(
                        new Date(),
                        new Date()
                ),
                new MetaReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        "preloode,data,admin,panel,admin panel,data admin panel,preloode data admin panel,preloode data admin panel,management,system,management system,data management system,data management system,customize data panel,customize management system",
                        ""
                ),
                "Preloode Digital Inc.",
                new OgReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        ""
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "",
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                BigInteger.ZERO,
                "DKI Jakarta",
                ApplicationStatus.Online,
                "Jl. Pluit Karang Permai III No.42 RT 8/15",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "14450"
        );
        companyRepository.save(companyData);

        CompanyBranch companyBranchData = new CompanyBranch(
                null,
                data.initializeTimestampReference("0", "System", null),
                data.initializeTimestampReference("0", "System", null),
                new SettingActivationReference(
                        true,
                        true
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Jakarta Utara",
                new CompanyReference(
                        companyData.getId(),
                        companyData.getName()
                ),
                new ContactReference(
                        "renataki@preloode.com",
                        "",
                        "renataki",
                        "+6285772723989",
                        "renataki",
                        "+6285772723989"
                ),
                Country.Indonesia,
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "favicon.ico",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "logo.png",
                new SettingMaintenanceReference(
                        new Date(),
                        new Date()
                ),
                new MetaReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        "preloode,data,admin,panel,admin panel,data admin panel,preloode data admin panel,preloode data admin panel,management,system,management system,data management system,data management system,customize data panel,customize management system",
                        ""
                ),
                "Preloode Digital Inc. Muara Karang",
                new OgReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        ""
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "",
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                BigInteger.ZERO,
                "DKI Jakarta",
                ApplicationStatus.Online,
                "Jl. Pluit Karang Permai III No.42 RT 8/15",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "14450"
        );
        companyBranchRepository.save(companyBranchData);

        companyBranchData = new CompanyBranch(
                null,
                data.initializeTimestampReference("0", "System", null),
                data.initializeTimestampReference("0", "System", null),
                new SettingActivationReference(
                        true,
                        true
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Jakarta Utara",
                new CompanyReference(
                        companyData.getId(),
                        companyData.getName()
                ),
                new ContactReference(
                        "renataki@preloode.com",
                        "",
                        "renataki",
                        "+6285772723989",
                        "renataki",
                        "+6285772723989"
                ),
                Country.Indonesia,
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "favicon.ico",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "logo.png",
                new SettingMaintenanceReference(
                        new Date(),
                        new Date()
                ),
                new MetaReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        "preloode,data,admin,panel,admin panel,data admin panel,preloode data admin panel,preloode data admin panel,management,system,management system,data management system,data management system,customize data panel,customize management system",
                        ""
                ),
                "Preloode Digital Inc. Muara Baru",
                new OgReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        ""
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "",
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                BigInteger.ZERO,
                "DKI Jakarta",
                ApplicationStatus.Online,
                "Jl. Pluit Karang Permai III No.42 RT 8/15",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "14450"
        );
        companyBranchRepository.save(companyBranchData);

        companyData = new Company(
                null,
                data.initializeTimestampReference("0", "System", null),
                data.initializeTimestampReference("0", "System", null),
                new SettingActivationReference(
                        true,
                        true
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Jakarta Utara",
                new ContactReference(
                        "renataki@preloode.com",
                        "",
                        "renataki",
                        "+6285772723989",
                        "renataki",
                        "+6285772723989"
                ),
                Country.Indonesia,
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "favicon.ico",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "logo.png",
                new SettingMaintenanceReference(
                        new Date(),
                        new Date()
                ),
                new MetaReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        "preloode,data,admin,panel,admin panel,data admin panel,preloode data admin panel,preloode data admin panel,management,system,management system,data management system,data management system,customize data panel,customize management system",
                        ""
                ),
                "Rezuna Apparel Inc.",
                new OgReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        ""
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "",
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                BigInteger.ZERO,
                "DKI Jakarta",
                ApplicationStatus.Online,
                "Jl. Pluit Karang Permai III No.42 RT 8/15",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "14450"
        );
        companyRepository.save(companyData);

        companyBranchData = new CompanyBranch(
                null,
                data.initializeTimestampReference("0", "System", null),
                data.initializeTimestampReference("0", "System", null),
                new SettingActivationReference(
                        true,
                        true
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Jakarta Utara",
                new CompanyReference(
                        companyData.getId(),
                        companyData.getName()
                ),
                new ContactReference(
                        "renataki@preloode.com",
                        "",
                        "renataki",
                        "+6285772723989",
                        "renataki",
                        "+6285772723989"
                ),
                Country.Indonesia,
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "favicon.ico",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "logo.png",
                new SettingMaintenanceReference(
                        new Date(),
                        new Date()
                ),
                new MetaReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        "preloode,data,admin,panel,admin panel,data admin panel,preloode data admin panel,preloode data admin panel,management,system,management system,data management system,data management system,customize data panel,customize management system",
                        ""
                ),
                "Rezuna Apparel Inc. Muara Karang",
                new OgReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        ""
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "",
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                BigInteger.ZERO,
                "DKI Jakarta",
                ApplicationStatus.Online,
                "Jl. Pluit Karang Permai III No.42 RT 8/15",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "14450"
        );
        companyBranchRepository.save(companyBranchData);

        companyBranchData = new CompanyBranch(
                null,
                data.initializeTimestampReference("0", "System", null),
                data.initializeTimestampReference("0", "System", null),
                new SettingActivationReference(
                        true,
                        true
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Jakarta Utara",
                new CompanyReference(
                        companyData.getId(),
                        companyData.getName()
                ),
                new ContactReference(
                        "renataki@preloode.com",
                        "",
                        "renataki",
                        "+6285772723989",
                        "renataki",
                        "+6285772723989"
                ),
                Country.Indonesia,
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "favicon.ico",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "logo.png",
                new SettingMaintenanceReference(
                        new Date(),
                        new Date()
                ),
                new MetaReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        "preloode,data,admin,panel,admin panel,data admin panel,preloode data admin panel,preloode data admin panel,management,system,management system,data management system,data management system,customize data panel,customize management system",
                        ""
                ),
                "Rezuna Apparel Inc. Muara Baru",
                new OgReference(
                        "Preloode panel, data management system with a simple layout and high end feature. Customize your own data management system with us!",
                        ""
                ),
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                "",
                new SwitchReference(
                        SwitchStatus.On,
                        SwitchStatus.On
                ),
                BigInteger.ZERO,
                "DKI Jakarta",
                ApplicationStatus.Online,
                "Jl. Pluit Karang Permai III No.42 RT 8/15",
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                new SettingTransactionReference(
                        BigInteger.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO
                ),
                "14450"
        );
        companyBranchRepository.save(companyBranchData);

        result.setResponse("Demo data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse install() {

        BaseResponse result = new BaseResponse(
                "Failed to install application",
                false
        );

        BaseResponse initializeData = initializeData();

        if(initializeData.isResult()) {

            result.setResponse("Application installed");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse uninstall(HttpServletRequest request, HttpServletResponse response) {

        BaseResponse result = new BaseResponse(
                "Failed to uninstall application",
                false
        );

        globalRepository.dropAllCollection();

        data.removeAllCookie(request, response);

        result.setResponse("Application uninstalled");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


}
