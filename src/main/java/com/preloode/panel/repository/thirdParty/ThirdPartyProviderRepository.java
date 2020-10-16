package com.preloode.panel.repository.thirdParty;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.thirdParty.ThirdPartyProvider;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ThirdPartyProviderRepository extends MongoRepository<ThirdPartyProvider, String> {


    @Query("{'name' : ?0}")
    public ThirdPartyProvider findByName(String name);


    @Query("{'status' : ?0}")
    public List<ThirdPartyProvider> findByStatusSort(Status status, Sort sort);


}
