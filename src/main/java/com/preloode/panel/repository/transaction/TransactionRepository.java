package com.preloode.panel.repository.transaction;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.transaction.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {


    @Query("{'$or': [{'from.payment.account.id' : ?0}, {'to.payment.account.id' : ?0}], 'created.timestamp': {$gte: ?1}}")
    public List<Transaction> findByFromPaymentAccountIdOrToPaymentAccountIdGreaterEqualCreateadTimestampSort(String paymentAccountId, Date createdTimestamp, Sort sort);


    @Query("{'$or': [{'from.user.id' : ?0}, {'to.user.id' : ?0}], 'created.timestamp': {$gte: ?1}}")
    public List<Transaction> findByFromUserIdOrToUserIdGreaterEqualCreateadTimestampSort(String userId, Date createdTimestamp, Sort sort);


    @Query("{'status' : ?0}")
    public List<Transaction> findByStatusSort(Status status, Sort sort);


}
