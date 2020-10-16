package com.preloode.panel.repository.payment;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.payment.PaymentAccount;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentAccountRepository extends MongoRepository<PaymentAccount, String> {


    @Query("{'id' : ?0, 'method.id' : ?1, 'name' : ?2, 'number' : ?3}")
    public PaymentAccount findByIdMethodIdNameNumber(String id, String methodId, String name, String number);


    @Query("{'method.id' : ?0, 'number' : ?1}")
    public PaymentAccount findByMethodIdNumber(String methodId, String number);


    @Query("{'method.id' : ?0, 'status' : ?1}")
    public List<PaymentAccount> findByMethodIdStatusSort(String methodId, Status status, Sort sort);


    @Query("{'status' : ?0}")
    public List<PaymentAccount> findByStatusSort(Status status, Sort sort);


}
