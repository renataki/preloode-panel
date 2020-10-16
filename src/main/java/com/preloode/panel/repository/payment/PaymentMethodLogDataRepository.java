package com.preloode.panel.repository.payment;

import com.preloode.panel.model.payment.PaymentMethodLogData;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface PaymentMethodLogDataRepository extends MongoRepository<PaymentMethodLogData, String> {


}
