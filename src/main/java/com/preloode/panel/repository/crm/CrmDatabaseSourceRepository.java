package com.preloode.panel.repository.crm;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.crm.CrmDatabaseSource;
import com.preloode.panel.model.user.UserGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface CrmDatabaseSourceRepository extends MongoRepository<CrmDatabaseSource, String> {


    @Query("{'name' : ?0}")
    public CrmDatabaseSource findByName(String name);


    @Query("{'status' : ?0}")
    public List<CrmDatabaseSource> findByStatusSort(Status status, Sort sort);


}
