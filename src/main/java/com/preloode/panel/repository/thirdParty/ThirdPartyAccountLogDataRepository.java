package com.preloode.panel.repository.thirdParty;

import com.preloode.panel.model.thirdParty.ThirdPartyAccountLogData;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ThirdPartyAccountLogDataRepository extends MongoRepository<ThirdPartyAccountLogData, String> {


}
