package com.preloode.panel.repository.crm;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.model.crm.CrmGroup;
import com.preloode.panel.model.user.UserGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface CrmGroupRepository extends MongoRepository<CrmGroup, String> {


    @Query("{'name' : ?0}")
    public CrmGroup findByName(String name);


    @Query("{'status' : ?0}")
    public List<CrmGroup> findByStatusSort(Status status, Sort sort);


    @Query("{'userGroup.idList' : ?0, 'status' : ?1}")
    public List<CrmGroup> findByUserGroupIdListStatusSort(String userGroupIdList, Status status, Sort sort);


}
