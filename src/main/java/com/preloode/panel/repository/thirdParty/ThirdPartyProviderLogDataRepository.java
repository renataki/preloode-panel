package com.preloode.panel.repository.thirdParty;

import com.preloode.panel.model.thirdParty.ThirdPartyProviderLogData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ThirdPartyProviderLogDataRepository extends MongoRepository<ThirdPartyProviderLogData, String> {


}
