package com.preloode.panel.repository.user;

import com.preloode.panel.enumeration.global.Status;
import com.preloode.panel.enumeration.user.UserType;
import com.preloode.panel.model.user.UserGroup;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserGroupRepository extends MongoRepository<UserGroup, String> {


    @Query("{'name' : ?0}")
    public UserGroup findByName(String name);


    @Query("{'status' : ?0}")
    public List<UserGroup> findByStatusSort(Status status, Sort sort);


    @Query("{'status' : ?0, 'type' : ?1}")
    public List<UserGroup> findByStatusTypeSort(Status status, UserType type, Sort sort);


}
