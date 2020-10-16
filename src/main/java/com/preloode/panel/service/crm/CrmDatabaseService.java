package com.preloode.panel.service.crm;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.crm.CrmDatabaseStatus;
import com.preloode.panel.enumeration.crm.CrmDatabaseType;
import com.preloode.panel.enumeration.global.*;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.model.company.CompanyBranchListReference;
import com.preloode.panel.model.company.CompanyListReference;
import com.preloode.panel.model.crm.*;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.ContactReference;
import com.preloode.panel.model.global.NameReference;
import com.preloode.panel.model.global.TimestampReference;
import com.preloode.panel.model.user.User;
import com.preloode.panel.model.user.UserReference;
import com.preloode.panel.repository.crm.CrmDatabaseLogDataRepository;
import com.preloode.panel.repository.crm.CrmDatabaseRepository;
import com.preloode.panel.repository.crm.CrmDatabaseSourceRepository;
import com.preloode.panel.repository.crm.CrmGroupRepository;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.service.user.UserLogService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Count;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class CrmDatabaseService {


    private static final Logger logger = LoggerFactory.getLogger(CrmDatabaseService.class);

    private static final String cookieFilter = "fltcrmdts";

    private static final String cookiePagination = "pgncrmdts";

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


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete CRM database",
                false
        );

        CrmDatabase crmDatabaseById = crmDatabaseRepository.findById(id).orElse(null);

        if(crmDatabaseById != null) {

            crmDatabaseLogDataRepository.save(data.initializeLogData("crmDatabaseId", crmDatabaseById, account, CrmDatabaseLogData.class));

            crmDatabaseRepository.delete(crmDatabaseById);

            userLogService.insert(request, "", "Delete CRM database id " + crmDatabaseById.getId(), UserLogType.DeleteCrmDatabase, crmDatabaseById.getId(), crmDatabaseById.getName().getFirst(), account);

            result.setResponse("CRM database deleted");
            result.setResult(true);

        } else {

            result.setResponse("CRM database doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public CrmDatabaseResponse importDatabase(MultipartHttpServletRequest multipartRequest, User account) {

        CrmDatabaseResponse result = new CrmDatabaseResponse() {
            {
                setResponse("Failed to import CRM database file");
                setResult(false);
            }
        };

        Iterator<String> files = multipartRequest.getFileNames();

        while(files.hasNext()) {

            List<MultipartFile> multipartFiles = multipartRequest.getFiles(files.next());

            for(MultipartFile multipartFile : multipartFiles) {

                XSSFWorkbook workbook = null;

                try {

                    workbook = new XSSFWorkbook(multipartFile.getInputStream());

                } catch(Exception exception) {

                    logger.error(exception.getMessage());

                }

                if(workbook != null) {

                    XSSFSheet xssfSheet = workbook.getSheetAt(0);

                    List<CrmDatabase> crmDatabaseList = new ArrayList<>();

                    for(int i = 1; i < xssfSheet.getPhysicalNumberOfRows(); i++) {

                        CrmDatabase crmDatabase = new CrmDatabase(
                                null,
                                data.initializeTimestampReference(account.getId(), account.getUsername(), null),
                                data.initializeTimestampReference(account.getId(), account.getUsername(), null),
                                new ArrayList<>(),
                                "",
                                new CompanyListReference(
                                        new CompanyBranchListReference(
                                                new ArrayList<>(),
                                                new ArrayList<>()
                                        ),
                                        new ArrayList<>(),
                                        new ArrayList<>()
                                ),
                                new ContactReference(
                                        "",
                                        "",
                                        "",
                                        "",
                                        "",
                                        ""
                                ),
                                Country.Unknown,
                                Gender.Unknown,
                                new CrmGroupReference(
                                        "",
                                        ""
                                ),
                                Language.Unknown,
                                new NameReference(
                                        "",
                                        "",
                                        ""
                                ),
                                "",
                                new CrmDatabaseSourceReference(
                                        "",
                                        ""
                                ),
                                "",
                                CrmDatabaseStatus.Unknown,
                                "",
                                CrmDatabaseType.Unknown,
                                new UserReference(
                                        "",
                                        ""
                                ),
                                ""
                        );

                        XSSFRow row = xssfSheet.getRow(i);

                        XSSFCell firstNameCell = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        firstNameCell.setCellType(CellType.STRING);
                        crmDatabase.getName().setFirst(firstNameCell.getStringCellValue());

                        XSSFCell middleNameCell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        middleNameCell.setCellType(CellType.STRING);
                        crmDatabase.getName().setMiddle(middleNameCell.getStringCellValue());

                        XSSFCell lastNameCell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        lastNameCell.setCellType(CellType.STRING);
                        crmDatabase.getName().setLast(lastNameCell.getStringCellValue());

                        XSSFCell emailAddressCell = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        emailAddressCell.setCellType(CellType.STRING);
                        crmDatabase.getContact().setEmailAddress(emailAddressCell.getStringCellValue());

                        XSSFCell faxNumberCell = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        faxNumberCell.setCellType(CellType.STRING);
                        crmDatabase.getContact().setFaxNumber(faxNumberCell.getStringCellValue());

                        XSSFCell phoneNumberCell = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        phoneNumberCell.setCellType(CellType.STRING);
                        crmDatabase.getContact().setPhoneNumber(phoneNumberCell.getStringCellValue());

                        XSSFCell whatsappNumberCell = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        whatsappNumberCell.setCellType(CellType.STRING);
                        crmDatabase.getContact().setWhatsappNumber(whatsappNumberCell.getStringCellValue());

                        XSSFCell lineIdCell = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        lineIdCell.setCellType(CellType.STRING);
                        crmDatabase.getContact().setLineId(lineIdCell.getStringCellValue());

                        XSSFCell wechatIdCell = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        wechatIdCell.setCellType(CellType.STRING);
                        crmDatabase.getContact().setWechatId(wechatIdCell.getStringCellValue());

                        XSSFCell streetAddressCell = row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        streetAddressCell.setCellType(CellType.STRING);
                        crmDatabase.setStreetAddress(streetAddressCell.getStringCellValue());

                        XSSFCell cityCell = row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        cityCell.setCellType(CellType.STRING);
                        crmDatabase.setCity(cityCell.getStringCellValue());

                        XSSFCell stateCell = row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        stateCell.setCellType(CellType.STRING);
                        crmDatabase.setState(stateCell.getStringCellValue());

                        XSSFCell countryCell = row.getCell(11, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        countryCell.setCellType(CellType.STRING);

                        if(Arrays.asList(Country.values()).contains(countryCell.getStringCellValue())) {

                            crmDatabase.setCountry(Country.valueOf(countryCell.getStringCellValue()));

                        }

                        XSSFCell zipCodeCell = row.getCell(12, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        zipCodeCell.setCellType(CellType.STRING);
                        crmDatabase.setZipCode(zipCodeCell.getStringCellValue());

                        XSSFCell sourceCell = row.getCell(13, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        sourceCell.setCellType(CellType.STRING);

                        CrmDatabaseSource crmDatabaseSourceByName = crmDatabaseSourceRepository.findByName(sourceCell.getStringCellValue());

                        if(crmDatabaseSourceByName != null) {

                            crmDatabase.setSource(new CrmDatabaseSourceReference(
                                    crmDatabaseSourceByName.getId(),
                                    crmDatabaseSourceByName.getName()
                            ));

                        }

                        crmDatabaseList.add(crmDatabase);

                    }

                    if(!crmDatabaseList.isEmpty()) {

                        crmDatabaseRepository.saveAll(crmDatabaseList);

                    }

                }

            }

        }

        result.setResponse("CRM database file imported");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public CrmDatabaseResponse initializeData(User account, String id) {

        CrmDatabaseResponse result = new CrmDatabaseResponse() {
            {
                setResponse("Failed to initialize CRM database data");
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


    public CrmDatabaseResponse initializePagination(HttpServletRequest request, User account, int page) {

        CrmDatabaseResponse result = new CrmDatabaseResponse() {
            {
                setResponse("Failed to initialize CRM database pagination");
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


    private Map<String, BaseResponse> inputValidation(String id, String contactEmailAddress, String contactFaxNumber, String contactLineId, String contactPhoneNumber, String contactWechatId, String contactWhatsappNumber) {

        Map<String, BaseResponse> result = new HashMap<>();

        if(!contactEmailAddress.isEmpty()) {

            result.put("crmDatabaseContactEmailAddress", data.validateCrmDatabaseContactEmailAddress(id, contactEmailAddress));

        }

        if(!contactFaxNumber.isEmpty()) {

            result.put("crmDatabaseContactFaxNumber", data.validateCrmDatabaseContactFaxNumber(id, contactFaxNumber));

        }

        if(!contactLineId.isEmpty()) {

            result.put("crmDatabaseContactLineId", data.validateCrmDatabaseContactLineId(id, contactLineId));

        }

        if(!contactPhoneNumber.isEmpty()) {

            result.put("crmDatabaseContactPhoneNumber", data.validateCrmDatabaseContactPhoneNumber(id, contactPhoneNumber));

        }

        if(!contactWechatId.isEmpty()) {

            result.put("crmDatabaseContactWechatId", data.validateCrmDatabaseContactWechatId(id, contactWechatId));

        }

        if(!contactWhatsappNumber.isEmpty()) {

            result.put("crmDatabaseContactWhatsappNumber", data.validateCrmDatabaseContactWhatsappNumber(id, contactWhatsappNumber));

        }

        logger.info("CRM database input validated");

        return result;

    }


    public BaseResponse insert(HttpServletRequest request, User account, CrmDatabase insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert CRM database",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation("", insertData.getContact().getEmailAddress(), insertData.getContact().getFaxNumber(), insertData.getContact().getLineId(), insertData.getContact().getPhoneNumber(), insertData.getContact().getWechatId(), insertData.getContact().getWhatsappNumber());

        boolean valid = true;

        for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

            if(!map.getValue().isResult()) {

                valid = false;

                result.setResponse(map.getValue().getResponse());

                break;

            }

        }

        if(valid) {

            for(String string : insertData.getCompany().getBranch().getIdList()) {

                insertData.getAttemptList().add(BigInteger.ZERO);

            }

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            crmDatabaseRepository.save(insertData);

            userLogService.insert(request, "", "Insert CRM database id " + insertData.getId(), UserLogType.InsertCrmDatabase, insertData.getId(), insertData.getName().getFirst(), account);

            result.setResponse("CRM database inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public CrmDatabase loadEntry(String id) {

        return crmDatabaseRepository.findById(id).orElse(new CrmDatabase());

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


    public BaseResponse update(HttpServletRequest request, User account, CrmDatabase updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update CRM database",
                false
        );

        updateData = initializeInput(updateData);

        CrmDatabase crmDatabaseById = crmDatabaseRepository.findById(updateData.getId()).orElse(null);

        if(crmDatabaseById != null) {

            Map<String, BaseResponse> validation = inputValidation(crmDatabaseById.getId(), updateData.getContact().getEmailAddress(), updateData.getContact().getFaxNumber(), updateData.getContact().getLineId(), updateData.getContact().getPhoneNumber(), updateData.getContact().getWechatId(), updateData.getContact().getWhatsappNumber());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                crmDatabaseLogDataRepository.save(data.initializeLogData("crmDatabaseId", crmDatabaseById, account, CrmDatabaseLogData.class));

                updateData.setCreated(crmDatabaseById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                crmDatabaseRepository.save(updateData);

                userLogService.insert(request, "", "Update CRM database id " + updateData.getId(), UserLogType.UpdateCrmDatabase, crmDatabaseById.getId(), crmDatabaseById.getName().getFirst(), account);

                result.setResponse("CRM database updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("CRM database doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


}
