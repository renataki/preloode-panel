package com.preloode.panel.repository.transaction;

import com.preloode.panel.model.transaction.TransactionLogData;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TransactionLogDataRepository extends MongoRepository<TransactionLogData, String> {


}
