package com.preloode.panel.repository.payment;

import com.preloode.panel.model.payment.PaymentAccountLogData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentAccountLogDataRepository extends MongoRepository<PaymentAccountLogData, String> {


}
