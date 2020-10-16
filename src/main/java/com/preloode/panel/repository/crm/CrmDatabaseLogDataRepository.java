package com.preloode.panel.repository.crm;

import com.preloode.panel.model.crm.CrmDatabaseLogData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CrmDatabaseLogDataRepository extends MongoRepository<CrmDatabaseLogData, String> {


}
