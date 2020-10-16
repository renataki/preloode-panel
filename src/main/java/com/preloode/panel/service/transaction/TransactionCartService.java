package com.preloode.panel.service.transaction;

import com.preloode.panel.component.DataComponent;
import com.preloode.panel.component.FileComponent;
import com.preloode.panel.configuration.global.GlobalConfiguration;
import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.shop.PriceRecurring;
import com.preloode.panel.enumeration.shop.ProductType;
import com.preloode.panel.enumeration.transaction.AdjustmentType;
import com.preloode.panel.enumeration.transaction.TransactionStatus;
import com.preloode.panel.enumeration.transaction.TransactionType;
import com.preloode.panel.enumeration.user.UserLogType;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.global.BaseResponse;
import com.preloode.panel.model.global.CreditReference;
import com.preloode.panel.model.payment.PaymentAccount;
import com.preloode.panel.model.transaction.*;
import com.preloode.panel.model.user.User;
import com.preloode.panel.repository.global.GlobalRepository;
import com.preloode.panel.repository.payment.PaymentAccountRepository;
import com.preloode.panel.repository.payment.PaymentMethodRepository;
import com.preloode.panel.repository.transaction.TransactionCartLogDataRepository;
import com.preloode.panel.repository.transaction.TransactionCartRepository;
import com.preloode.panel.repository.transaction.TransactionLogDataRepository;
import com.preloode.panel.repository.transaction.TransactionRepository;
import com.preloode.panel.repository.user.UserRepository;
import com.preloode.panel.service.user.UserLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class TransactionCartService {


    private static final Logger logger = LoggerFactory.getLogger(TransactionCartService.class);

    private static final String cookieFilter = "flttsncrt";

    private static final String cookiePagination = "pgntsncrt";

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
    private PaymentAccountRepository paymentAccountRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private TransactionCartLogDataRepository transactionCartLogDataRepository;

    @Autowired
    private TransactionCartRepository transactionCartRepository;

    @Autowired
    private UserRepository userRepository;


    public BaseResponse delete(HttpServletRequest request, User account, String id) {

        BaseResponse result = new BaseResponse(
                "Failed to delete transaction cart",
                false
        );

        TransactionCart transactionCartById = transactionCartRepository.findById(id).orElse(null);

        if(transactionCartById != null) {

            transactionCartLogDataRepository.save(data.initializeLogData("transactionCartId", transactionCartById, account, TransactionCartLogData.class));

            transactionCartRepository.delete(transactionCartById);

            for(String string : transactionCartById.getImageList()) {

                file.deleteImage(global.getSetting().getPath().getImage() + "/transaction/cart", string);

            }

            userLogService.insert(request, "", "Delete transaction cart id " + transactionCartById.getId(), UserLogType.DeleteTransactionCart, transactionCartById.getId(), transactionCartById.getAmount().toString(), account);

            result.setResponse("Transaction cart deleted");
            result.setResult(true);

        } else {

            result.setResponse("Transaction cart doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse filterPagination(HttpServletResponse response, User account, Map<String, Object> filter) {

        return data.setFilter(response, account.getId(), global.getCookie().getPrefix() + cookieFilter, filter);

    }


    public TransactionCartResponse initializeData(User account, String id) {

        TransactionCartResponse result = new TransactionCartResponse() {
            {
                setResponse("Failed to initialize transaction cart data");
                setResult(false);
            }
        };

        result.setTransactionCart(transactionCartRepository.findById(id).orElse(new TransactionCart()));

        result.setCompanyBranchList(data.initializeUserCompanyBranch(account));
        result.setCompanyList(data.initializeUserCompany(account));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setUserList(userRepository.findByStatusNotTypeSort(Status.Active, UserType.System, Sort.by(sort)));

        result.setResponse("Transaction cart data initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private TransactionCart initializeInput(TransactionCart input) {

        TransactionCart result = input;

        if(result.getAdditionalCost().getAdministration() == null) {

            result.getAdditionalCost().setAdministration(BigDecimal.ZERO);

        }

        if(result.getAdditionalCost().getProvider() == null) {

            result.getAdditionalCost().setProvider(BigDecimal.ZERO);

        }

        if(result.getAdditionalCost().getService() == null) {

            result.getAdditionalCost().setService(BigDecimal.ZERO);

        }

        if(result.getAdditionalCost().getTax() == null) {

            result.getAdditionalCost().setTax(BigDecimal.ZERO);

        }

        if(result.getAmount().getMain() == null) {

            result.getAmount().setMain(BigDecimal.ZERO);

        }

        if(result.getAmount().getPromotion() == null) {

            result.getAmount().setPromotion(BigDecimal.ZERO);

        }

        if(result.getProduct().getVariantList() != null) {

            for(int i = 0; i < result.getProduct().getVariantList().getNameList().size(); i++) {

                for(int j = 0; j < result.getProduct().getVariantList().getNameList().get(i).size(); j++) {

                    if(result.getProduct().getVariantList().getPrice().getOneTime().getDiscountList().get(i).get(j) == null) {

                        result.getProduct().getVariantList().getPrice().getOneTime().getDiscountList().get(i).set(j, BigDecimal.ZERO);

                    }

                    if(result.getProduct().getVariantList().getPrice().getOneTime().getNormalList().get(i).get(j) == null) {

                        result.getProduct().getVariantList().getPrice().getOneTime().getNormalList().get(i).set(j, BigDecimal.ZERO);

                    }

                    if(result.getProduct().getVariantList().getPrice().getRecurring().getDiscountList().get(i).get(j) == null) {

                        result.getProduct().getVariantList().getPrice().getRecurring().getDiscountList().get(i).set(j, new ArrayList<>());

                    }

                    if(result.getProduct().getVariantList().getPrice().getRecurring().getNormalList().get(i).get(j) == null) {

                        result.getProduct().getVariantList().getPrice().getRecurring().getNormalList().get(i).set(j, new ArrayList<>());

                    }

                    for(int k = 0; k < PriceRecurring.values().length; k++) {

                        if(k < result.getProduct().getVariantList().getPrice().getRecurring().getDiscountList().size()) {

                            if(result.getProduct().getVariantList().getPrice().getRecurring().getDiscountList().get(i).get(j).get(k) == null) {

                                result.getProduct().getVariantList().getPrice().getRecurring().getDiscountList().get(i).get(j).set(k, BigDecimal.ZERO);

                            }

                        } else {

                            result.getProduct().getVariantList().getPrice().getRecurring().getDiscountList().get(i).get(j).set(k, BigDecimal.ZERO);

                        }

                        if(k < result.getProduct().getVariantList().getPrice().getRecurring().getNormalList().size()) {

                            if(result.getProduct().getVariantList().getPrice().getRecurring().getNormalList().get(i).get(j).get(k) == null) {

                                result.getProduct().getVariantList().getPrice().getRecurring().getNormalList().get(i).get(j).set(k, BigDecimal.ZERO);

                            }

                        } else {

                            result.getProduct().getVariantList().getPrice().getRecurring().getNormalList().get(i).get(j).set(k, BigDecimal.ZERO);

                        }

                    }

                }

            }

        }

        logger.info("Transaction cart input initialized");

        return result;

    }


    public TransactionCartResponse initializePagination(HttpServletRequest request, User account, int page) {

        TransactionCartResponse result = new TransactionCartResponse() {
            {
                setResponse("Failed to initialize transaction cart pagination");
                setResult(false);
            }
        };

        result.setFilter(data.initializeFilterValue(request, account.getId(), global.getCookie().getPrefix() + cookieFilter));
        result.setPage(page);
        result.setSize(data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookiePagination));

        result.setProductTypeList(Stream.of(ProductType.values()).collect(Collectors.toList()));
        result.setTransactionStatusList(Stream.of(TransactionStatus.values()).collect(Collectors.toList()));

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "sequence"));
                add(new Sort.Order(Sort.Direction.ASC, "name"));
            }
        };
        result.setUserList(userRepository.findByStatusNotTypeSort(Status.Active, UserType.System, Sort.by(sort)));

        result.setResponse("Transaction cart pagination initialized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    private Map<String, BaseResponse> inputValidation(String fromPaymentAccountId, String fromPaymentAccountMethodId, String fromPaymentAccountName, String fromPaymentAccountNumber, String fromUserId, String fromUserUsername, String fromUserAccountUsername, String toPaymentAccountId, String toPaymentAccountMethodId, String toPaymentAccountName, String toPaymentAccountNumber, String toUserId, String toUserUsername, String toUserAccountUsername, TransactionType type) {

        Map<String, BaseResponse> result = new HashMap<>();

        if(type == TransactionType.Adjustment) {

            if(!fromPaymentAccountId.isEmpty() && !fromPaymentAccountMethodId.isEmpty() && !fromPaymentAccountName.isEmpty() && !fromPaymentAccountNumber.isEmpty()) {

                result.put("transactionFromPaymentAccount", data.validateTransactionPaymentAccount(fromPaymentAccountId, fromPaymentAccountMethodId, fromPaymentAccountName, fromPaymentAccountNumber));

            }

            if(!fromUserId.isEmpty() && !fromUserUsername.isEmpty() && !fromUserAccountUsername.isEmpty()) {

                result.put("transactionFromUser", data.validateTransactionUser(fromUserId, fromUserUsername, fromUserAccountUsername));

            }

            if(!toPaymentAccountId.isEmpty() && !toPaymentAccountMethodId.isEmpty() && !toPaymentAccountName.isEmpty() && !toPaymentAccountNumber.isEmpty()) {

                result.put("transactionToPaymentAccount", data.validateTransactionPaymentAccount(toPaymentAccountId, toPaymentAccountMethodId, toPaymentAccountName, toPaymentAccountNumber));

            }

            if(!toUserId.isEmpty() && !toUserUsername.isEmpty() && !toUserAccountUsername.isEmpty()) {

                result.put("transactionToUser", data.validateTransactionUser(toUserId, toUserUsername, toUserAccountUsername));

            }

        } else if(type == TransactionType.Deposit) {

            result.put("transactionToPaymentAccount", data.validateTransactionPaymentAccount(toPaymentAccountId, toPaymentAccountMethodId, toPaymentAccountName, toPaymentAccountNumber));
            result.put("transactionToUser", data.validateTransactionUser(toUserId, toUserUsername, toUserAccountUsername));

        } else if(type == TransactionType.Checkout) {

            result.put("transactionFromUser", data.validateTransactionUser(fromUserId, fromUserUsername, fromUserAccountUsername));
            result.put("transactionToPaymentAccount", data.validateTransactionPaymentAccount(toPaymentAccountId, toPaymentAccountMethodId, toPaymentAccountName, toPaymentAccountNumber));

        } else if(type == TransactionType.Expense) {

            result.put("transactionFromPaymentAccount", data.validateTransactionPaymentAccount(fromPaymentAccountId, fromPaymentAccountMethodId, fromPaymentAccountName, fromPaymentAccountNumber));

        } else if(type == TransactionType.Internal) {

            result.put("transactionFromPaymentAccount", data.validateTransactionPaymentAccount(fromPaymentAccountId, fromPaymentAccountMethodId, fromPaymentAccountName, fromPaymentAccountNumber));
            result.put("transactionToPaymentAccount", data.validateTransactionPaymentAccount(toPaymentAccountId, toPaymentAccountMethodId, toPaymentAccountName, toPaymentAccountNumber));

        } else if(type == TransactionType.Other) {

            /* Not set yet */

        } else if(type == TransactionType.Transfer) {

            result.put("transactionFromUser", data.validateTransactionUser(fromUserId, fromUserUsername, fromUserAccountUsername));
            result.put("transactionToUser", data.validateTransactionUser(toUserId, toUserUsername, toUserAccountUsername));

        } else if(type == TransactionType.Withdrawal) {

            result.put("transactionFromPaymentAccount", data.validateTransactionPaymentAccount(fromPaymentAccountId, fromPaymentAccountMethodId, fromPaymentAccountName, fromPaymentAccountNumber));
            result.put("transactionFromUser", data.validateTransactionUser(fromUserId, fromUserUsername, fromUserAccountUsername));

        }

        logger.info("Transaction input validated");

        return result;

    }


    /*public BaseResponse insert(HttpServletRequest request, User account, Transaction insertData) {

        BaseResponse result = new BaseResponse(
                "Failed to insert transaction",
                false
        );

        insertData = initializeInput(insertData);

        Map<String, BaseResponse> validation = inputValidation(insertData.getFrom().getPayment().getAccount().getId(), insertData.getFrom().getPayment().getMethod().getId(), insertData.getFrom().getPayment().getAccount().getName(), insertData.getFrom().getPayment().getAccount().getNumber(), insertData.getFrom().getUser().getId(), insertData.getFrom().getUser().getUsername(), insertData.getFrom().getUser().getAccount().getUsername(), insertData.getTo().getPayment().getAccount().getId(), insertData.getTo().getPayment().getMethod().getId(), insertData.getTo().getPayment().getAccount().getName(), insertData.getTo().getPayment().getAccount().getNumber(), insertData.getTo().getUser().getId(), insertData.getTo().getUser().getUsername(), insertData.getTo().getUser().getAccount().getUsername(), insertData.getType());

        boolean valid = true;

        for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

            if(!map.getValue().isResult()) {

                valid = false;

                result.setResponse(map.getValue().getResponse());

                break;

            }

        }

        if(valid) {

            if(insertData.getType() == TransactionType.Adjustment) {

                if(insertData.getFrom().getPayment().getAccount().getId() != null) {

                    insertData.getFrom().getPayment().getAccount().setCredit(decreasePaymentAccountCredit(insertData.getFrom().getPayment().getAccount().getId(), insertData.getAmount().getMain()));

                }

                if(insertData.getTo().getPayment().getAccount().getId() != null) {

                    insertData.getTo().getPayment().getAccount().setCredit(increasePaymentAccountCredit(insertData.getTo().getPayment().getAccount().getId(), insertData.getAmount().getMain()));

                }

                if(insertData.getFrom().getUser().getId() != null) {

                    insertData.getFrom().getUser().setCredit(decreaseUserCredit(insertData.getFrom().getUser().getId(), insertData.getFrom().getUser().getAccount().getUsername(), insertData.getAmount().getMain(), BigDecimal.ZERO));

                }

                if(insertData.getTo().getUser().getId() != null) {

                    insertData.getTo().getUser().setCredit(increaseUserCredit(insertData.getTo().getUser().getId(), insertData.getTo().getUser().getAccount().getUsername(), insertData.getAmount().getMain(), BigDecimal.ZERO));

                }

            } else if(insertData.getType() == TransactionType.Checkout || insertData.getType() == TransactionType.Deposit) {

                if(insertData.getTo().getPayment().getAccount().getId() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().subtract(insertData.getAdditionalCost().getAdministration()).subtract(insertData.getAdditionalCost().getProvider());
                    insertData.getTo().getPayment().getAccount().setCredit(increasePaymentAccountCredit(insertData.getTo().getPayment().getAccount().getId(), amount));

                }

                if(insertData.getTo().getUser().getUsername() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().subtract(insertData.getAdditionalCost().getAdministration()).subtract(insertData.getAdditionalCost().getProvider()).subtract(insertData.getAdditionalCost().getService()).subtract(insertData.getAdditionalCost().getTax());
                    insertData.getTo().getUser().setCredit(increaseUserCredit(insertData.getTo().getUser().getId(), insertData.getTo().getUser().getAccount().getUsername(), amount, insertData.getAmount().getPromotion()));

                }

            } else if(insertData.getType() == TransactionType.Expense) {

                if(insertData.getFrom().getPayment().getAccount().getId() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().add(insertData.getAdditionalCost().getAdministration()).add(insertData.getAdditionalCost().getProvider());
                    insertData.getFrom().getPayment().getAccount().setCredit(decreasePaymentAccountCredit(insertData.getFrom().getPayment().getAccount().getId(), amount));

                }

            } else if(insertData.getType() == TransactionType.Internal) {

                if(insertData.getFrom().getPayment().getAccount().getId() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().add(insertData.getAdditionalCost().getAdministration()).add(insertData.getAdditionalCost().getProvider());
                    insertData.getFrom().getPayment().getAccount().setCredit(decreasePaymentAccountCredit(insertData.getFrom().getPayment().getAccount().getId(), amount));

                }

                if(insertData.getTo().getPayment().getAccount().getId() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().subtract(insertData.getAdditionalCost().getAdministration()).subtract(insertData.getAdditionalCost().getProvider());
                    insertData.getTo().getPayment().getAccount().setCredit(increasePaymentAccountCredit(insertData.getTo().getPayment().getAccount().getId(), amount));

                }

            } else if(insertData.getType() == TransactionType.Income) {

                if(insertData.getTo().getPayment().getAccount().getId() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().subtract(insertData.getAdditionalCost().getAdministration()).subtract(insertData.getAdditionalCost().getProvider());
                    insertData.getTo().getPayment().getAccount().setCredit(increasePaymentAccountCredit(insertData.getTo().getPayment().getAccount().getId(), amount));

                }

            } else if(insertData.getType() == TransactionType.Transfer) {

                if(insertData.getFrom().getUser().getUsername() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().subtract(insertData.getAdditionalCost().getAdministration()).subtract(insertData.getAdditionalCost().getProvider()).subtract(insertData.getAdditionalCost().getService()).subtract(insertData.getAdditionalCost().getTax());
                    insertData.getFrom().getUser().setCredit(decreaseUserCredit(insertData.getFrom().getUser().getId(), insertData.getFrom().getUser().getAccount().getUsername(), amount, insertData.getAmount().getPromotion()));

                }

                if(insertData.getTo().getUser().getUsername() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().subtract(insertData.getAdditionalCost().getAdministration()).subtract(insertData.getAdditionalCost().getProvider()).subtract(insertData.getAdditionalCost().getService()).subtract(insertData.getAdditionalCost().getTax());
                    insertData.getTo().getUser().setCredit(increaseUserCredit(insertData.getTo().getUser().getId(), insertData.getTo().getUser().getAccount().getUsername(), amount, insertData.getAmount().getPromotion()));

                }

            } else if(insertData.getType() == TransactionType.Withdrawal) {

                if(insertData.getFrom().getPayment().getAccount().getId() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().add(insertData.getAdditionalCost().getAdministration()).add(insertData.getAdditionalCost().getProvider());
                    insertData.getFrom().getPayment().getAccount().setCredit(decreasePaymentAccountCredit(insertData.getFrom().getPayment().getAccount().getId(), amount));

                }

                if(insertData.getFrom().getUser().getUsername() != null) {

                    BigDecimal amount = insertData.getAmount().getMain().subtract(insertData.getAdditionalCost().getAdministration()).subtract(insertData.getAdditionalCost().getProvider()).subtract(insertData.getAdditionalCost().getService()).subtract(insertData.getAdditionalCost().getTax());
                    insertData.getFrom().getUser().setCredit(decreaseUserCredit(insertData.getFrom().getUser().getId(), insertData.getFrom().getUser().getAccount().getUsername(), amount, insertData.getAmount().getPromotion()));

                }

            }

            insertData.setTicketNumber(data.generateTicketNumber(insertData.getType()));

            insertData.setCreated(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            insertData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
            transactionRepository.save(insertData);

            userLogService.insert(request, "", "Insert transaction id " + insertData.getId(), UserLogType.InsertTransaction, insertData.getId(), insertData.getType().toString(), account);

            result.setResponse("Transaction inserted");
            result.setResult(true);

        }

        logger.info(result.getResponse());

        return result;

    }


    public Transaction loadEntry(String id) {

        return transactionRepository.findById(id).orElse(new Transaction());

    }


    public List<Transaction> loadMutation(String paymentAccountId) {

        Query query = new Query().addCriteria(new Criteria().orOperator(Criteria.where("from.payment.account.id").is(paymentAccountId), Criteria.where("to.payment.account.id").is(paymentAccountId))).with(Sort.by(Sort.Direction.DESC, "created.timestamp"));
        List<Transaction> result = globalRepository.findPagination(query, Transaction.class);

        logger.info("Transaction mutation loaded");

        return result;

    }


    public List<Transaction> loadMutationPagination(String paymentAccountId, int page, int size) {

        Query query = new Query().addCriteria(new Criteria().orOperator(Criteria.where("from.payment.account.id").is(paymentAccountId), Criteria.where("to.payment.account.id").is(paymentAccountId))).with(Sort.by(Sort.Direction.DESC, "created.timestamp")).with(PageRequest.of((page - 1), size));
        List<Transaction> result = globalRepository.findPagination(query, Transaction.class);

        logger.info("Transaction mutation pagination loaded");

        return result;

    }


    public TransactionResponse loadPagination(HttpServletRequest request, User account, Sort.Direction sortDirection, String sortColumn, int page) {

        TransactionResponse result = new TransactionResponse() {
            {
                setResponse("Failed to load transaction pagination");
                setResult(false);
            }
        };

        int size = data.initializePaginationSize(request, account.getId(), global.getCookie().getPrefix() + cookieFilter);
        Query query = data.initializeQueryFilter(request, account.getId(), global.getCookie().getPrefix() + cookieFilter).with(Sort.by(sortDirection, sortColumn)).with(PageRequest.of((page - 1), size));

        result.setTransactionList(globalRepository.findPagination(query, Transaction.class));
        result.setLink(data.initializePaginationLink(request, "/transaction", page, size, result.getTransactionList().size()));

        result.setResponse("Transaction pagination loaded");
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


    public BaseResponse syncronizePaymentAccountCredit(String paymentAccountId, Date createdTimestamp) {

        BaseResponse result = new BaseResponse(
                "Failed to syncronize payment account credit",
                false
        );

        BigDecimal lastCredit = BigDecimal.ZERO;

        Query query = new Query().addCriteria(new Criteria().orOperator(Criteria.where("from.payment.account.id").is(paymentAccountId), Criteria.where("to.payment.account.id").is(paymentAccountId))).addCriteria(Criteria.where("created.timestamp").lt(createdTimestamp)).with(Sort.by(Sort.Direction.DESC, "created.timestamp")).with(PageRequest.of(0, 1));
        List<Transaction> lastTransaction = globalRepository.findPagination(query, Transaction.class);

        if(!lastTransaction.isEmpty()) {

            if(lastTransaction.get(0).getFrom().getPayment().getAccount().getId() != null) {

                if(lastTransaction.get(0).getFrom().getPayment().getAccount().getId().equals(paymentAccountId)) {

                    lastCredit = lastTransaction.get(0).getFrom().getPayment().getAccount().getCredit();

                }

            } else if(lastTransaction.get(0).getTo().getPayment().getAccount().getId() != null) {

                if(lastTransaction.get(0).getTo().getPayment().getAccount().getId().equals(paymentAccountId)) {

                    lastCredit = lastTransaction.get(0).getTo().getPayment().getAccount().getCredit();

                }

            }

        }

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "created.timestamp"));
                add(new Sort.Order(Sort.Direction.ASC, "id"));
            }
        };
        List<Transaction> transactionList = transactionRepository.findByFromPaymentAccountIdOrToPaymentAccountIdGreaterEqualCreateadTimestampSort(paymentAccountId, createdTimestamp, Sort.by(sort));

        List<Transaction> updateData = new ArrayList<>();

        if(!transactionList.isEmpty()) {

            for(Transaction transaction : transactionList) {

                if(transaction.getFrom().getPayment().getAccount().getId().equals(paymentAccountId)) {

                    BigDecimal credit = lastCredit.subtract(transaction.getAmount().getMain()).subtract(transaction.getAdditionalCost().getAdministration()).subtract(transaction.getAdditionalCost().getService()).subtract(transaction.getAdditionalCost().getTax());
                    transaction.getFrom().getPayment().getAccount().setCredit(credit);

                } else if(transaction.getTo().getPayment().getAccount().getId().equals(paymentAccountId)) {

                    BigDecimal credit = lastCredit.add(transaction.getAmount().getMain());
                    transaction.getFrom().getPayment().getAccount().setCredit(credit);

                }

                updateData.add(transaction);

            }

            if(!updateData.isEmpty()) {

                if(updateData.get((updateData.size() - 1)).getFrom().getPayment().getAccount().getId().equals(paymentAccountId)) {

                    lastCredit = updateData.get((updateData.size() - 1)).getFrom().getPayment().getAccount().getCredit();

                } else if(updateData.get((updateData.size() - 1)).getTo().getPayment().getAccount().getId().equals(paymentAccountId)) {

                    lastCredit = updateData.get((updateData.size() - 1)).getTo().getPayment().getAccount().getCredit();

                }

            }

            transactionRepository.saveAll(updateData);

        }

        PaymentAccount paymentAccountById = paymentAccountRepository.findById(paymentAccountId).orElse(new PaymentAccount());
        paymentAccountById.setCredit(lastCredit);
        paymentAccountRepository.save(paymentAccountById);

        result.setResponse("Payment account credit syncronized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse syncronizeUserCredit(String userId, String userAccountUsername, Date createdTimestamp) {

        BaseResponse result = new BaseResponse(
                "Failed to sync user credit",
                false
        );

        BigDecimal lastMainCredit = BigDecimal.ZERO;
        BigDecimal lastPromotionCredit = BigDecimal.ZERO;
        BigDecimal lastAccountCredit = BigDecimal.ZERO;

        Query query = new Query().addCriteria(new Criteria().orOperator(Criteria.where("from.user.id").is(userId), Criteria.where("to.user.id").is(userId))).addCriteria(Criteria.where("created.timestamp").lt(createdTimestamp)).with(Sort.by(Sort.Direction.DESC, "created.timestamp")).with(PageRequest.of(0, 1));
        List<Transaction> transactionPagination = globalRepository.findPagination(query, Transaction.class);

        if(!transactionPagination.isEmpty()) {

            if(transactionPagination.get(0).getFrom().getUser().getId() != null) {

                if(transactionPagination.get(0).getFrom().getUser().getId().equals(userId)) {

                    lastMainCredit = transactionPagination.get(0).getFrom().getUser().getCredit().getMain();
                    lastPromotionCredit = transactionPagination.get(0).getFrom().getUser().getCredit().getPromotion();

                    if(transactionPagination.get(0).getFrom().getUser().getAccount().getUsername().equals(userAccountUsername)) {

                        lastAccountCredit = transactionPagination.get(0).getFrom().getUser().getAccount().getCredit();

                    }

                }

            } else if(transactionPagination.get(0).getTo().getUser().getId() != null) {

                if(transactionPagination.get(0).getTo().getUser().getId().equals(userId)) {

                    lastMainCredit = transactionPagination.get(0).getTo().getPayment().getAccount().getCredit();
                    lastPromotionCredit = transactionPagination.get(0).getTo().getUser().getCredit().getPromotion();

                    if(transactionPagination.get(0).getTo().getUser().getAccount().getUsername().equals(userAccountUsername)) {

                        lastAccountCredit = transactionPagination.get(0).getTo().getUser().getAccount().getCredit();

                    }

                }

            }

        }

        List<Sort.Order> sort = new ArrayList<>() {
            {
                add(new Sort.Order(Sort.Direction.ASC, "created.timestamp"));
                add(new Sort.Order(Sort.Direction.ASC, "id"));
            }
        };
        List<Transaction> transactionList = transactionRepository.findByFromUserIdOrToUserIdGreaterEqualCreateadTimestampSort(userId, createdTimestamp, Sort.by(sort));

        List<Transaction> updateData = new ArrayList<>();

        if(!transactionList.isEmpty()) {

            for(Transaction transaction : transactionList) {

                if(transaction.getFrom().getUser().getId().equals(userId)) {

                    CreditReference credit = new CreditReference(
                            lastMainCredit.subtract(transaction.getAmount().getMain()),
                            lastPromotionCredit.subtract(transaction.getAmount().getPromotion())
                    );
                    transaction.getFrom().getUser().setCredit(credit);

                    if(!transaction.getFrom().getUser().getAccount().getUsername().isEmpty() && transaction.getFrom().getUser().getAccount().getUsername().equals(userAccountUsername)) {

                        BigDecimal accountCredit = lastAccountCredit.subtract(transaction.getAmount().getMain()).subtract(transaction.getAmount().getPromotion());
                        transaction.getFrom().getUser().getAccount().setCredit(accountCredit);

                    }

                } else if(transaction.getTo().getUser().getId().equals(userId)) {

                    CreditReference credit = new CreditReference(
                            lastMainCredit.add(transaction.getAmount().getMain()),
                            lastPromotionCredit.add(transaction.getAmount().getPromotion())
                    );
                    transaction.getTo().getUser().setCredit(credit);

                    if(!transaction.getTo().getUser().getAccount().getUsername().isEmpty() && transaction.getTo().getUser().getAccount().getUsername().equals(userAccountUsername)) {

                        BigDecimal accountCredit = lastAccountCredit.add(transaction.getAmount().getMain()).add(transaction.getAmount().getPromotion());
                        transaction.getFrom().getUser().getAccount().setCredit(accountCredit);

                    }

                }

                updateData.add(transaction);

            }

            if(!updateData.isEmpty()) {

                if(updateData.get((updateData.size() - 1)).getFrom().getUser().getId().equals(userId)) {

                    lastMainCredit = updateData.get((updateData.size() - 1)).getFrom().getUser().getCredit().getMain();
                    lastPromotionCredit = updateData.get((updateData.size() - 1)).getFrom().getUser().getCredit().getPromotion();
                    lastAccountCredit = updateData.get((updateData.size() - 1)).getFrom().getUser().getAccount().getCredit();

                } else if(updateData.get((updateData.size() - 1)).getTo().getUser().getId().equals(userId)) {

                    lastMainCredit = updateData.get((updateData.size() - 1)).getTo().getUser().getCredit().getMain();
                    lastPromotionCredit = updateData.get((updateData.size() - 1)).getTo().getUser().getCredit().getPromotion();
                    lastAccountCredit = updateData.get((updateData.size() - 1)).getTo().getUser().getAccount().getCredit();

                }

            }

            transactionRepository.saveAll(updateData);

        }

        User userById = userRepository.findById(userId).orElse(new User());
        userById.setCredit(new CreditReference(
                lastMainCredit,
                lastPromotionCredit
        ));

        if(!userAccountUsername.isEmpty()) {

            for(int i = 0; i < userById.getAccount().getUsernameList().size(); i++) {

                if(userById.getAccount().getUsernameList().get(i).equals(userAccountUsername)) {

                    userById.getAccount().getCreditList().set(i, lastAccountCredit);

                }

            }

        }

        userRepository.save(userById);

        result.setResponse("User credit syncronized");
        result.setResult(true);

        logger.info(result.getResponse());

        return result;

    }


    public BaseResponse update(HttpServletRequest request, User account, Transaction updateData) {

        BaseResponse result = new BaseResponse(
                "Failed to update transaction",
                false
        );

        updateData = initializeInput(updateData);

        Transaction transactionById = transactionRepository.findById(updateData.getId()).orElse(null);

        if(transactionById != null) {

            Map<String, BaseResponse> validation = inputValidation(updateData.getFrom().getPayment().getAccount().getId(), updateData.getFrom().getPayment().getMethod().getId(), updateData.getFrom().getPayment().getAccount().getName(), updateData.getFrom().getPayment().getAccount().getNumber(), updateData.getFrom().getUser().getId(), updateData.getFrom().getUser().getUsername(), updateData.getFrom().getUser().getAccount().getUsername(), updateData.getTo().getPayment().getAccount().getId(), updateData.getTo().getPayment().getMethod().getId(), updateData.getTo().getPayment().getAccount().getName(), updateData.getTo().getPayment().getAccount().getNumber(), updateData.getTo().getUser().getId(), updateData.getTo().getUser().getUsername(), updateData.getTo().getUser().getAccount().getUsername(), updateData.getType());

            boolean valid = true;

            for(Map.Entry<String, BaseResponse> map : validation.entrySet()) {

                if(!map.getValue().isResult()) {

                    valid = false;

                    result.setResponse(map.getValue().getResponse());

                    break;

                }

            }

            if(valid) {

                transactionLogDataRepository.save(data.initializeLogData("transactionId", transactionById, account, TransactionLogData.class));

                updateData.setTicketNumber(transactionById.getTicketNumber());
                updateData.setCreated(transactionById.getCreated());
                updateData.setModified(data.initializeTimestampReference(account.getId(), account.getUsername(), null));
                transactionRepository.save(updateData);

                for(String string : transactionById.getImageList()) {

                    if(!updateData.getImageList().contains(string)) {

                        file.deleteImage(global.getSetting().getPath().getImage() + "/transaction", string);

                    }

                }

                if(updateData.getFrom().getPayment().getAccount().getId() != null) {

                    syncronizePaymentAccountCredit(updateData.getFrom().getPayment().getAccount().getId(), transactionById.getCreated().getTimestamp());

                }

                if(!updateData.getFrom().getPayment().getAccount().getId().equals(transactionById.getFrom().getPayment().getAccount().getId())) {

                    syncronizePaymentAccountCredit(transactionById.getFrom().getPayment().getAccount().getId(), transactionById.getCreated().getTimestamp());

                }

                if(updateData.getTo().getPayment().getAccount().getId() != null) {

                    syncronizePaymentAccountCredit(updateData.getTo().getPayment().getAccount().getId(), transactionById.getCreated().getTimestamp());

                }

                if(!updateData.getTo().getPayment().getAccount().getId().equals(transactionById.getTo().getPayment().getAccount().getId())) {

                    syncronizePaymentAccountCredit(transactionById.getTo().getPayment().getAccount().getId(), transactionById.getCreated().getTimestamp());

                }

                if(updateData.getFrom().getUser().getId() != null) {

                    syncronizeUserCredit(updateData.getFrom().getUser().getId(), updateData.getFrom().getUser().getAccount().getUsername(), transactionById.getCreated().getTimestamp());

                }

                if(updateData.getTo().getUser().getId() != null) {

                    syncronizeUserCredit(updateData.getTo().getUser().getId(), updateData.getTo().getUser().getAccount().getUsername(), transactionById.getCreated().getTimestamp());

                }

                if(!updateData.getFrom().getUser().getId().equals(transactionById.getFrom().getUser().getId())) {

                    syncronizeUserCredit(transactionById.getFrom().getUser().getId(), transactionById.getFrom().getUser().getAccount().getUsername(), transactionById.getCreated().getTimestamp());

                }

                if(!updateData.getTo().getUser().getId().equals(transactionById.getTo().getUser().getId())) {

                    syncronizeUserCredit(transactionById.getTo().getUser().getId(), transactionById.getTo().getUser().getAccount().getUsername(), transactionById.getCreated().getTimestamp());

                }

                userLogService.insert(request, "", "Update transaction id " + updateData.getId(), UserLogType.UpdateTransaction, transactionById.getId(), transactionById.getType().toString(), account);

                result.setResponse("Transaction updated");
                result.setResult(true);

            }

        } else {

            result.setResponse("Transaction doesn't exist");

        }

        logger.info(result.getResponse());

        return result;

    }*/


}
