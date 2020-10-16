package com.preloode.panel.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.TypeIncrement;
import com.preloode.panel.enumeration.transaction.TransactionType;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.blog.BlogCategory;
import com.preloode.panel.model.blog.BlogPost;
import com.preloode.panel.model.blog.BlogStar;
import com.preloode.panel.model.company.Company;
import com.preloode.panel.model.company.CompanyBranch;
import com.preloode.panel.model.crm.CrmDatabase;
import com.preloode.panel.model.crm.CrmDatabaseSource;
import com.preloode.panel.model.crm.CrmGroup;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.TimestampReference;
import com.preloode.panel.model.payment.PaymentAccount;
import com.preloode.panel.model.payment.PaymentMethod;
import com.preloode.panel.model.setting.SettingSlider;
import com.preloode.panel.model.shop.ShopBrand;
import com.preloode.panel.model.shop.ShopCategory;
import com.preloode.panel.model.shop.ShopProduct;
import com.preloode.panel.model.thirdParty.ThirdPartyAccount;
import com.preloode.panel.model.thirdParty.ThirdPartyProvider;
import com.preloode.panel.model.transaction.TicketNumber;
import com.preloode.panel.model.transaction.TicketNumberLogData;
import com.preloode.panel.model.user.User;
import com.preloode.panel.model.user.UserGroup;
import com.preloode.panel.model.user.UserReference;
import com.preloode.panel.model.user.UserRole;
import com.preloode.panel.repository.blog.BlogCategoryRepository;
import com.preloode.panel.repository.blog.BlogPostRepository;
import com.preloode.panel.repository.blog.BlogStarRepository;
import com.preloode.panel.repository.company.CompanyBranchRepository;
import com.preloode.panel.repository.company.CompanyRepository;
import com.preloode.panel.repository.crm.CrmDatabaseRepository;
import com.preloode.panel.repository.crm.CrmDatabaseSourceRepository;
import com.preloode.panel.repository.crm.CrmGroupRepository;
import com.preloode.panel.repository.payment.PaymentAccountRepository;
import com.preloode.panel.repository.payment.PaymentMethodRepository;
import com.preloode.panel.repository.setting.SettingSliderRepository;
import com.preloode.panel.repository.shop.ShopBrandRepository;
import com.preloode.panel.repository.shop.ShopCategoryRepository;
import com.preloode.panel.repository.shop.ShopProductRepository;
import com.preloode.panel.repository.thirdParty.ThirdPartyAccountRepository;
import com.preloode.panel.repository.thirdParty.ThirdPartyProviderRepository;
import com.preloode.panel.repository.transaction.TicketNumberLogDataRepository;
import com.preloode.panel.repository.transaction.TicketNumberRepository;
import com.preloode.panel.repository.user.UserGroupRepository;
import com.preloode.panel.repository.user.UserRepository;
import com.preloode.panel.repository.user.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;


@Component
public class DataComponent {


    private static final Logger logger = LoggerFactory.getLogger(DataComponent.class);

    @Autowired
    private GlobalConfiguration global;

    @Autowired
    private BlogCategoryRepository blogCategoryRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @Autowired
    private BlogStarRepository blogStarRepository;

    @Autowired
    private CompanyBranchRepository companyBranchRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CrmDatabaseRepository crmDatabaseRepository;

    @Autowired
    private CrmDatabaseSourceRepository crmDatabaseSourceRepository;

    @Autowired
    private CrmGroupRepository crmGroupRepository;

    @Autowired
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private SettingSliderRepository settingSliderRepository;

    @Autowired
    private ShopBrandRepository shopBrandRepository;

    @Autowired
    private ShopCategoryRepository shopCategoryRepository;

    @Autowired
    private ShopProductRepository shopProductRepository;

    @Autowired
    private ThirdPartyAccountRepository thirdPartyAccountRepository;

    @Autowired
    private ThirdPartyProviderRepository thirdPartyProviderRepository;

    @Autowired
    private TicketNumberLogDataRepository ticketNumberLogDataRepository;

    @Autowired
    private TicketNumberRepository ticketNumberRepository;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;


    private <T> T checkIncrementData(TypeIncrement type, String id, List<String> categoryId, String parentId, String url) {

        T result = null;

        if(type == TypeIncrement.BlogCategory) {

            if(!id.isEmpty()) {

                result = (T) blogCategoryRepository.findByUrlParentIdNotId(url, parentId, id);

            } else {

                result = (T) blogCategoryRepository.findByUrlParentId(url, parentId);

            }

        } else if(type == TypeIncrement.BlogPost) {

            if(!id.isEmpty()) {

                result = (T) blogPostRepository.findByUrlInCategoryIdNotId(url, new HashSet(categoryId), id);

            } else {

                result = (T) blogPostRepository.findByUrlInCategoryId(url, new HashSet(categoryId));

            }

        } else if(type == TypeIncrement.BlogStar) {

            if(!id.isEmpty()) {

                result = (T) blogStarRepository.findByUrlNotId(url, id);

            } else {

                result = (T) blogStarRepository.findByUrl(url);

            }

        } else if(type == TypeIncrement.ShopBrand) {

            if(!id.isEmpty()) {

                result = (T) shopBrandRepository.findByUrlNotId(url, id);

            } else {

                result = (T) shopBrandRepository.findByUrl(url);

            }

        } else if(type == TypeIncrement.ShopCategory) {

            if(!id.isEmpty()) {

                result = (T) shopCategoryRepository.findByUrlParentIdNotId(url, parentId, id);

            } else {

                result = (T) shopCategoryRepository.findByUrlParentId(url, parentId);

            }

        } else if(type == TypeIncrement.ShopProduct) {

            if(!id.isEmpty()) {

                result = (T) shopProductRepository.findByUrlInCategoryIdNotId(url, new HashSet(categoryId), id);

            } else {

                result = (T) shopProductRepository.findByUrlInCategoryId(url, new HashSet(categoryId));

            }

        }

        logger.info("Increment repository initialized");

        return result;

    }


