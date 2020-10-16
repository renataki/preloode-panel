package com.preloode.panel.repository.company;

import com.preloode.panel.model.company.CompanyLogData;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface CompanyLogDataRepository extends MongoRepository<CompanyLogData, String> {


}
