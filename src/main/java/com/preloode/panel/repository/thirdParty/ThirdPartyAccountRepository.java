package com.preloode.panel.repository.thirdParty;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.thirdParty.ThirdPartyAccount;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface ThirdPartyAccountRepository extends MongoRepository<ThirdPartyAccount, String> {


    @Query("{'provider.id' : ?0, 'username' : ?1}")
    public ThirdPartyAccount findByProviderIdUsername(String providerId, String username);


    @Query("{'status' : ?0}")
    public List<ThirdPartyAccount> findByStatusSort(Status status, Sort sort);


}
