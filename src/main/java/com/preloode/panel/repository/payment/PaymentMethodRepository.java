package com.preloode.panel.repository.payment;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.payment.PaymentMethod;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface PaymentMethodRepository extends MongoRepository<PaymentMethod, String> {


    @Query("{'company.branch.idList' : ?0, 'company.idList' : ?1, 'footer.display' : ?2, 'status' : ?3}")
    public List<PaymentMethod> findByCompanyBranchIdListCompanyIdListFooterDisplayStatusSort(String companyBranchIdList, String companyIdList, boolean footerDisplay, Status status, Sort sort);


    @Query("{'name' : ?0}")
    public PaymentMethod findByName(String name);


    @Query("{'status' : ?0}")
    public List<PaymentMethod> findByStatusSort(Status status, Sort sort);


}