    public String generateTicketNumber(TransactionType transactionType) {

        String result = "";

        if(transactionType == TransactionType.Adjustment) {

            result = "ADSN";

        } else if(transactionType == TransactionType.Checkout) {

            result = "CHKO";

        } else if(transactionType == TransactionType.Deposit) {

            result = "DPOS";

        } else if(transactionType == TransactionType.Expense) {

            result = "EXPS";

        } else if(transactionType == TransactionType.Income) {

            result = "INCE";

        } else if(transactionType == TransactionType.Internal) {

            result = "ITNL";

        } else if(transactionType == TransactionType.Other) {

            result = "OTHR";

        } else if(transactionType == TransactionType.Withdrawal) {

            result = "WDAL";

        } else if(transactionType == TransactionType.Transfer) {

            result = "TAFR";

        }

        TicketNumber ticketNumber = new TicketNumber(
                null,
                initializeTimestampReference("0", "System", null),
                initializeTimestampReference("0", "System", null),
                "",
                transactionType
        );

        TicketNumber ticketNumberByType = ticketNumberRepository.findByType(transactionType);

        if(ticketNumberByType != null) {

            BigInteger lastNumber = new BigInteger(ticketNumberByType.getLast().replaceAll("[^0-9]+", "")).add(BigInteger.ONE);
            String lastNumberString = lastNumber.toString();

            if(lastNumberString.length() <= 10) {

                int zeroLength = 10 - lastNumberString.length();

                for(int i = 0; i < zeroLength; i++) {

                    result += "0";

                }

                result += lastNumberString;

            } else {

                result += lastNumberString;

            }

            ticketNumber.setId(ticketNumberByType.getId());

            TicketNumberLogData ticketNumberLogData = new TicketNumberLogData(
                    null,
                    initializeTimestampReference("0", "System", null),
                    initializeTimestampReference("0", "System", null),
                    ticketNumberByType.getLast(),
                    ticketNumberByType.getId(),
                    ticketNumberByType.getType()
            );
            ticketNumberLogDataRepository.save(ticketNumberLogData);

        } else {

            result += "0000000001";

        }

        ticketNumber.setLast(result);
        ticketNumberRepository.save(ticketNumber);

        logger.info("Ticket number generated");

        return result;

    }


    public <T> String incrementUrl(TypeIncrement type, String id, List<String> categoryId, String parentId, String url) {

        String result = "";

        if(url.substring(url.length() - 1).equals("/")) {

            url = url.substring(0, url.length() - 1);

        }

        result = url;

        T model = checkIncrementData(type, id, categoryId, parentId, url);

        int i = 1;

        while(model != null) {

            result = url + "-" + i + "/";

            model = checkIncrementData(type, id, categoryId, parentId, result);

            i++;

        }

        logger.info("URL incremented");

        return result;

    }


    public String initializeCookie(HttpServletRequest request, String cookieName) {

        String result = "";

        Cookie[] cookies = request.getCookies();

        if(cookies != null) {

            for(Cookie servletCookie : cookies) {

                if(servletCookie.getName().equals(cookieName)) {

                    result = servletCookie.getValue();

                    break;

                }

            }

        }

        logger.info("Cookie initialized");

        return result;

    }


    private Map<String, Object> initializeFilterCookie(String accountId, String json) {

        Map<String, Object> result = new HashMap<>();

        if(!json.isEmpty()) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> jsonMap = objectMapper.readValue(json, Map.class);

                if(!jsonMap.isEmpty()) {

                    for(Map.Entry<String, Object> map : jsonMap.entrySet()) {

                        if(map.getKey().equals(accountId)) {

                            result = (Map<String, Object>) map.getValue();

                            break;

                        }

                    }

                }

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info("Filter cookie initialized");

        return result;

    }


    public Map<String, Object> initializeFilterValue(HttpServletRequest request, String accountId, String cookieName) {

        Map<String, Object> result = new HashMap<>();

        Map<String, Object> accountFilter = initializeFilterCookie(accountId, initializeCookie(request, cookieName));

        if(!accountFilter.isEmpty()) {

            for(Map.Entry<String, Object> map : accountFilter.entrySet()) {

                result.put(map.getKey().replace(".", "_"), map.getValue());

            }

        }

        logger.info("Filter value initialized");

        return result;

    }


    public <T, U> U initializeLogData(String parentKey, T logData, User user, Class<U> logClass) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.convertValue(logData, Map.class);

        map.put(parentKey, map.get("id"));
        map.put("created", initializeTimestampReference(user.getId(), user.getUsername(), null));
        map.put("modified", initializeTimestampReference(user.getId(), user.getUsername(), null));
        map.remove("id");

        U result = objectMapper.convertValue(map, logClass);

        logger.info("Log data initialized");

        return result;

    }


    public List<String> initializeMultiplePath(List<String> path, List<String> url) {

        List<String> result = new ArrayList<>();

        for(int i = 0; i < path.size(); i++) {

            result.add(path.get(i) + "/" + url.get(i));

            if(result.get(i).startsWith("/")) {

                result.set(i, result.get(i).replaceFirst("/", ""));

            }

        }

        logger.info("Multiple path initialized");

        return result;

    }


    private int initializePaginationCookie(String accountId, String json) {

        int result = 0;

        if(!json.isEmpty()) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Integer> jsonMap = objectMapper.readValue(json, Map.class);

                if(!jsonMap.isEmpty()) {

                    for(Map.Entry<String, Integer> map : jsonMap.entrySet()) {

                        if(map.getKey().equals(accountId)) {

                            result = map.getValue();

                            break;

                        }

                    }

                }

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info("Pagination cookie initialized");

        return result;

    }


    public String initializePaginationLink(HttpServletRequest request, String url, int page, int size, long total) {

        String result = "";

        url = request.getRequestURI().replace(request.getServletPath(), url) + "/";

        int previous = page - global.getPagination().getPrevious();
        int next = page + global.getPagination().getNext();
        int max = (int) Math.ceil(total / size);

        if(max > 1) {

            result = "<ul>";

            if(previous < 1) {

                previous = 1;

            }

            if(page > global.getPagination().getPrevious()) {

                result += "<a href=\"" + url + global.getPagination().getUrl() + "1\"><li>First</li></a>";

            }

            if(page > 1) {

                result += "<a href=\"" + url + global.getPagination().getUrl() + (page - 1) + "\"><li><i class=\"previous-white square-15\"></i></li></a>";

            }

            if(page > (global.getPagination().getPrevious() + 1)) {

                result += "<li>...</li>";

            }

            if(page > 1) {

                for(int i = previous; i < page; i++) {

                    result += "<a href=\"" + url + global.getPagination().getUrl() + i + "\"><li>" + i + "</li></a>";

                }

            }

            result += "<li>"
                    + "<form class=\"" + global.getPagination().getCss().getForm() + "\" method=\"POST\" action=\"\" " + global.getPagination().getAttribute().getForm() + ">"
                    + "<input class=\"" + global.getPagination().getCss().getInput() + "\" name=\"page\" " + global.getPagination().getAttribute().getInput() + " />"
                    + "<button class=\"" + global.getPagination().getCss().getButton() + "\" name=\"go-to-page\" " + global.getPagination().getAttribute().getButton() + ">Go</button>"
                    + "</form>"
                    + "</li>";

            if(next > max) {

                next = max;

            }

            if(page < next) {

                for(Integer i = (page + 1); i <= next; i++) {

                    result += "<a href=\"" + url + global.getPagination().getUrl() + i + "\"><li>" + i + "</li></a>";

                }

                if(next < (max - 1)) {

                    result += "<li>...</li>";

                }

                if(page < max) {

                    result += "<a href=\"" + url + global.getPagination().getUrl() + (page + 1) + "\"><li><i class=\"next-white square-15\"></i></li></a>";

                }

                if(next < max) {

                    result += "<a href=\"" + url + global.getPagination().getUrl() + max + "\"><li>Last</li></a>";

                }

            }

            result += "</ul>";

        }

        logger.info("Pagination link initialized");

        return result;

    }


    public int initializePaginationSize(HttpServletRequest request, String accountId, String cookieName) {

        int result = global.getPagination().getSize();

        int sizeCookie = initializePaginationCookie(accountId, initializeCookie(request, global.getCookie().getPrefix() + cookieName));

        if(sizeCookie > 0) {

            result = sizeCookie;

        }

        logger.info("Pagination size initialized");

        return result;

    }


    public Query initializeQueryFilter(HttpServletRequest request, String accountId, String cookieName) {

        Query result = new Query();

        Map<String, Object> accountFilter = initializeFilterCookie(accountId, initializeCookie(request, cookieName));

        if(!accountFilter.isEmpty()) {

            for(Map.Entry<String, Object> map : accountFilter.entrySet()) {

                List<String> filterList = (List<String>) map.getValue();

                if(filterList.get(0).equals("equal")) {

                    if(!filterList.get(1).isEmpty()) {

                        result.addCriteria(Criteria.where(map.getKey()).is(filterList.get(1)));

                    }

                } else if(filterList.get(0).equals("notequal")) {

                    if(!filterList.get(1).isEmpty()) {

                        result.addCriteria(Criteria.where(map.getKey()).ne(filterList.get(1)));

                    }

                } else if(filterList.get(0).equals("like")) {

                    if(!filterList.get(1).isEmpty()) {

                        result.addCriteria(Criteria.where(map.getKey()).regex(filterList.get(1)));

                    }

                } else if(filterList.get(0).equals("greterThan")) {

                    if(!filterList.get(1).isEmpty()) {

                        result.addCriteria(Criteria.where(map.getKey()).gt(filterList.get(1)));

                    }

                } else if(filterList.get(0).equals("greaterThanEqual")) {

                    if(!filterList.get(1).isEmpty()) {

                        result.addCriteria(Criteria.where(map.getKey()).gte(filterList.get(1)));

                    }

                } else if(filterList.get(0).equals("lessThan")) {

                    if(!filterList.get(1).isEmpty()) {

                        result.addCriteria(Criteria.where(map.getKey()).lt(filterList.get(1)));

                    }

                } else if(filterList.get(0).equals("lessThanEqual")) {

                    if(!filterList.get(1).isEmpty()) {

                        result.addCriteria(Criteria.where(map.getKey()).lte(filterList.get(1)));

                    }

                } else if(filterList.get(0).equals("between")) {

                    if(filterList.size() > 3) {

                        if(filterList.get(1).equals("date")) {

                            Date start = null;
                            Date end = null;

                            if(!filterList.get(2).isEmpty()) {

                                try {

                                    start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(filterList.get(2));

                                    logger.info("Query filter start date initialized.");

                                } catch(Exception exception) {

                                    logger.error(exception.getMessage());

                                }

                            }

                            if(!filterList.get(3).isEmpty()) {

                                try {

                                    end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(filterList.get(3));

                                    logger.info("Query filter end date initialized.");

                                } catch(Exception exception) {

                                    logger.error(exception.getMessage());

                                }

                            }

                            if(start != null && end != null) {

                                result.addCriteria(Criteria.where(map.getKey()).gte(start).lte(end));

                            }

                        } else {

                            result.addCriteria(Criteria.where(map.getKey()).gte(filterList.get(1)).lte(filterList.get(2)));

                        }

                    }

                }

            }

        }

        logger.info("Query filter initialized");

        return result;

    }


    public TimestampReference initializeTimestampReference(String userId, String userUsername, Date timestamp) {

        TimestampReference result = new TimestampReference(
                new Date(),
                new UserReference(
                        userId,
                        userUsername
                )
        );

        if(timestamp != null) {

            result.setTimestamp(timestamp);

        }

        logger.info("Timestamp reference initialized");

        return result;

    }


    public List<Company> initializeUserCompany(User user) {

        List<Company> result = new ArrayList<>();

        if(user.getType() == UserType.System) {

            List<Sort.Order> sort = new ArrayList<>() {
                {
                    add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                    add(new Sort.Order(Sort.Direction.ASC, "name"));
                }
            };
            result = companyRepository.findAll(Sort.by(sort));

        } else {

            for(int i = 0; i < user.getCompany().getIdList().size(); i++) {

                int index = i;
                result.add(new Company() {
                    {
                        setId(user.getCompany().getIdList().get(index));
                        setName(user.getCompany().getNameList().get(index));
                    }
                });

            }

        }

        logger.info("User company initialized");

        return result;

    }


    public List<CompanyBranch> initializeUserCompanyBranch(User user) {

        List<CompanyBranch> result = new ArrayList<>();

        if(user.getType() == UserType.System) {

            List<Sort.Order> sort = new ArrayList<>() {
                {
                    add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                    add(new Sort.Order(Sort.Direction.ASC, "name"));
                }
            };
            result = companyBranchRepository.findAll(Sort.by(sort));

        } else {

            for(int i = 0; i < user.getCompany().getBranch().getIdList().size(); i++) {

                int index = i;
                result.add(new CompanyBranch() {
                    {
                        setId(user.getCompany().getBranch().getIdList().get(index));
                        setName(user.getCompany().getBranch().getNameList().get(index));
                    }
                });

            }

        }

        logger.info("User company branch initialized");

        return result;

    }


    public void removeAllCookie(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();

        if(cookies != null) {

            for(Cookie servletCookie : cookies) {

                servletCookie.setValue(null);
                servletCookie.setPath(global.getCookie().getPath());
                servletCookie.setMaxAge(0);
                response.addCookie(servletCookie);

            }

        }

        logger.info("All cookies removed");

    }


    public BaseResponse removeFilter(HttpServletRequest request, HttpServletResponse response, String accountId, String cookieName) {

        BaseResponse result = new BaseResponse(
                "Failed to remove filter",
                false
        );

        String accountFilter = initializeCookie(request, cookieName);

        if(!accountFilter.isEmpty()) {

            try {

                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Integer> accountFilterMap = objectMapper.readValue(accountFilter, Map.class);

                if(accountFilterMap.containsKey(accountId)) {

                    accountFilterMap.remove(accountId);

                }

                Cookie filter = new Cookie(cookieName, objectMapper.writeValueAsString(accountFilterMap));
                filter.setPath(global.getCookie().getPath());
                filter.setMaxAge(30 * 24 * 60 * 60);
                response.addCookie(filter);

                result.setResponse("Filter removed");
                result.setResult(true);

            } catch(Exception exception) {

                logger.error(exception.getMessage());

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse setFilter(HttpServletResponse response, String accountId, String cookieName, Map<String, Object> data) {

        BaseResponse result = new BaseResponse(
                "Failed to set filter",
                false
        );

        Map<String, Object> filter = new HashMap<>() {
            {
                put(accountId, data);
            }
        };

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            Cookie cookie = new Cookie(cookieName, objectMapper.writeValueAsString(filter));
            cookie.setPath(global.getCookie().getPath());
            cookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(cookie);

            result.setResponse("Filter set");
            result.setResult(true);

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse setPagination(HttpServletResponse response, String accountId, String cookieName, int pagination) {

        BaseResponse result = new BaseResponse(
                "Failed to set pagination",
                false
        );

        Map<String, Integer> paginationMap = new HashMap<>() {
            {
                put(accountId, pagination);
            }
        };

        try {

            ObjectMapper objectMapper = new ObjectMapper();
            Cookie cookie = new Cookie(cookieName, objectMapper.writeValueAsString(paginationMap));
            cookie.setPath(global.getCookie().getPath());
            cookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(cookie);

            result.setResponse("Pagination set");
            result.setResult(true);

        } catch(Exception exception) {

            logger.error(exception.getMessage());

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateBlogCategoryName(String id, String name, String parentId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate blog category name",
                false
        );

        BlogCategory blogCategoryByNameParentId = blogCategoryRepository.findByNameParentId(name, parentId);

        if(blogCategoryByNameParentId == null) {

            result.setResponse("Blog category name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(blogCategoryByNameParentId.getId())) {

                    result.setResponse("Blog category name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Blog category name already exist");

                }

            } else {

                result.setResponse("Blog category name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateBlogCategoryParentId(String parentId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate blog category parent id",
                false
        );

        if(parentId.equals("0")) {

            result.setResponse("Blog category parent id validated");
            result.setResult(true);

        } else {

            BlogCategory blogCategoryById = blogCategoryRepository.findById(parentId).orElse(null);

            if(blogCategoryById != null) {

                result.setResponse("Blog category parent id validated");
                result.setResult(true);

            } else {

                result.setResponse("Blog category parent id doesn't exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateBlogPostTitle(String id, String title, Set<String> categoryId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate blog post title",
                false
        );

        BlogPost blogPostByTitleInCategoryId = blogPostRepository.findByTitleInCategoryId(title, categoryId);

        if(blogPostByTitleInCategoryId == null) {

            result.setResponse("Blog post title validated");
            result.setResult(true);

            logger.info("Blog post title validated.");

        } else {

            if(!id.isEmpty()) {

                if(id.equals(blogPostByTitleInCategoryId.getId())) {

                    result.setResponse("Blog post title validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Blog post title already exist");

                }

            } else {

                result.setResponse("Blog post title already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateBlogStarName(String id, String name) {

        BaseResponse result = new BaseResponse(
                "Failed to validate blog star name",
                false
        );

        BlogStar blogStarByName = blogStarRepository.findByName(name);

        if(blogStarByName == null) {

            result.setResponse("Blog star name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(blogStarByName.getId())) {

                    result.setResponse("Blog star name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Blog star name already exist");

                }

            } else {

                result.setResponse("Blog star name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyBranchContactEmailAddress(String id, String contactEmailAddress) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company branch contact email address",
                false
        );

        List<CompanyBranch> companyBranchByContactEmailAddress = companyBranchRepository.findByContactEmailAddress(contactEmailAddress);

        if(companyBranchByContactEmailAddress.isEmpty()) {

            result.setResponse("Company branch contact email address validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyBranchByContactEmailAddress.get(0).getId())) {

                    result.setResponse("Company branch contact email address validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company branch contact email address already exist");

                }

            } else {

                result.setResponse("Company branch contact email address already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyBranchContactFaxNumber(String id, String contactFaxNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company branch contact fax number",
                false
        );

        List<CompanyBranch> companyBranchByContactFaxNumber = companyBranchRepository.findByContactFaxNumber(contactFaxNumber);

        if(companyBranchByContactFaxNumber.isEmpty()) {

            result.setResponse("Company branch contact fax number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyBranchByContactFaxNumber.get(0).getId())) {

                    result.setResponse("Company branch contact fax number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company branch contact fax number already exist");

                }

            } else {

                result.setResponse("Company branch contact fax number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyBranchContactLineId(String id, String contactLineId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company branch contact line id",
                false
        );

        List<CompanyBranch> companyBranchByContactLineId = companyBranchRepository.findByContactLineId(contactLineId);

        if(companyBranchByContactLineId.isEmpty()) {

            result.setResponse("Company branch contact line id validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyBranchByContactLineId.get(0).getId())) {

                    result.setResponse("Company branch contact line id validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company branch contact line id already exist");

                }

            } else {

                result.setResponse("Company branch contact line id already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyBranchContactPhoneNumber(String id, String contactPhoneNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company branch contact phone number",
                false
        );

        List<CompanyBranch> companyBranchByContactPhoneNumber = companyBranchRepository.findByContactPhoneNumber(contactPhoneNumber);

        if(companyBranchByContactPhoneNumber.isEmpty()) {

            result.setResponse("Company branch contact phone number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyBranchByContactPhoneNumber.get(0).getId())) {

                    result.setResponse("Company branch contact phone number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company branch contact phone number already exist");

                }

            } else {

                result.setResponse("Company branch contact phone number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyBranchContactWechatId(String id, String contactWechatId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company branch contact wechat id",
                false
        );

        List<CompanyBranch> companyBranchByContactWechatId = companyBranchRepository.findByContactWechatId(contactWechatId);

        if(companyBranchByContactWechatId.isEmpty()) {

            result.setResponse("Company branch contact wechat id validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyBranchByContactWechatId.get(0).getId())) {

                    result.setResponse("Company branch contact wechat id validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company branch contact wechat id already exist");

                }

            } else {

                result.setResponse("Company branch contact wechat id already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyBranchContactWhatsappNumber(String id, String contactWhatsappNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company branch contact whatsapp number",
                false
        );

        List<CompanyBranch> companyBranchByContactWhatsappNumber = companyBranchRepository.findByContactWhatsappNumber(contactWhatsappNumber);

        if(companyBranchByContactWhatsappNumber.isEmpty()) {

            result.setResponse("Company branch contact whatsapp number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyBranchByContactWhatsappNumber.get(0).getId())) {

                    result.setResponse("Company branch contact whatsapp number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company branch contact whatsapp number already exist");

                }

            } else {

                result.setResponse("Company branch contact whatsapp number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyBranchName(String id, String name) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company branch name",
                false
        );

        CompanyBranch companyBranchByName = companyBranchRepository.findByName(name);

        if(companyBranchByName == null) {

            result.setResponse("Company branch name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyBranchByName.getId())) {

                    result.setResponse("Company branch name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company branch name already exist");

                }

            } else {

                result.setResponse("Company branch name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyContactEmailAddress(String id, String contactEmailAddress) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company contact email address",
                false
        );

        List<Company> companyByContactEmailAddress = companyRepository.findByContactEmailAddress(contactEmailAddress);

        if(companyByContactEmailAddress.isEmpty()) {

            result.setResponse("Company contact email address validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyByContactEmailAddress.get(0).getId())) {

                    result.setResponse("Company contact email address validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company contact email address already exist");

                }

            } else {

                result.setResponse("Company contact email address already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyContactFaxNumber(String id, String contactFaxNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company contact fax number",
                false
        );

        List<Company> companyByContactFaxNumber = companyRepository.findByContactFaxNumber(contactFaxNumber);

        if(companyByContactFaxNumber.isEmpty()) {

            result.setResponse("Company contact fax number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyByContactFaxNumber.get(0).getId())) {

                    result.setResponse("Company contact fax number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company contact fax number already exist");

                }

            } else {

                result.setResponse("Company contact fax number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyContactLineId(String id, String contactLineId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company contact line id",
                false
        );

        List<Company> companyByContactLineId = companyRepository.findByContactLineId(contactLineId);

        if(companyByContactLineId.isEmpty()) {

            result.setResponse("Company contact line id validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyByContactLineId.get(0).getId())) {

                    result.setResponse("Company contact line id validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company contact line id already exist");

                }

            } else {

                result.setResponse("Company contact line id already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyContactPhoneNumber(String id, String contactPhoneNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company contact phone number",
                false
        );

        List<Company> companyByContactPhoneNumber = companyRepository.findByContactPhoneNumber(contactPhoneNumber);

        if(companyByContactPhoneNumber.isEmpty()) {

            result.setResponse("Company contact phone number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyByContactPhoneNumber.get(0).getId())) {

                    result.setResponse("Company contact phone number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company contact phone number already exist");

                }

            } else {

                result.setResponse("Company contact phone number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyContactWechatId(String id, String contactWechatId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company contact wechat id",
                false
        );

        List<Company> companyByContactWechatId = companyRepository.findByContactWechatId(contactWechatId);

        if(companyByContactWechatId.isEmpty()) {

            result.setResponse("Company contact wechat id validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyByContactWechatId.get(0).getId())) {

                    result.setResponse("Company contact wechat id validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company contact wechat id already exist");

                }

            } else {

                result.setResponse("Company contact wechat id already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyContactWhatsappNumber(String id, String contactWhatsappNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company contact whatsapp number",
                false
        );

        List<Company> companyByContactWhatsappNumber = companyRepository.findByContactWhatsappNumber(contactWhatsappNumber);

        if(companyByContactWhatsappNumber.isEmpty()) {

            result.setResponse("Company contact whatsapp number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyByContactWhatsappNumber.get(0).getId())) {

                    result.setResponse("Company contact whatsapp number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company contact whatsapp number already exist");

                }

            } else {

                result.setResponse("Company contact whatsapp number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCompanyName(String id, String name) {

        BaseResponse result = new BaseResponse(
                "Failed to validate company name",
                false
        );

        Company companyByName = companyRepository.findByName(name);

        if(companyByName == null) {

            result.setResponse("Company name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(companyByName.getId())) {

                    result.setResponse("Company name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Company name already exist");

                }

            } else {

                result.setResponse("Company name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCrmDatabaseContactEmailAddress(String id, String contactEmailAddress) {

        BaseResponse result = new BaseResponse(
                "Failed to validate CRM database contact email address",
                false
        );

        List<CrmDatabase> crmDatabaseByContactEmailAddress = crmDatabaseRepository.findByContactEmailAddress(contactEmailAddress);

        if(crmDatabaseByContactEmailAddress.isEmpty()) {

            result.setResponse("CRM database contact email address validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(crmDatabaseByContactEmailAddress.get(0).getId())) {

                    result.setResponse("CRM database contact email address validated");
                    result.setResult(true);

                } else {

                    result.setResponse("CRM database contact email address already exist");

                }

            } else {

                result.setResponse("CRM database contact email address already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCrmDatabaseContactFaxNumber(String id, String contactFaxNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate CRM database contact fax number",
                false
        );

        List<CrmDatabase> crmDatabaseByContactFaxNumber = crmDatabaseRepository.findByContactFaxNumber(contactFaxNumber);

        if(crmDatabaseByContactFaxNumber.isEmpty()) {

            result.setResponse("CRM database contact fax number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(crmDatabaseByContactFaxNumber.get(0).getId())) {

                    result.setResponse("CRM database contact fax number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("CRM database contact fax number already exist");

                }

            } else {

                result.setResponse("CRM database contact fax number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCrmDatabaseContactLineId(String id, String contactLineId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate CRM database contact line id",
                false
        );

        List<CrmDatabase> crmDatabaseByContactLineId = crmDatabaseRepository.findByContactLineId(contactLineId);

        if(crmDatabaseByContactLineId.isEmpty()) {

            result.setResponse("CRM database contact line id validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(crmDatabaseByContactLineId.get(0).getId())) {

                    result.setResponse("CRM database contact line id validated");
                    result.setResult(true);

                } else {

                    result.setResponse("CRM database contact line id already exist");

                }

            } else {

                result.setResponse("CRM database contact line id already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCrmDatabaseContactPhoneNumber(String id, String contactPhoneNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate CRM database contact phone number",
                false
        );

        List<CrmDatabase> crmDatabaseByContactPhoneNumber = crmDatabaseRepository.findByContactPhoneNumber(contactPhoneNumber);

        if(crmDatabaseByContactPhoneNumber.isEmpty()) {

            result.setResponse("CRM database contact phone number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(crmDatabaseByContactPhoneNumber.get(0).getId())) {

                    result.setResponse("CRM database contact phone number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("CRM database contact phone number already exist");

                }

            } else {

                result.setResponse("CRM database contact phone number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCrmDatabaseContactWechatId(String id, String contactWechatId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate CRM database contact wechat id",
                false
        );

        List<CrmDatabase> crmDatabaseByContactWechatId = crmDatabaseRepository.findByContactWechatId(contactWechatId);

        if(crmDatabaseByContactWechatId.isEmpty()) {

            result.setResponse("CRM database contact wechat id validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(crmDatabaseByContactWechatId.get(0).getId())) {

                    result.setResponse("CRM database contact wechat id validated");
                    result.setResult(true);

                } else {

                    result.setResponse("CRM database contact wechat id already exist");

                }

            } else {

                result.setResponse("CRM database contact wechat id already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCrmDatabaseContactWhatsappNumber(String id, String contactWhatsappNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate CRM database contact whatsapp number",
                false
        );

        List<CrmDatabase> crmDatabaseByContactWhatsappNumber = crmDatabaseRepository.findByContactWhatsappNumber(contactWhatsappNumber);

        if(crmDatabaseByContactWhatsappNumber.isEmpty()) {

            result.setResponse("CRM database contact whatsapp number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(crmDatabaseByContactWhatsappNumber.get(0).getId())) {

                    result.setResponse("CRM database contact whatsapp number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("CRM database contact whatsapp number already exist");

                }

            } else {

                result.setResponse("CRM database contact whatsapp number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCrmDatabaseSourceName(String id, String name) {

        BaseResponse result = new BaseResponse() {
            {
                setResponse("Failed to validate CRM database source name");
                setResult(false);
            }
        };

        CrmDatabaseSource crmDatabaseSourceByNameDesc = crmDatabaseSourceRepository.findByName(name);

        if(crmDatabaseSourceByNameDesc == null) {

            result.setResponse("CRM database source name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(crmDatabaseSourceByNameDesc.getId())) {

                    result.setResponse("CRM database source name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("CRM database source name already exist");

                }

            } else {

                result.setResponse("CRM database source name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateCrmGroupName(String id, String name) {

        BaseResponse result = new BaseResponse() {
            {
                setResponse("Failed to validate CRM group name");
                setResult(false);
            }
        };

        CrmGroup crmGroupByNameDesc = crmGroupRepository.findByName(name);

        if(crmGroupByNameDesc == null) {

            result.setResponse("CRM group name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(crmGroupByNameDesc.getId())) {

                    result.setResponse("CRM group name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("CRM group name already exist");

                }

            } else {

                result.setResponse("CRM group name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validatePaymentAccount(String id, String methodId, String number) {

        BaseResponse result = new BaseResponse(
                "Failed to validate payment account",
                false
        );

        PaymentAccount paymentAccountByMethodIdNumber = paymentAccountRepository.findByMethodIdNumber(methodId, number);

        if(paymentAccountByMethodIdNumber == null) {

            result.setResponse("Payment account validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(paymentAccountByMethodIdNumber.getId())) {

                    result.setResponse("Payment account validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Payment account already exist");

                }

            } else {

                result.setResponse("Payment account already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validatePaymentMethodName(String id, String name) {

        BaseResponse result = new BaseResponse(
                "Failed to validate payment method name",
                false
        );

        PaymentMethod paymentMethodByName = paymentMethodRepository.findByName(name);

        if(paymentMethodByName == null) {

            result.setResponse("Payment method name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(paymentMethodByName.getId())) {

                    result.setResponse("Payment method name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Payment method name already exist");

                }

            } else {

                result.setResponse("Payment method name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateSettingSliderName(String id, String name) {

        BaseResponse result = new BaseResponse() {
            {
                setResponse("Failed to validate setting slider name");
                setResult(false);
            }
        };

        SettingSlider settingSliderByName = settingSliderRepository.findByName(name);

        if(settingSliderByName == null) {

            result.setResponse("Setting slider name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(settingSliderByName.getId())) {

                    result.setResponse("Setting slider name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Setting slider name already exist");

                }

            } else {

                result.setResponse("Setting slider name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateShopBrandName(String id, String name) {

        BaseResponse result = new BaseResponse(
                "Failed to validate shop brand name",
                false
        );

        ShopBrand shopBrandByName = shopBrandRepository.findByName(name);

        if(shopBrandByName == null) {

            result.setResponse("Shop brand name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(shopBrandByName.getId())) {

                    result.setResponse("Shop brand name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Shop brand name already exist");

                }

            } else {

                result.setResponse("Shop brand name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateShopCategoryName(String id, String name, String parentId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate shop category name",
                false
        );

        ShopCategory shopCategoryByNameParentId = shopCategoryRepository.findByNameParentId(name, parentId);

        if(shopCategoryByNameParentId == null) {

            result.setResponse("Shop category name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(shopCategoryByNameParentId.getId())) {

                    result.setResponse("Shop category name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Shop category name already exist");

                }

            } else {

                result.setResponse("Shop category name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateShopCategoryParentId(String parentId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate shop category parent id",
                false
        );

        if(parentId.equals("0")) {

            result.setResponse("Shop category parent id validated");
            result.setResult(true);

        } else {

            ShopCategory shopCategoryById = shopCategoryRepository.findById(parentId).orElse(null);

            if(shopCategoryById != null) {

                result.setResponse("Shop category parent id validated");
                result.setResult(true);

            } else {

                result.setResponse("Shop category parent id doesn't exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateShopProductName(String id, String name, Set<String> categoryId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate shop product name",
                false
        );

        ShopProduct shopProductByNameInCategoryId = shopProductRepository.findByNameInCategoryId(name, categoryId);

        if(shopProductByNameInCategoryId == null) {

            result.setResponse("Shop product name validated");
            result.setResult(true);

            logger.info("Shop product name validated.");

        } else {

            if(!id.isEmpty()) {

                if(id.equals(shopProductByNameInCategoryId.getId())) {

                    result.setResponse("Shop product name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Shop product name already exist");

                }

            } else {

                result.setResponse("Shop product name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validationThirdPartyAccountUsername(String id, String providerId, String username) {

        BaseResponse result = new BaseResponse(
                "Failed to validate third party account username",
                false
        );

        ThirdPartyAccount thirdPartyAccountByProviderIdUsername = thirdPartyAccountRepository.findByProviderIdUsername(providerId, username);

        if(thirdPartyAccountByProviderIdUsername == null) {

            result.setResponse("Third party account username validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(thirdPartyAccountByProviderIdUsername.getId())) {

                    result.setResponse("Third party account username validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Third party account username already exist");

                }

            } else {

                result.setResponse("Third party account username already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateThirdPartyProviderName(String id, String name) {

        BaseResponse result = new BaseResponse(
                "Failed to validate third party provider name",
                false
        );

        ThirdPartyProvider thirdPartyProviderByName = thirdPartyProviderRepository.findByName(name);

        if(thirdPartyProviderByName == null) {

            result.setResponse("Third party provider name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(thirdPartyProviderByName.getId())) {

                    result.setResponse("Third party provider name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("Third party provider name already exist");

                }

            } else {

                result.setResponse("Third party provider name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateTransactionPaymentAccount(String id, String methodId, String name, String number) {

        BaseResponse result = new BaseResponse(
                "Failed to validate transaction payment account",
                false
        );

        PaymentAccount paymentAccountByIdMethodIdNameNumber = paymentAccountRepository.findByIdMethodIdNameNumber(id, methodId, name, number);

        if(paymentAccountByIdMethodIdNameNumber != null) {

            result.setResponse("Transaction payment account validated");
            result.setResult(true);

        } else {

            result.setResponse("Transaction payment account doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateTransactionUser(String id, String username, String accountUsername) {

        BaseResponse result = new BaseResponse(
                "Failed to validate transaction user",
                false
        );

        User userByIdUsername = userRepository.findByIdUsername(id, username);

        if(!accountUsername.isEmpty()) {

            userByIdUsername = userRepository.findByIdUsernameAccountUsername(id, username, accountUsername);

        }


        if(userByIdUsername != null) {

            result.setResponse("Transaction user validated");
            result.setResult(true);

        } else {

            result.setResponse("Transaction user doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateUserGroupName(String id, String name) {

        BaseResponse result = new BaseResponse() {
            {
                setResponse("Failed to validate user group name");
                setResult(false);
            }
        };

        UserGroup userGroupByNameDesc = userGroupRepository.findByName(name);

        if(userGroupByNameDesc == null) {

            result.setResponse("User group name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(userGroupByNameDesc.getId())) {

                    result.setResponse("User group name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("User group name already exist");

                }

            } else {

                result.setResponse("User group name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateUserRoleName(String id, String name) {

        BaseResponse result = new BaseResponse(
                "Failed to validate user role name",
                false
        );

        UserRole userRoleByName = userRoleRepository.findByName(name);

        if(userRoleByName == null) {

            result.setResponse("User role name validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(userRoleByName.getId())) {

                    result.setResponse("User role name validated");
                    result.setResult(true);

                } else {

                    result.setResponse("User role name already exist");

                }

            } else {

                result.setResponse("User role name already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateUserContactEmailAddress(String id, String contactEmailAddress) {

        BaseResponse result = new BaseResponse(
                "Failed to validate user contact email address",
                false
        );

        List<User> userByContactEmailAddress = userRepository.findByContactEmailAddress(contactEmailAddress);

        if(userByContactEmailAddress.isEmpty()) {

            result.setResponse("User contact email address validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(userByContactEmailAddress.get(0).getId())) {

                    result.setResponse("User contact email address validated");
                    result.setResult(true);

                } else {

                    result.setResponse("User contact email address already exist");

                }

            } else {

                result.setResponse("User contact email address already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateUserContactFaxNumber(String id, String contactFaxNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate user contact fax number",
                false
        );

        List<User> userByContactFaxNumber = userRepository.findByContactFaxNumber(contactFaxNumber);

        if(userByContactFaxNumber.isEmpty()) {

            result.setResponse("User contact fax number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(userByContactFaxNumber.get(0).getId())) {

                    result.setResponse("User contact fax number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("User contact fax number already exist");

                }

            } else {

                result.setResponse("User contact fax number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateUserContactLineId(String id, String contactLineId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate user contact line id",
                false
        );

        List<User> userByContactLineId = userRepository.findByContactLineId(contactLineId);

        if(userByContactLineId.isEmpty()) {

            result.setResponse("User contact line id validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(userByContactLineId.get(0).getId())) {

                    result.setResponse("User contact line id validated");
                    result.setResult(true);

                } else {

                    result.setResponse("User contact line id already exist");

                }

            } else {

                result.setResponse("User contact line id already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateUserContactPhoneNumber(String id, String contactPhoneNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate user contact phone number",
                false
        );

        List<User> userByContactPhoneNumber = userRepository.findByContactPhoneNumber(contactPhoneNumber);

        if(userByContactPhoneNumber.isEmpty()) {

            result.setResponse("User contact phone number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(userByContactPhoneNumber.get(0).getId())) {

                    result.setResponse("User contact phone number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("User contact phone number already exist");

                }

            } else {

                result.setResponse("User contact phone number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateUserContactWechatId(String id, String contactWechatId) {

        BaseResponse result = new BaseResponse(
                "Failed to validate user contact wechat id",
                false
        );

        List<User> userByContactWechatId = userRepository.findByContactWechatId(contactWechatId);

        if(userByContactWechatId.isEmpty()) {

            result.setResponse("User contact wechat id validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(userByContactWechatId.get(0).getId())) {

                    result.setResponse("User contact wechat id validated");
                    result.setResult(true);

                } else {

                    result.setResponse("User contact wechat id already exist");

                }

            } else {

                result.setResponse("User contact wechat id already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateUserContactWhatsappNumber(String id, String contactWhatsappNumber) {

        BaseResponse result = new BaseResponse(
                "Failed to validate user contact whatsapp number",
                false
        );

        List<User> userByContactWhatsappNumber = userRepository.findByContactWhatsappNumber(contactWhatsappNumber);

        if(userByContactWhatsappNumber.isEmpty()) {

            result.setResponse("User contact whatsapp number validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(userByContactWhatsappNumber.get(0).getId())) {

                    result.setResponse("User contact whatsapp number validated");
                    result.setResult(true);

                } else {

                    result.setResponse("User contact whatsapp number already exist");

                }

            } else {

                result.setResponse("User contact whatsapp number already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse validateUserUsername(String id, String username) {

        BaseResponse result = new BaseResponse(
                "Failed to validate user username",
                false
        );

        User userByUsername = userRepository.findByUsername(username);

        if(userByUsername == null) {

            result.setResponse("User username validated");
            result.setResult(true);

        } else {

            if(!id.isEmpty()) {

                if(id.equals(userByUsername.getId())) {

                    result.setResponse("User username validated");
                    result.setResult(true);

                } else {

                    result.setResponse("User username already exist");

                }

            } else {

                result.setResponse("User username already exist");

            }

        }

        logger.info(result.getResponse());

        return result;

    }


}
